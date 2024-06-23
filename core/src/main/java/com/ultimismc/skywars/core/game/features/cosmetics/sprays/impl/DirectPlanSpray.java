package com.ultimismc.skywars.core.game.features.cosmetics.sprays.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.sprays.Spray;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class DirectPlanSpray extends Spray {

    private final PurchasableDesign design = new PurchasableDesign("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjAwOWVlYjc5MTE4MjUzZGUzZmQzYTIxZjhlYmExYTFkNzllOWI5ODY5NTE5OTI3NTJjMGQyYWZlNjFlMmQyOSJ9fX0=");

    public DirectPlanSpray() {
        super("DirectPlan", CosmeticRarity.LEGENDARY, "directplan.png");
    }

}
