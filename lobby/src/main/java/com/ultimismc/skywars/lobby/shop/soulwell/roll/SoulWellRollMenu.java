package com.ultimismc.skywars.lobby.shop.soulwell.roll;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private final List<Purchasable> purchasables;
    private Purchasable currentPurchasable;
    private int currentPurchaseIndex = 0;

    private final int[] purchaseSlots;

    private final BukkitTask animationTask;


    public SoulWellRollMenu(SkyWarsPlugin plugin, User user) {
        super("Soul Well", 5);

        FeatureHandler featureHandler = plugin.getFeatureHandler();
        this.user = user;
        purchasables = new ArrayList<>(featureHandler.getAllPurchasables());
        Collections.shuffle(purchasables);

        purchasables.removeIf(purchasable -> {
            PurchasableRarity rarity = purchasable.getRarity();
            int occurrence = rarity.getOccurrenceChance();
            boolean hasChanceOccurred = PluginUtility.hasChanceOccurred(occurrence);
            return !purchasable.isSoulWell() || user.hasPurchased(purchasable) || !hasChanceOccurred;
        });
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

        int purchasesSize = purchasables.size();
        int purchaseSlotIndex = 0;
        for(int i = 0; i < purchaseSlots.length; i++) {
            Purchasable purchasable = purchasables.get(currentPurchaseIndex + purchaseSlotIndex);

            if(((currentPurchaseIndex + purchaseSlotIndex) + 1) >= purchasesSize) {
                currentPurchaseIndex = 0;
            }
            int purchaseSlot =  purchaseSlots[purchaseSlotIndex];
            if(purchaseSlot == 22) {
                currentPurchasable = purchasable;
            }
            purchaseSlotIndex++;
            Material material = purchasable.getDisplayMaterial();
            short durability = purchasable.getDisplayDurability();
            String name = purchasable.getNameWithCategory();

            PurchasableRarity purchasableRarity = purchasable.getRarity();
            ChatColor color = ChatColor.GREEN;
            if(purchasableRarity != null) {
                color = purchasableRarity.getColor();
            }
            MenuItem menuItem = new MenuItem(material, color + name, durability);
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

        String name = currentPurchasable.getNameWithCategory();

        PurchasableRarity rarity = currentPurchasable.getRarity();
        ChatColor rarityColor = rarity.getColor();
        String purchasableDisplayName = (rarityColor + name);
        Sound sound = Sound.LEVEL_UP;
        if(rarity == PurchasableRarity.LEGENDARY) {
            sound = Sound.ENDERDRAGON_GROWL;
        }
        player.playSound(player.getLocation(), sound, 1f, 1f);

        user.sendMessage("&7&lYou found " + purchasableDisplayName + " &7&lin the well!");
        if(rarity.isRare()) {
            String userDisplayName = user.getDisplayName();
            Bukkit.broadcastMessage(PluginUtility.translateMessage(userDisplayName + " &7has found " + purchasableDisplayName + " &7in the &bSoul Well&7!"));
        }
        user.addAsset(new UserAsset(currentPurchasable));
    }

    @Override
    public String getInventoryId() {
        return "SoulWell Roll";
    }
}
