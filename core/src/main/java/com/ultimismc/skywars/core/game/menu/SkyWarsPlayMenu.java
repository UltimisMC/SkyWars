package com.ultimismc.skywars.core.game.menu;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.server.SkyWarsServerManager;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.inventory.ActionableItem;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class SkyWarsPlayMenu extends InventoryUI {

    private final GameMenuHandler gameMenuHandler;
    private final SkyWarsServerManager serverManager;
    private final User user;
    private final TeamType teamType;

    public SkyWarsPlayMenu(GameMenuHandler gameMenuHandler, SkyWarsServerManager serverManager, User user, TeamType teamType) {
        super("Play SkyWars", 4);

        this.gameMenuHandler = gameMenuHandler;
        this.serverManager = serverManager;
        this.user = user;
        this.teamType = teamType;
    }


    @Override
    public void build(Player player) {
        addMode(11, GameType.NORMAL, Material.ENDER_PEARL);
        addMode(14, GameType.INSANE, Material.EYE_OF_ENDER);

        addCloseButton();
    }


    private void addMode(int slot, GameType gameType, Material gameMaterial) {

        int onlinePlayers = serverManager.getOnlinePlayers(teamType, gameType);
        String gameDisplayName = teamType.getName() + " " + gameType.getName();
        MenuItem playItem = new MenuItem(gameMaterial, ChatColor.GREEN + "SkyWars (" + gameDisplayName + ")");
        playItem.setAction(new SkyWarsPlayItemAction(serverManager, user, teamType, gameType));
        playItem.setLore("&7Play " + gameDisplayName + " SkyWars!");
        playItem.setLore(" ");
        playItem.setLore("&7" + onlinePlayers + " currently playing");
        playItem.setLore(" ");
        playItem.setLore("&eClick to play!");

        setSlot(slot, playItem);

        MenuItem mapSelectorItem = new MenuItem(Material.SIGN, ChatColor.GREEN + "Map Selector (" + gameDisplayName + ")");
        mapSelectorItem.setAction(new SkyWarsMapSelectorItemAction(gameMenuHandler, user, teamType, gameType));
        mapSelectorItem.setLore("&7Pick which map you want to play");
        mapSelectorItem.setLore("&7from a list of available games.");
        mapSelectorItem.setLore(" ");
        mapSelectorItem.setLore("&eClick to browse!");
        setSlot(slot + 1, mapSelectorItem);
    }

}

@RequiredArgsConstructor
class SkyWarsPlayItemAction implements ActionableItem {

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
class SkyWarsMapSelectorItemAction implements ActionableItem {

    private final GameMenuHandler gameMenuHandler;
    private final User user;
    private final TeamType teamType;
    private final GameType gameType;

    @Override
    public void performAction(MenuItem item, Player clicker, Block clickedBlock, ClickType clickType) {
        gameMenuHandler.openMapSelector(user, teamType, gameType);
    }
}
