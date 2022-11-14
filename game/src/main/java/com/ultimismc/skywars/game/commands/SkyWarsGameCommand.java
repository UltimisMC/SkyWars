package com.ultimismc.skywars.game.commands;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.game.GameManager;
import com.ultimismc.skywars.game.mode.GameHandler;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarsgame|sgame")
@CommandPermission("ultimismc.skywars.admin")
public class SkyWarsGameCommand {

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

    private void sendMessage(Player player, String message) {
        player.sendMessage(PluginUtility.translateMessage(message));
    }
}
