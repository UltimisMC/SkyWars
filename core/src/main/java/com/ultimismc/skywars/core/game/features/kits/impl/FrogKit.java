package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class FrogKit extends AbstractKit {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE3ODE4OWVmOGZhN2E1YjcyNGZiOTFkZjlhNDQ3ODRmZDg1NjQ4ZWQzZTNhY2Y2ZDBkZWQ3YjhjYWEzMGYwNyJ9fX0=");

    public FrogKit() {
        super("Frog", KitRarity.COMMON);
    }
}
