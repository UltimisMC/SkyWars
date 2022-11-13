package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.features.Purchasable;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Perk extends Purchasable {

    PerkRarity getPerkRarity();

    List<String> getDescription();

    boolean isSoulWellPerk();
}
