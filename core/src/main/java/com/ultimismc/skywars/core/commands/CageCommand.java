package com.ultimismc.skywars.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.Cage;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.CageHandler;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@CommandAlias("cages|cage")
@CommandPermission("ultimismc.skywars.admin")
public class CageCommand extends BaseCommand {

    @Dependency
    private CageHandler cageHandler;

    @HelpCommand
    @Syntax("")
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("placecage")
    @Syntax("<cage>")
    public void onPlaceCage(User user, String cageName, TeamType teamType) {
        cageHandler.placeCage(teamType, user, cageName, true);
    }

    @Subcommand("listcages")
    @Syntax("")
    public void onListCages(User user) {
        user.sendMessage("&aShowing cages:");
        for(Cage cage : cageHandler) {
            CosmeticRarity rarity = cage.getCosmeticRarity();
            user.sendMessage(" &7* &a" + cage.getName() + " " + rarity.getDisplayName() + "&a. ");
        }
    }

    @Subcommand("create|createcage")
    @Syntax("<name> <material> <durability> <rarity>")
    public void onCreateCage(User user, String name, Material material, int durability, String rarityName) {
        cageHandler.createCage(user, name, material, durability, rarityName);
    }

    @Subcommand("update|updatecage")
    @Syntax("<name>")
    public void onUpdateCage(User user, String name) {
        cageHandler.updateCageSchematic(user, name);
    }
}
