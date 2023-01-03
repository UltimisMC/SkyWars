package com.ultimismc.skywars.core.game.menu;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.server.SkyWarsServer;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.*;

/**
 * @author DirectPlan
 */
public class MapSelectorMenu extends PaginatedMenu<ServerMap> {

    private final SkyWarsServerManager serverManager;
    private final User user;
    private final TeamType teamType;
    private final GameType gameType;

    private final String modeDisplayName;

    public MapSelectorMenu(SkyWarsServerManager serverManager, User user, TeamType teamType, GameType gameType) {
        super("SkyWars " + teamType.getName() + " " + gameType.getName(), 6, PaginatedModel.HYPIXEL_MODEL);

        modeDisplayName = teamType.getName() + " " + gameType.getName();
        this.serverManager = serverManager;
        this.user = user;
        this.teamType = teamType;
        this.gameType = gameType;
    }


    @Override
    public Collection<ServerMap> getList() {

        Map<String, ServerMap> serverMaps = new HashMap<>();
        for(SkyWarsServer server : serverManager.getGameServers(teamType, gameType)) {
            String mapName = server.getMapName();
            ServerMap serverMap = serverMaps.computeIfAbsent(mapName, s -> new ServerMap(mapName));
            serverMap.incrementAvailableGames();
        }
        return serverMaps.values();
    }

    @Override
    public MenuItem buildContent(Player player, ServerMap serverMap) {

        String mapName = serverMap.getName();
        String displayName = ChatColor.GREEN + mapName;
        boolean favouriteMap = user.hasFavouriteMap(mapName);
        if(favouriteMap) {
            displayName = "&b✫ " + displayName;
        }
        MenuItem serverMapItem = new MenuItem(Material.FIREWORK_CHARGE, displayName);
        ItemStack itemStack = serverMapItem.getItemStack();
        Color color = Color.RED;
        if(favouriteMap) {
            color = Color.AQUA;
        }
        ItemMeta meta = itemStack.getItemMeta();
        FireworkEffectMeta fireworkEffectMeta = (FireworkEffectMeta) meta;
        fireworkEffectMeta.setEffect(FireworkEffect.builder()
                .with(FireworkEffect.Type.BALL)
                .withColor(color).build());
        itemStack.setItemMeta(fireworkEffectMeta);
        serverMapItem.setAction(new ServerMapItemAction(serverManager, user, teamType, gameType, serverMap));
        serverMapItem.setLore(ChatColor.DARK_GRAY + modeDisplayName);
        serverMapItem.setLore(" ");
        serverMapItem.setLore("&7Available Games: &a" + serverMap.getAvailableGames());
        serverMapItem.setLore("&7Map Selections: &aUnlimited");
        serverMapItem.setLore(" ");
        serverMapItem.setLore(ChatColor.GREEN + "▶ Click to play");
        serverMapItem.setLore("&eRight click to toggle favourite!");
        return serverMapItem;
    }


    @Override
    public void build(Player player) {
        super.build(player);

        MenuItem randomMapItem = new MenuItem(Material.FIREWORK, ChatColor.GREEN + "Random Map");
        randomMapItem.setAction(new RandomMapItemAction(serverManager, user, teamType, gameType));
        randomMapItem.setLore(ChatColor.DARK_GRAY + modeDisplayName);
        randomMapItem.setLore(" ");
        randomMapItem.setLore("&7Map Selections: &aUnlimited");
        randomMapItem.setLore(" ");
        randomMapItem.setLore(ChatColor.GREEN + "▶ Click to play");
        setSlot(39, randomMapItem);

        MenuItem randomFavoriteItem = new MenuItem(Material.DIAMOND, ChatColor.GREEN + "Random Favourite");
        randomFavoriteItem.setAction(new RandomMapItemAction(serverManager, user, teamType, gameType));
        randomFavoriteItem.setLore(ChatColor.DARK_GRAY + modeDisplayName);
        randomFavoriteItem.setLore(" ");
        randomFavoriteItem.setLore("&7Map Selections: &aUnlimited");
        randomFavoriteItem.setLore(" ");
        randomFavoriteItem.setLore(ChatColor.GREEN + "▶ Click to play");
        setSlot(41, randomFavoriteItem);

        addCloseButton();
    }
}

@Data
class ServerMap {
    private final String name;

    private int availableGames;

    public void incrementAvailableGames() {
        availableGames++;
    }
}

@RequiredArgsConstructor
class RandomMapItemAction implements ActionableItem {

    private final SkyWarsServerManager serverManager;
    private final User user;
    private final TeamType teamType;
    private final GameType gameType;

    @Override
    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
        clicker.closeInventory();
        serverManager.sendToAvailableServer(user, teamType, gameType);
    }
}

@RequiredArgsConstructor
class FavouriteMapItemAction implements ActionableItem {

    private final SkyWarsServerManager serverManager;
    private final User user;
    private final TeamType teamType;
    private final GameType gameType;

    @Override
    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
        Collection<String> favouriteMaps = user.getFavouriteMaps();
        if(favouriteMaps.isEmpty()) {
            user.sendMessage("&cNo favourite servers available!");
            return;
        }
        List<String> favouriteMapsList = new ArrayList<>(favouriteMaps);
        Collections.shuffle(favouriteMapsList);
        String randomFavouriteMap = favouriteMapsList.get(0);

        serverManager.sendToAvailableServer(user, teamType, gameType, randomFavouriteMap);
    }
}

@RequiredArgsConstructor
class ServerMapItemAction implements ActionableItem {

    private final SkyWarsServerManager serverManager;
    private final User user;
    private final TeamType teamType;
    private final GameType gameType;
    private final ServerMap serverMap;

    @Override
    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
        String mapName = serverMap.getName();
        if(clickType == ClickType.RIGHT) {
            if(user.hasFavouriteMap(mapName)) {
                user.removeFavouriteMap(mapName);
            }else {
                user.addFavouriteMap(mapName);
            }
            return;
        }
        clicker.closeInventory();
        serverManager.sendToAvailableServer(user, teamType, gameType, mapName);
    }

    @Override
    public boolean updateButtons(Player clicker, ClickType clickType) {
        return true;
    }
}