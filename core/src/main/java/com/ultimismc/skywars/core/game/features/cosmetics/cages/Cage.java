package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.AbstractCosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.CageSchematic;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

/**
 * @author DirectPlan
 */
@Getter

public class Cage extends AbstractCosmetic {

    @Setter private CageSchematic soloSchematic, teamSchematic;
    private final String category = "Cage";
    @Setter private boolean defaultCage = false;

    public Cage(CageSchematic soloSchematic, CageSchematic teamSchematic, PurchasableDesign design, String name, CosmeticRarity rarity) {
        super(design.getMaterial(), design.getDurability(), name, rarity);

        this.soloSchematic = soloSchematic;
        this.teamSchematic = teamSchematic;
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
