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
public class BridgerPerk extends Perk implements PerkEvent<UserItemConsumeEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.WOOD);
    private final Class<UserItemConsumeEvent> eventClass = UserItemConsumeEvent.class;

    public BridgerPerk() {
        super("Bridger", PerkRarity.COMMON,
                Arrays.asList("&7Grants 50% chance to not consume",
                        "&7placeable blocks."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserItemConsumeEvent event) {
        if(!event.isBlockItem()) return;
        if(!PluginUtility.hasChanceOccurred(50)) return;

        event.setCancelled(true);
    }
}
