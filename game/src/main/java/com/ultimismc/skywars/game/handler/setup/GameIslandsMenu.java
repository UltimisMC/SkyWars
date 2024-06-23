package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.game.island.Island;
import com.ultimismc.skywars.game.island.IslandHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class GameIslandsMenu extends PaginatedMenu<Island> {

    private final IslandHandler islandHandler;

    public GameIslandsMenu(IslandHandler islandHandler, GameConfig gameConfig) {
        super("Islands " + gameConfig.getServerId() + " (" + islandHandler.getSize() + "/" + TeamType.SOLO.getMaximumPlayers() + ")", 3, PaginatedModel.DEFAULT_MODEL);
        this.islandHandler = islandHandler;
    }

    @Override
    public MenuItem buildContent(Player player, Island island) {
        Location cageLocation = island.getCageLocation();
        int x = cageLocation.getBlockX();
        int y = cageLocation.getBlockY();
        int z = cageLocation.getBlockZ();

        MenuItem menuItem = new MenuItem(Material.CHEST, "&3Island &b" + x + "&3, &b" + y + "&3, &b" + z);
        menuItem.setLore(" ");
        menuItem.setLore("&eLeft Click to teleport!");
        menuItem.setLore("&eRight Click to remove!");

        menuItem.setAction((item, clicker, clickedBlock, clickType) -> {
            if(clickType == ClickType.LEFT) {
                player.teleport(cageLocation);
                return;
            }
            islandHandler.removeIsland(island);

            refresh(player);
        });
        return menuItem;
    }

    @Override
    public void build(Player player) {
        super.build(player);

        MenuItem menuItem = new MenuItem(Material.BARRIER, "&cClear All");
        menuItem.setLore("&7Removes all saved island locations");
        menuItem.setAction((item, clicker, clickedBlock, clickType) -> {
            islandHandler.getIslands().clear();
            player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
            player.closeInventory();
        });

        setSlot(4, menuItem);
    }

    @Override
    public Collection<Island> getList() {
        return islandHandler.getIslands();
    }
}
