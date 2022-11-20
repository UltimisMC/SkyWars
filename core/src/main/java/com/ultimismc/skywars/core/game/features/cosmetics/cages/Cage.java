package com.ultimismc.skywars.core.game.features.cosmetics.cages;

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

    @Setter private CageSchematic schematic;

    public Cage(CageSchematic schematic, PurchasableDesign design, String name, CosmeticRarity rarity) {
        super(design.getMaterial(), design.getDurability(), name, rarity);

        this.schematic = schematic;
    }

    public void placeSchematic(Location location) {
        schematic.placeSchematic(location);
    }
}
