package com.ultimismc.skywars.game.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.GameManager;
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
        sendMessage(player, "&aYou've successfully set waiting location.");
    }

    @Subcommand("forcestart")
    @Syntax("")
    public void onForceStart(User user) {
        if(gameHandler.hasStarted()) {
            user.sendMessage("&cThis game has already started!");
            return;
        }
        user.sendMessage("&aYou've force started SkyWars &e" + gameHandler.getServerName() + " - " + gameHandler.getServerId() + " &agame!");
        gameHandler.startPreparer();
    }

    @CommandAlias("whereami")
    public void onWhereAmi(Player player) {
        GameServer gameServer = gameHandler.getGameServer();
        String name = gameServer.getName();
        sendMessage(player, "&aYou are currently connected to &e" + name + " - " +  gameServer.getServerId() +"&a.");
    }
    private void sendMessage(Player player, String message) {
        player.sendMessage(PluginUtility.translateMessage(message));
    }
}
