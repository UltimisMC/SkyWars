package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.chest.Chest;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.island.Island;
import com.ultimismc.skywars.game.island.IslandHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import com.ultimismc.skywars.game.user.UserSessionHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.manager.MenuManager;

/**
 * @author DirectPlan
 */
public class GameSetupHandler {

    private final GameHandler gameHandler;

    private final UserSessionHandler userSessionHandler;
    private final ChestHandler chestHandler;
    private final IslandHandler islandHandler;

    public GameSetupHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;

        userSessionHandler = gameHandler.getUserSessionHandler();
        chestHandler = gameHandler.getChestHandler();
        islandHandler = gameHandler.getIslandHandler();

    }

    public void toggleSetupMode(User user) {
        MenuManager menuManager = gameHandler.getMenuManager();

        UserGameSession userSession = userSessionHandler.getSession(user);

        boolean setupMode = userSession.isSetupMode();
        Player player = user.getPlayer();

        String serverName = gameHandler.getServerName();
        if(!setupMode) {
            MessageConfigKeys.SETUP_MODE_ENTERED.sendMessage(player, new Replacement("name", serverName));
            userSession.setSetupMode(true);
            menuManager.applyInventoryLayout(user, new GameSetupMenuLayout(this, user), true, true);
            return;
        }

        MessageConfigKeys.SETUP_MODE_LEFT.sendMessage(player, new Replacement("name", serverName));
        userSession.setSetupMode(false);
        menuManager.revertInventory(user);
    }

    public void addIsland(User user, Island island) {

        islandHandler.addIsland(island);
    }

    public void addRemoveCage(User user, Island island) {
        if(island == null) return;

        Player player = user.getPlayer();
        Location location = player.getLocation();

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Location cageLocation = island.getCageLocation();

        user.sendMessage("&aYou've " + (cageLocation != null ? "&cremoved" : "added")+ "&a a cage at &e" + x + "&a, &e" + y + "&a, &e" + z + "&a.");

        if(cageLocation == null) {
            island.setCageLocation(location);
            return;
        }
        island.setCageLocation(null);
    }

    public void addRemoveChest(User user, Island island, Block block, boolean midChest) {
        if(block == null) return;

        if(block.getType() != Material.CHEST) {
            user.sendMessage("&cThis block isn't a chest.");
            return;
        }

        Chest chest = chestHandler.getChest(block);
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if(chest != null) midChest = chest.isMidChest();
        user.sendMessage("&aYou've " + (chest != null ? "&cremoved" : "added")+ "&a a " + (midChest ? "&emiddle " : "") + "&achest at &e" + x + "&a, &e" + y + "&a, &e" + z + (midChest ? " &7(Middle Chest)" : "") + "&a.");
        if(chest == null) {
            chest = chestHandler.addChest(block, midChest);
            island.addChest(block, chest);
            return;
        }
        chestHandler.removeChest(block);
        island.removeChest(block);
    }
}
