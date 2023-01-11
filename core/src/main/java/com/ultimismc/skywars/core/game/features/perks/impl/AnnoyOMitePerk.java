package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.BowArrowHitEvent;
import com.ultimismc.skywars.core.events.UserDeathEvent;
import com.ultimismc.skywars.core.events.UserSkyWarsEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Silverfish;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class AnnoyOMitePerk extends Perk implements PerkEvent<BowArrowHitEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.MONSTER_EGG, 60);
    private final Class<BowArrowHitEvent> eventClass = BowArrowHitEvent.class;

    public AnnoyOMitePerk() {
        super("Annoy-o-mite", PerkRarity.RARE,
                Arrays.asList("&710% chance to spawn SilverFish",
                        "&7next to enemies when you hit",
                        "&7them with a bow."));
    }


    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, BowArrowHitEvent event) {
        if(!PluginUtility.hasChanceOccurred(10)) return;

        LivingEntity entity = event.getEnemy();
        World world = entity.getWorld();
        world.spawnEntity(entity.getLocation(), EntityType.SILVERFISH);
    }
}
