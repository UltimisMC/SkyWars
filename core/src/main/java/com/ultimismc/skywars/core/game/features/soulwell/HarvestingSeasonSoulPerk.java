package com.ultimismc.skywars.core.game.features.soulwell;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class HarvestingSeasonSoulPerk extends AbstractPerk {

    public HarvestingSeasonSoulPerk() {
        super(Material.GHAST_TEAR, (short)0, "Harvesting Season", PerkRarity.SOUL_WELL, false,
                Arrays.asList("&7Increase the maximum number of",
                        "&7souls you can have to &c250&7.",
                        " ",
                        "&b5% &7chance to gain double",
                        "&7souls from kills and wins."));
        setPrice(250000);
    }
}
