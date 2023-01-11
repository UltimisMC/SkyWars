package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.BowArrowHitEvent;
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
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class FrostPerk extends Perk implements PerkEvent<BowArrowHitEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.ICE);
    private final Class<BowArrowHitEvent> eventClass = BowArrowHitEvent.class;

    public FrostPerk() {
        super("Frost", PerkRarity.LEGENDARY,
                Arrays.asList("&740% chance to give a player",
                        "&7slowness I for 3 seconds on",
                        "&7fully charged bow hit."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, BowArrowHitEvent event) {
        // TODO: Check if bow is fully charged
        Arrow arrow = event.getArrow();
        System.out.println("Arrow velocity at: " + arrow.getVelocity());
        if(!PluginUtility.hasChanceOccurred(40)) return;

        LivingEntity enemy = event.getEnemy();
        enemy.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0));
        enemy.sendMessage(PluginUtility.translateMessage("&eYou got Slowness I for 3 seconds from " + user.getDisplayName() + "&e's Frost Perk."));
    }
}
