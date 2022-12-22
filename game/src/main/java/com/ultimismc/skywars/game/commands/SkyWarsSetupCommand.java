package com.ultimismc.skywars.game.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.Map;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.handler.setup.GameChestsMenu;
import com.ultimismc.skywars.game.handler.setup.GameIslandsMenu;
import com.ultimismc.skywars.game.handler.setup.GameSetupHandler;
import com.ultimismc.skywars.game.island.IslandHandler;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarssetup|gamesetup|gsetup")
@CommandPermission("ultimismc.skywars.admin")
public class SkyWarsSetupCommand extends BaseCommand {

    @Dependency
    private GameManager gameManager;

    @Dependency
    private GameHandler gameHandler;

    @HelpCommand
    @Syntax("")
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

    @CommandAlias("togglesetup")
    @Syntax("")
    public void onToggleSetup(User user) {
        GameSetupHandler gameSetupHandler = gameHandler.getGameSetupHandler();
        gameSetupHandler.toggleSetupMode(user);
    }

    @CommandAlias("mapinfo")
    @Syntax("")
    public void onMapInfo(User user) {
        GameConfig gameConfig = gameManager.getGameConfig();
        Map map = gameConfig.getMap();

        String mapName = map.getName();
        int chestsAmount = gameHandler.getRegisteredChests();
        int islandsAmount = gameHandler.getRegisteredIslands();
        user.sendMessage("&bShowing &3" + gameConfig.getServerId() + "&b server map info:");
        user.sendMessage(" ");
        user.sendMessage(" &7- &bName: &3" + mapName);
        user.sendMessage(" &7- &bIslands: &3" + islandsAmount);
        user.sendMessage(" &7- &bChests: &3" + chestsAmount);
        user.sendMessage(" ");
        user.sendMessage("&b&oRun &3/gsetup showchests &b&oto show available chests!");
    }

    @CommandAlias("showchests")
    public void onShowChests(Player player) {
        GameConfig gameConfig = gameManager.getGameConfig();
        ChestHandler chestHandler = gameHandler.getChestHandler();
        gameManager.openMenu(player, new GameChestsMenu(chestHandler, gameConfig));
    }

    @CommandAlias("showislands")
    public void onShowIslands(Player player) {
        GameConfig gameConfig = gameManager.getGameConfig();
        IslandHandler islandHandler = gameHandler.getIslandHandler();
        gameManager.openMenu(player, new GameIslandsMenu(gameHandler.getIslandHandler(), gameConfig));
    }

    @CommandAlias("renamemap")
    @Syntax("<name>")
    public void onRenameMap(User user, String name) {
        GameConfig gameConfig = gameManager.getGameConfig();
        String previousName = gameConfig.getMapName();
        gameConfig.setMapName(name);
        user.sendMessage("&aYou've changed map name from &e" + previousName + " &ato &e" + name + "&a.");
    }
}
