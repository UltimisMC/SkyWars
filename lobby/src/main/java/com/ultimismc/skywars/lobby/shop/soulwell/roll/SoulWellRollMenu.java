package com.ultimismc.skywars.lobby.shop.soulwell.roll;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.soulwell.rewards.BagOfCoinsReward;
import com.ultimismc.skywars.lobby.shop.soulwell.rewards.SoulWellPurchasableReward;
import com.ultimismc.skywars.lobby.shop.soulwell.rewards.SoulWellReward;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author DirectPlan
 */
public class SoulWellRollMenu extends InventoryUI {

    private final User user;
    private final List<SoulWellReward> soulWellRewards = new ArrayList<>();
    private SoulWellReward currentReward;
    private int currentPurchaseIndex = 0;

    private final int[] purchaseSlots;

    private final BukkitTask animationTask;


    public SoulWellRollMenu(SkyWarsPlugin plugin, User user) {
        super("Soul Well", 5);

        FeatureHandler featureHandler = plugin.getFeatureHandler();
        this.user = user;
        List<Purchasable> purchasables = new ArrayList<>(featureHandler.getAllPurchasables());

        purchasables.removeIf(purchasable -> {
            PurchasableRarity rarity = purchasable.getRarity();
            int occurrence = rarity.getOccurrenceChance();
            boolean hasChanceOccurred = PluginUtility.hasChanceOccurred(occurrence);
            return !purchasable.isSoulWell() || purchasable.isDefault() || !hasChanceOccurred;
            // TODO: purchasables that were already purchased will be turned into coins.
        });


        GameType[] gameTypes = GameType.values();
        for(Purchasable purchasable : purchasables) {
            GameType randomGameType = PluginUtility.getRandomElement(gameTypes);
            soulWellRewards.add(new SoulWellPurchasableReward(purchasable, randomGameType));
        }
        int randomBags = PluginUtility.getRandomInteger(1, 5);
        for(int i = 0; i < randomBags; i++) {
            BagOfCoinsReward bagOfCoinsReward = BagOfCoinsReward.getRandomBagOfCoinsReward();
            soulWellRewards.add(bagOfCoinsReward);
        }

        Collections.shuffle(soulWellRewards);

        purchaseSlots = new int[]{
                40,
                31,
                22,
                13,
                4
        };

        animationTask = Bukkit.getScheduler().runTaskTimer(plugin, new SoulWellRollAnimatorClock(this), 1L, 1L);
    }



    @Override
    public void build(Player player) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int glassMaxDurability = 15;

        for(int i = 0; i < getSize(); i++) {
            int randomGlassDurability = random.nextInt(glassMaxDurability) + 1;
            MenuItem menuItem = new MenuItem(Material.STAINED_GLASS_PANE, "&8Rolling...", randomGlassDurability);
            setSlot(i, menuItem);
        }

        int purchasesSize = soulWellRewards.size();
        int purchaseSlotIndex = 0;
        for(int i = 0; i < purchaseSlots.length; i++) {
            SoulWellReward soulWellReward = soulWellRewards.get(currentPurchaseIndex + purchaseSlotIndex);

            if(((currentPurchaseIndex + purchaseSlotIndex) + 1) >= purchasesSize) {
                currentPurchaseIndex = 0;
            }
            int purchaseSlot =  purchaseSlots[purchaseSlotIndex];
            if(purchaseSlot == 22) {
                currentReward = soulWellReward;
            }
            purchaseSlotIndex++;

            PurchasableDesign design = soulWellReward.getDesign();
            PurchasableRarity rarity = soulWellReward.getRarity();

            Material material = design.getMaterial();
            int durability = design.getDurability();
            String displayName = rarity.getColor() + soulWellReward.getDisplayName();

            MenuItem menuItem = new MenuItem(material, displayName, durability);
            menuItem.setCustomSkullProperty(design.getTexture());
            setSlot(purchaseSlot, menuItem);
        }
        currentPurchaseIndex++;

        MenuItem chosenArrowGlass = new MenuItem(Material.STAINED_GLASS, "&c", 15);
        setSlot(21, chosenArrowGlass);
        setSlot(23, chosenArrowGlass);
    }

    public void update() {
        Player player = user.getPlayer();
        if(!player.isOnline()) {
            animationTask.cancel();
            return;
        }
        updateButtons(player);
        player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
    }

    public void stopRoll() {
        animationTask.cancel();

        Player player = user.getPlayer();

        for(int i = 0; i < getSize(); i++) {
            if(i == 21 || i == 22 || i == 23) continue;
            inventory.setItem(i, null);
        }
        player.updateInventory();

        String displayName = currentReward.getDisplayName();

        PurchasableRarity rarity = currentReward.getRarity();
        displayName = rarity.getColor() + displayName;

        Sound sound = Sound.LEVEL_UP;
        if(rarity == PurchasableRarity.LEGENDARY) {
            sound = Sound.ENDERDRAGON_GROWL;
        }
        for(Player online : Bukkit.getOnlinePlayers()) {
            online.playSound(player.getLocation(), sound, 1f, 1f);
        }

        user.sendMessage("&7&lYou found " + displayName + " &7&lin the well!");
        if(rarity.isRare()) {
            String userDisplayName = user.getDisplayName();
            PluginUtility.broadcastMessage(userDisplayName + " &7has found " + displayName + " &7in the &bSoul Well&7!");
        }
        if(currentReward.hasPurchased(user)) {
            int rarityCoins = currentReward.getRarityCoins();
            user.sendMessage("&7&lSince you already have it, you receive &e&l " + rarityCoins + " coins&7&l.");
            Currency.COIN_CURRENCY.increaseCurrency(user, rarityCoins);
            return;
        }
        currentReward.giveReward(user);
    }

    @Override
    public String getInventoryId() {
        return "Soul Well Roll";
    }
}
