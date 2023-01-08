package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author DirectPlan
 */
public class SkyWarsEventListener implements Listener {

    private final SkyWarsPlugin plugin;
    private final UserManager userManager;

    public SkyWarsEventListener(SkyWarsPlugin plugin) {
        this.plugin = plugin;
        userManager = plugin.getUserManager();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        User user = userManager.getCachedUser(player);
        plugin.callEvent(new BlockMinedEvent(user, block));
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        User user = userManager.getCachedUser(player);
        ItemStack item = event.getItem();
        UserItemConsumeEvent itemConsumeEvent = new UserItemConsumeEvent(user, item);
        plugin.callEvent(itemConsumeEvent);
        event.setCancelled(itemConsumeEvent.isCancelled());
    }

    @EventHandler
    public void onArrowShot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if(!(projectile instanceof Arrow)) return;
        Arrow arrow = (Arrow) projectile;
        Entity entity = (Entity) arrow.getShooter();
        if(!(entity instanceof Player)) return;
        Player player = (Player) entity;

        User user = userManager.getCachedUser(player);
        plugin.callEvent(new BowArrowShotEvent(user, arrow));
    }
}
