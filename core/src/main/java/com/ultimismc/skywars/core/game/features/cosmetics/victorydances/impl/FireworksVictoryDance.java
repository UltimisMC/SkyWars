package com.ultimismc.skywars.core.game.features.cosmetics.victorydances.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDance;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDanceExecutor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@Getter
public class FireworksVictoryDance extends VictoryDance {

    private final PurchasableDesign design = new PurchasableDesign(Material.FIREWORK);

    public FireworksVictoryDance() {
        super("Fireworks", CosmeticRarity.COMMON);
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    @Override
    public VictoryDanceExecutor executor() {
        return (user, tick) -> {
            Player player = user.getPlayer();

            World world = player.getWorld();
            Firework firework = (Firework) world.spawnEntity(player.getLocation(), EntityType.FIREWORK);
            firework.detonate();
        };
    }
}
