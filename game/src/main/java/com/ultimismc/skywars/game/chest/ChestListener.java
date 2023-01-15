package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class ChestListener implements Listener {

    private final GameHandler gameHandler;
    private final ChestHandler chestHandler;

    public ChestListener(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        chestHandler = gameHandler.getChestHandler();
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UserGameSession userGameSession = gameHandler.getSession(player);
        if(userGameSession.isSpectator()) {
            event.setCancelled(true);
            return;
        }
        Block clickedBlock = event.getClickedBlock();
        Action action = event.getAction();
        if(clickedBlock == null) return;
        if(action != Action.RIGHT_CLICK_BLOCK) return;
        if(clickedBlock.getType() != Material.CHEST) return;

        event.setCancelled(true);
        chestHandler.openChest(userGameSession, clickedBlock);
        PluginUtility.playSound(player, Sound.CHEST_OPEN);
    }

    @EventHandler
    public void onChestBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if(block.getType() != Material.CHEST) return;

        Chest chest = chestHandler.getChest(block);
        if(chest == null) return;

        Inventory inventory = chest.getInventory();
        for(ItemStack itemStack : inventory) {
            if(itemStack == null) continue;
            if(itemStack.getType() == Material.AIR) continue;

            World world = block.getWorld();
            world.dropItem(block.getLocation(), itemStack);
        }
        chestHandler.removeChest(block);
    }
}
