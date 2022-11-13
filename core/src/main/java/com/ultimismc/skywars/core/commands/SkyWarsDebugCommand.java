package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.level.LevelReward;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.manager.MenuManager;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarsdebug|sdebug")
@CommandPermission("ultimismc.skywars.admin")
public class SkyWarsDebugCommand extends BaseCommand {

    @Dependency
    private LevelManager levelManager;

    @Dependency
    private MenuManager menuManager;

    @HelpCommand
    @Syntax("")
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }


    @Subcommand("displaylevels")
    public void onLevelsDisplay(User user) {
        LinkedList<Level> levels = levelManager.getLevelList();
        user.sendMessage("&aShowing available levels:");
        for(Level level : levels) {
            int order = level.getOrder();
            int expProgress = levelManager.getExpProgressOf(user, level);

            user.sendMessage("&7 - &bLevel: &3" + order + "&b, Exp Progress: &3(&b" + expProgress + "&3/" + level.getRequiredExp() + "&3)");
            if(!level.hasRewards()) continue;
            user.sendMessage("     &bRewards &7&o("+order+")&b:");
            for(LevelReward reward : level.getRewards()) {
                user.sendMessage("      &7 - &b" + reward.getDisplayName());
            }
        }
    }

    @Subcommand("givecoins")
    @Syntax("<amount>")
    public void onGiveCoins(User user, int amount) {
        increaseCurrency(user, Currency.COIN_CURRENCY, amount);
    }
    @Subcommand("giveexp")
    @Syntax("<experience>")
    public void onGiveExp(User user, int amount) {
        levelManager.giveExp(user, amount);
    }

    @Subcommand("givesouls")
    @Syntax("<souls>")
    public void onGiveSouls(User user, int amount) {
        increaseCurrency(user, Currency.SOUL_CURRENCY, amount);
    }

    @Subcommand("setexp")
    @Syntax("<amount>")
    public void onSetExp(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.setTotalExp(amount);

        levelManager.calculateUserLevel(user);
    }

    @Subcommand("dumpinventories")
    public void onDumpInventories(CommandSender sender) {
        sender.sendMessage(PluginUtility.translateMessage("&bShowing inventory entries:"));
        for(Map.Entry<UUID, InventoryUI> entry : menuManager.getInventories().entrySet()) {
            UUID uuid = entry.getKey();
            Player player = Bukkit.getPlayer(uuid);
            String name = player.getName();

            InventoryUI inventoryUI = entry.getValue();
            String inventoryId = inventoryUI.getInventoryId();

            sender.sendMessage(PluginUtility.translateMessage(" &3" + name + " &7 - &3") + inventoryId);
        }
    }

    private void increaseCurrency(User user, Currency currency, int amount) {
        currency.increaseCurrencyWithMessage(user, amount);
    }
}
