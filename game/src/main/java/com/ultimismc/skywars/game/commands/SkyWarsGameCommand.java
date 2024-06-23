package com.ultimismc.skywars.game.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.chest.ChestHandler;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarsgame|sgame")
@CommandPermission("ultimismc.skywars.admin")
public class SkyWarsGameCommand extends BaseCommand {

    @Dependency
    private GameManager gameManager;

    @Dependency
    private GameHandler gameHandler;

    @HelpCommand
    @Syntax("")
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("setwaitingspawn")
    public void onSetWaitingSpawn(Player player) {
        gameManager.setWaitingSpawnLocation(player.getLocation());
        sendMessage(player, "&aYou've successfully set waiting location!");
    }

    @Subcommand("forcestart")
    @Syntax("[countdown]")
    public void onForceStart(User user, @Default("0") Integer seconds) {
        if(gameHandler.hasStarted()) {
            user.sendMessage("&cThis game has already started!");
            return;
        }
        String serverDisplayName = gameHandler.getServerName() + " - " + gameHandler.getServerId();
        user.sendMessage("&aYou've force started SkyWars &e" + serverDisplayName + " &agame!");
        PluginUtility.broadcastMessage("&aSkyWars &e" + serverDisplayName + " &ahas been forcefully started by " + user.getDisplayName() + "&a.");
        if(seconds <= 0) {
            gameHandler.startPreparer();
            return;
        }
        gameHandler.startPreparer(seconds);
    }

    @Subcommand("refillchests")
    @Syntax("")
    public void onRefillChests(User user) {
        ChestHandler chestHandler = gameHandler.getChestHandler();
        chestHandler.refillAllChests();
        user.sendMessage("&aAll chests have been refilled!");
    }

    @CommandAlias("whereami")
    public void onWhereAmi(Player player) {
        GameConfig gameConfig = gameHandler.getGameConfig();
        String name = gameConfig.getName();
        sendMessage(player, "&aYou are currently connected to &e" + name + " - " +  gameConfig.getServerId() +"&a.");
    }
    private void sendMessage(Player player, String message) {
        player.sendMessage(PluginUtility.translateMessage(message));
    }
}
