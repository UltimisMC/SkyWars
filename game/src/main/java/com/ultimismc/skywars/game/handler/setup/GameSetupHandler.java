package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.game.map.Chest;
import com.ultimismc.skywars.core.game.map.Island;
import com.ultimismc.skywars.core.game.map.Map;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.config.MessageConfigKeys;
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

    private final GameManager gameManager;
    private final UserSessionHandler userSessionHandler;
    private final Map map;

    public GameSetupHandler(GameManager gameManager) {
        this.gameManager = gameManager;
        map = gameManager.getServerMap();

        userSessionHandler = gameManager.getUserSessionHandler();
    }

    public void toggleSetupMode(User user) {
        MenuManager menuManager = gameManager.getMenuManager();

        UserGameSession userSession = userSessionHandler.getSession(user);

        boolean setupMode = userSession.isSetupMode();
        Player player = user.getPlayer();

        String serverName = gameManager.getServerName();
        if(!setupMode) {
            MessageConfigKeys.SETUP_MODE_ENTERED.sendMessage(player, new Replacement("name", serverName));
            userSession.setSetupMode(true);
            menuManager.applyDesign(new GameSetupMenuInterface(this, user), true, true);
            return;
        }

        MessageConfigKeys.SETUP_MODE_LEFT.sendMessage(player, new Replacement("name", serverName));
        userSession.setSetupMode(false);
        menuManager.revertInventory(user);
    }

    public void addRemoveIsland(User user) {
        Player player = user.getPlayer();
        Location location = player.getLocation();

        Island island = map.getIsland(location);
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        user.sendMessage("&aYou've " + (island != null ? "&cremoved" : "added")+ "&a a cage at &e" + x + "&a, &e" + y + "&a, &e" + z + "&a.");
        if(island == null) {
            map.addIsland(new Island(location));
            return;
        }
        map.removeIsland(location);
    }

    public void addRemoveChest(User user, Block block, boolean midChest) {
        if(block == null) return;

        if(block.getType() != Material.CHEST) {
            user.sendMessage("&cThis block isn't a chest.");
            return;
        }

        Chest chest = map.getChest(block);
        Location location = block.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if(chest != null) midChest = chest.isMidChest();
        user.sendMessage("&aYou've " + (chest != null ? "&cremoved" : "added")+ "&a a " + (midChest ? "&emiddle " : "") + "&achest at &e" + x + "&a, &e" + y + "&a, &e" + z + (midChest ? " &7(Middle Chest)" : "") + "&a.");
        if(chest == null) {
            map.addChest(block, midChest);
            return;
        }
        map.removeChest(block);
    }
}
