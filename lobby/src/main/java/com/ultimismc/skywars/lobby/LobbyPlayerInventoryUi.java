package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.lobby.user.User;
import com.ultimismc.skywars.lobby.user.UserPlayerInventoryUi;
import com.ultimismc.skywars.lobby.user.UserStatistics;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.List;

/**
 * @author DirectPlan
 */
public class LobbyPlayerInventoryUi extends UserPlayerInventoryUi {

    public LobbyPlayerInventoryUi(User user) {
        super(user);
    }

    @Override
    public void build(Player player) {

        String displayName = MessageConfigKeys.SKYWARS_LOBBY_SHOP_ITEM_DISPLAY_NAME.getStringValue();
        MenuItem menuItem = new MenuItem(Material.EMERALD, displayName);

        UserStatistics userStatistics = user.getStatistics();
        List<String> lore = MessageConfigKeys.SKYWARS_LOBBY_SHOP_ITEM_LORE.getStringList(new Replacement("coins", userStatistics.getCoins()));
        menuItem.setLore(lore);

    }
}
