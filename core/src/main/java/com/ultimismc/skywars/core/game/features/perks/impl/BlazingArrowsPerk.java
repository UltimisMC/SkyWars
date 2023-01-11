package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.BowArrowShotEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class BlazingArrowsPerk extends Perk implements PerkEvent<BowArrowShotEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.BLAZE_POWDER);
    private final Class<BowArrowShotEvent> eventClass = BowArrowShotEvent.class;

    public BlazingArrowsPerk() {
        super("Blazing Arrows", PerkRarity.RARE,
                Arrays.asList("&715% chance of shooting a fire",
                        "&7arrow with a bow."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, BowArrowShotEvent event) {
        if(!PluginUtility.hasChanceOccurred(15)) return;
        Arrow arrow = event.getArrow();

        arrow.setFireTicks(300); // 15 seconds
    }
}
