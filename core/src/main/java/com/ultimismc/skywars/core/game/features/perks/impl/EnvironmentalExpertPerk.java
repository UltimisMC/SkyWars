package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserDamagedEvent;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class EnvironmentalExpertPerk extends Perk implements PerkEvent<UserDamagedEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.LEATHER_CHESTPLATE);
    private final Class<UserDamagedEvent> eventClass = UserDamagedEvent.class;

    public EnvironmentalExpertPerk() {
        super("Environmental Expert", PerkRarity.RARE,
                Arrays.asList("&7Reduces environmental damage by",
                        "&750%"));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserDamagedEvent event) {
        if(!event.isEnvironmentalDamage()) return;

        double damage = event.getDamage();
        event.setDamage((damage / 2)); // 50%
    }
}
