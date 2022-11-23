package com.ultimismc.skywars.game.menubar.menu;

import com.ultimismc.skywars.core.game.features.PurchasableItem;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
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

    private final GameHandler gameHandler;
    private final User user;

    public KitSelectorMenu(GameHandler gameHandler, User user) {
        super("Kit Selector", 6);

        this.gameHandler = gameHandler;
        this.user = user;
    }

    @Override
    public void build(Player player) {

        List<Kit> purchasedKits = user.getAssets(Kit.class);

        int index = 0;
        for(Kit kit : purchasedKits) {
            MenuItem menuItem = new PurchasableItem(kit, ChatColor.GREEN);
            menuItem.setAction(new KitSelectorItemAction(user, kit));

            List<String> lore = new ArrayList<>(kit.getDisplayItems());

            menuItem.setLore(lore);
            setSlot(index, menuItem);
            index++;
        }
        addCloseButton();
    }

    @RequiredArgsConstructor
    public static class KitSelectorItemAction implements ActionableItem {

        private final User user;
        private final Kit kit;

        @Override
        public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
            clicker.closeInventory();
            PluginUtility.playSound(clicker, Sound.SUCCESSFUL_HIT);
            user.setSetting("kit", kit);
        }
    }
}
