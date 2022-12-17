package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.kits.impl.*;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.directplan.directlib.PluginUtility;

import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class KitManager extends PurchasableRegistry<Kit> {

    private final String name = "Kits";

    public KitManager() {
        super("kit");
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasables(new DefaultKit(), new ArmorerKit(), new ArmorsmithKit(), new BatguyKit(), new CactusKit(), new DiscoKit(), new EcologistKit(),
                new FrogKit(), new GrenadeKit(), new HealerKit(), new KnightKit(), new ProKit(), new ScoutKit(), new BaseballPlayerKit(),
                new EnchanterKit(), new EnderchestKit(), new EngineerKit(), new FarmerKit(), new FishermanKit(), new HunterKit(), new MagicianKit(),
                new PharaohKit(), new PigRiderKit(), new PrincessKit(), new SlothKit(), new SnowmanKit(), new SpeleologistKit(), new WarlockKit(),
                new CannoneerKit(), new PyroKit(), new SalmonKit(), new SlimeKit(), new TrollKit(), new RookieKit(), new EnergixKit());

        for(Kit kit : this) {
            for(GameType gameType : GameType.values()) {
                KitBundle kitBundle = kit.getBundle(gameType);
                if(kitBundle == null) {
                    kitBundle = new Kit.DummyKitBundle();
                    kit.addBundle(gameType, kitBundle);
                }
                kitBundle.buildGameItems();
            }
        }
        super.initializeFeature(plugin);
    }

    public void giveKit(User user, Kit kit, GameType gameType) {
        Player player = user.getPlayer();

        PlayerInventory inventory = player.getInventory();
        List<KitItem> items = kit.getItems(gameType);
        for(KitItem kitItem : items) {
            ItemStack itemStack = kitItem.getItemStack();
            int armorSlot = PluginUtility.getItemArmorSlot(itemStack);
            if(armorSlot != -1) {
                inventory.setItem(armorSlot, itemStack);
                continue;
            }
            inventory.addItem(itemStack);
        }
    }

    public void giveKit(User user, GameType gameType) {
        Kit kit = user.getSetting(Kit.class, settingKey);
        giveKit(user, kit, gameType);
    }
}
