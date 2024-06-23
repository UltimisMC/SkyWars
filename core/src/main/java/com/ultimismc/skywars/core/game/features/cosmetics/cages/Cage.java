package com.ultimismc.skywars.core.game.features.cosmetics.cages;

import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.cages.schematic.CageSchematic;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class Cage extends Cosmetic {

    private final PurchasableDesign design;

    @Setter private Map<TeamType, CageSchematic> schematics;
    private final String category = "Cage";
    @Setter private boolean defaultCage = false;

    public Cage(Map<TeamType, CageSchematic> schematics, PurchasableDesign design, String name, CosmeticRarity rarity) {
        super(name, rarity);
        this.schematics = schematics;
        this.design = design;

        addDescription("Selects the " + getNameWithCategory() + ".");
    }

    public void placeSchematic(TeamType teamType, Location location, boolean ignoreAir) {
        CageSchematic schematic = schematics.get(teamType);
        if(schematic == null) throw new IllegalStateException("Could not found the cage '" + getName() + "' from " + teamType.getName() + " mode.");

        schematic.placeSchematic(location, ignoreAir);
    }

    public void updateTeamSchematic(TeamType teamType, CageSchematic schematic) {
        schematics.put(teamType, schematic);
    }

    @Override
    public boolean isDefault() {
        return defaultCage;
    }
}
