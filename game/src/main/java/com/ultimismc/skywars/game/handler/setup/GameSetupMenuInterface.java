package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserPlayerInventoryUi;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class GameSetupMenuInterface extends UserPlayerInventoryUi {

    private final GameSetupHandler gameSetupHandler;
    public GameSetupMenuInterface(GameSetupHandler gameSetupHandler, User user) {
        super(user);

        this.gameSetupHandler = gameSetupHandler;
    }

    @Override
    public void build(Player player) {

        MenuItem addRemoveChest = new MenuItem(Material.CHEST, "&aAdd/Remove a Chest");
        addRemoveChest.setAction((item, clicker, clickedBlock, clickType) -> gameSetupHandler.addRemoveChest(user, clickedBlock, clicker.isSneaking()));
        addRemoveChest.setLore("&7Right click a chest to either add or remove it to the game.");
        addRemoveChest.setLore("&7Sneak + Right Click to add as middle chest.");


        setSlot(0, addRemoveChest);
    }
}
