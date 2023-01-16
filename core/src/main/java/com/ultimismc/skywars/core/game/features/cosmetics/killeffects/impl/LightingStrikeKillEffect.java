package com.ultimismc.skywars.core.game.features.cosmetics.killeffects.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffect;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectExecutor;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@Getter
public class LightingStrikeKillEffect extends KillEffect {

    private final PurchasableDesign design = new PurchasableDesign(Material.GLASS);

    public LightingStrikeKillEffect() {
        super("Lighting Strike", CosmeticRarity.COMMON);

        addDescription("Killing an enemy will wake up");
        addDescription("Thor.");
    }

    @Override
    public KillEffectExecutor killEffectExecutor(User target, User killer) {
        return new Executor(target, killer);
    }
}

class Executor extends KillEffectExecutor {

    public Executor(User user, User killer) {
        super(user, killer, 1);
    }

    @Override
    public void animateEffect(int tick) {
        Player player = user.getPlayer();

        World world = player.getWorld();
        world.spawnEntity(player.getLocation(), EntityType.LIGHTNING);
    }
}
