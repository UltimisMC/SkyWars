package com.ultimismc.skywars.core.game.features.soulwell;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class HarvestingSeasonSoulPerk extends Perk {

    private final PurchasableDesign design = new PurchasableDesign(Material.GHAST_TEAR);

    public HarvestingSeasonSoulPerk() {
        super("Harvesting Season", PerkRarity.SOUL_WELL, false,
                Arrays.asList("&7Increase the maximum number of",
                        "&7souls you can have to &c250&7.",
                        " ",
                        "&b5% &7chance to gain double",
                        "&7souls from kills and wins."));
        setPrice(250000);
    }
}
