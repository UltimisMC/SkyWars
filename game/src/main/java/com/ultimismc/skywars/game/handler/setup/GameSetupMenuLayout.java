package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.island.Island;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PlayerInventoryLayout;

/**
 * @author DirectPlan
 */
public class GameSetupMenuLayout extends PlayerInventoryLayout {

    private final GameSetupHandler gameSetupHandler;

    private final User user;
    private Island currentIsland;

    public GameSetupMenuLayout(GameSetupHandler gameSetupHandler, User user) {
        this.gameSetupHandler = gameSetupHandler;
        this.user = user;
    }

    @Override
    public void build(Player player) {
        if(currentIsland != null) {

            MenuItem addRemoveChest = new MenuItem(Material.CHEST, "&aAdd/Remove a Chest");
            addRemoveChest.setAction(updateOnClick(player, (item, clicker, clickedBlock, clickType) -> gameSetupHandler.addRemoveChest(user, currentIsland, clickedBlock, clicker.isSneaking())));
            addRemoveChest.setLore("&7Right click a chest to either add or remove it to the game.");
            addRemoveChest.setLore("&7Sneak + Right Click to add as middle chest.");

            setSlot(0, addRemoveChest);

            MenuItem addRemoveCage = new MenuItem(Material.BED, "&eAdd/Remove Cage");
            addRemoveCage.setAction(updateOnClick(player, (item, clicker, clickedBlock, clickType) -> gameSetupHandler.addRemoveCage(user, currentIsland)));
            addRemoveCage.setLore("&7Right click to either add or remove cage location.");
            setSlot(4, addRemoveCage);

            MenuItem saveIsland = new MenuItem(Material.BED, "Add Island" + (currentIsland.isConfigured() ? "&c&lNOT FULLY CONFIGURED" : "&a&lFULLY CONFIGURED"));
            saveIsland.setAction(updateOnClick(player, (item, clicker, clickedBlock, clickType) -> gameSetupHandler.addIsland(user, currentIsland)));
            saveIsland.setLore("&7Right click to add this island configuration.");
            setSlot(7, saveIsland);
        }

        MenuItem createDeleteIsland = new MenuItem(Material.BED, currentIsland != null ? "&cDelete this island." : "&aCreate a new island.");
        createDeleteIsland.setAction(updateOnClick(player, (item, clicker, clickedBlock, clickType) -> {
            currentIsland = clickType.isRightClick() ? new Island() : null;
        }));
        createDeleteIsland.setLore("&7Right click to create a new island configuration.");
        if (currentIsland != null) createDeleteIsland.setLore("&7Left click to delete this island configuration.");

        setSlot(8, createDeleteIsland);
    }

    public ActionableItem updateOnClick(Player player, ActionableItem actionableItem) {
        refresh(player);
        return actionableItem;
    }
}
