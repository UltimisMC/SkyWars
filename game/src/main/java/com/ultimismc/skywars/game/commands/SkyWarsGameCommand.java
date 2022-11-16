package com.ultimismc.skywars.game.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.GameServer;
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


    @CommandAlias("whereami")
    public void onWhereAmi(Player player) {
        GameServer gameServer = gameHandler.getGameServer();
        String name = gameServer.getName();
        sendMessage(player, "&aYou are currently in server &e" + name + " - " +  gameServer.getServerId() +"&a.");
    }
    private void sendMessage(Player player, String message) {
        player.sendMessage(PluginUtility.translateMessage(message));
    }
}
