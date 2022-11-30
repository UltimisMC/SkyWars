package com.ultimismc.skywars.game.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.Purchasable;
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

    @Dependency
    private FeatureHandler featureHandler;

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
    public void onForceStart(User user, @Optional int seconds) {
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

    @Subcommand("setsetting")
    @Syntax("<key> <value>")
    public void onSetSetting(User user, @Flags("other") User target, String key, String value) {
        Object objValue = value;
        Purchasable purchasable = featureHandler.getPurchasable(value);
        if(purchasable != null) {
            objValue = purchasable;
            user.sendMessage("&aFound a purchasable with this value: &e" + purchasable.getNameWithCategory());

        }
        target.setSetting(key, objValue);
        user.sendMessage("&aYou have set &e" + target.getDisplayName() + "&a's setting &e" + key + "&a to &e" + value + "&a.");
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
