package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.CageSchematic;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

/**
 * @author DirectPlan
 */
@Getter
public class Cage extends Cosmetic {

    private final PurchasableDesign design;

    @Setter private CageSchematic soloSchematic, teamSchematic;
    private final String category = "Cage";
    @Setter private boolean defaultCage = false;

    public Cage(CageSchematic soloSchematic, CageSchematic teamSchematic, PurchasableDesign design, String name, CosmeticRarity rarity) {
        super(name, rarity);

        this.soloSchematic = soloSchematic;
        this.teamSchematic = teamSchematic;

        this.design = design;
    }

    public void placeSchematic(TeamType teamType, Location location, boolean ignoreAir) {
        CageSchematic schematic = (teamType.isSolo() ? soloSchematic : teamSchematic);
        schematic.placeSchematic(location, ignoreAir);
    }

    @Override
    public boolean isDefault() {
        return defaultCage;
    }
}
