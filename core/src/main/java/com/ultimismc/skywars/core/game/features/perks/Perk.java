package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.features.Purchasable;

/**
 * @author DirectPlan
 */
public interface Perk extends Purchasable {

    PerkRarity getRarity();

    String getDescription();

    boolean isSoulWellPerk();
}
