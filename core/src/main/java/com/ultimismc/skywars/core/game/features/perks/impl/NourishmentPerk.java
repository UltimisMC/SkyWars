package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserKillEvent;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class NourishmentPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.BREAD);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public NourishmentPerk() {
        super("Nourishment", PerkRarity.COMMON,
                Arrays.asList("&7Every kill gives you full hunger",
                        "&7and saturation."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        Player player = user.getPlayer();
        player.setFoodLevel(20);
        player.setSaturation(20);
    }
}
