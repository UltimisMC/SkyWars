package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.LevelManager;
import com.ultimismc.skywars.core.game.features.level.LevelReward;
import com.ultimismc.skywars.core.user.User;

import java.util.LinkedList;

/**
 * @author DirectPlan
 */
@CommandAlias("skywarsdebug|sdebug")
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
            int requiredExp = level.getRequiredExp();
            user.sendMessage("&7 - &bLevel: &3" + order + "&b, &3" + requiredExp + " Exp");
            if(!level.hasRewards()) continue;
            user.sendMessage("     &bRewards &7&o("+order+")&b:");
            for(LevelReward reward : level.getRewards()) {
                user.sendMessage("      &7 - &b" + reward.getDisplayName());
            }
        }
    }
}
