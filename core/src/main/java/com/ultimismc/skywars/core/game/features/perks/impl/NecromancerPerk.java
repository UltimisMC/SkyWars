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
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreature;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class NecromancerPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.ROTTEN_FLESH);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;


    public NecromancerPerk() {
        super("Necromancer", PerkRarity.LEGENDARY,
                Arrays.asList("&716% chance to spawn a friendly",
                        "&7Zombie on kill."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        if(!PluginUtility.hasChanceOccurred(16)) return;

        User victim = event.getVictim();
        Player player = victim.getPlayer();
        World world = player.getWorld();
        Zombie zombie = (Zombie) world.spawnEntity(player.getLocation(), EntityType.ZOMBIE);
    }
}
