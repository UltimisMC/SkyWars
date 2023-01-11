package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserDeathEvent;
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
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;

/**
 * @author DirectPlan
 */
@Getter
public class RevengePerk extends Perk implements PerkEvent<UserDeathEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_SWORD);
    private final Class<UserDeathEvent> eventClass = UserDeathEvent.class;

    public RevengePerk() {
        super("Revenge", PerkRarity.LEGENDARY,
                "&7Spawn a spider when you die.");
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserDeathEvent event) {
        Player player = user.getPlayer();
        World world = player.getWorld();
        Spider spider = (Spider) world.spawnEntity(player.getLocation(), EntityType.SPIDER);

        User killer = event.getKiller();
        if(killer == null) return;
        Player killerPlayer = killer.getPlayer();
        spider.setTarget(killerPlayer);
    }
}
