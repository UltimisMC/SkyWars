package com.ultimismc.skywars.game.menubar.menu;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class KitSelectorMenu extends InventoryUI {

    private final User user;
    private final GameType gameType;
    private final KitManager kitManager;

    public KitSelectorMenu(GameHandler gameHandler, User user) {
        super("Kit Selector", 6);

        this.user = user;
        this.gameType = gameHandler.getGameType();
        FeatureHandler featureHandler = gameHandler.getFeatureHandler();
        kitManager = featureHandler.getKitManager();
    }

    @Override
    public void build(Player player) {
        Kit selectedKit = user.getSetting(Kit.class, kitManager.getSettingKey());
        fillInventory(new MenuItem(Material.STAINED_GLASS_PANE, "&c", 15));

        int index = 0;
        for(Kit kit : kitManager) {
            PurchasableDesign purchasableDesign = kit.getKitDesign(gameType);
            Material material = purchasableDesign.getMaterial();
            int durability = purchasableDesign.getDurability();

            ChatColor color = ChatColor.GREEN;
            ActionableItem action = null;
            boolean purchased = user.hasPurchased(kit, gameType);
            if(!purchased) {
                color = ChatColor.RED;
                material = Material.STAINED_GLASS_PANE;
                durability = 14;
            }else {
                action = new KitSelectorItemAction(kitManager, user, kit);
            }

            String displayName = color + kit.getName();

            MenuItem menuItem = new MenuItem(material, displayName, durability, action);

            String status = "&eClick to select!";
            if(!purchased) {
                status = "&cNot unlocked yet!";
            }else if(kit == selectedKit) {
                status = "&aSELECTED";
                menuItem.addInvisibleEnchantment();
            }

            List<String> lore = new ArrayList<>(kit.getDisplayItems(gameType));
            lore.add(" ");
            lore.add(status);

            menuItem.setLore(lore);

            setSlot(index, menuItem);
            index++;
        }
        addCloseButton();
    }

    @RequiredArgsConstructor
    public static class KitSelectorItemAction implements ActionableItem {

        private final KitManager kitManager;
        private final User user;
        private final Kit kit;

        @Override
        public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
            PluginUtility.playSound(clicker, Sound.CLICK);
            user.setSetting(kitManager.getSettingKey(), kit);
            user.sendMessage("&eYou've selected " + kit.getName() + " kit!");
        }

        @Override
        public boolean isRefreshable(Player clicker, ClickType clickType) {
            return true;
        }
    }
}
