package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.level.LevelReward;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;

import java.util.LinkedList;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarsdebug|sdebug")
@CommandPermission("ultimismc.skywars.admin")
public class SkyWarsDebugCommand extends BaseCommand {

    @Dependency
    private LevelManager levelManager;

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

    @Subcommand("setexp")
    @Syntax("<amount>")
    public void onSetExp(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.setTotalExp(amount);

        levelManager.calculateUserLevel(user);
    }
}
