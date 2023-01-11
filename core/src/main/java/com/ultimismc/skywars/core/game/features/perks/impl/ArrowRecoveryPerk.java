package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserItemConsumeEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class ArrowRecoveryPerk extends Perk implements PerkEvent<UserItemConsumeEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.ARROW);
    private final Class<UserItemConsumeEvent> eventClass = UserItemConsumeEvent.class;

    public ArrowRecoveryPerk() {
        super("Arrow Recovery", PerkRarity.RARE,
                Arrays.asList("&750% chance of getting your arrow",
                        "&7back on bow hit."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserItemConsumeEvent event) {
        if(!event.isType(Material.ARROW)) return;
        if(!PluginUtility.hasChanceOccurred(50));

        event.setCancelled(true);
    }
}
