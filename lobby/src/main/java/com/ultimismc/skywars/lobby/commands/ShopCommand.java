package com.ultimismc.skywars.lobby.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.LobbyManager;

/**
 * @author DirectPlan
 */
@CommandAlias("shop")
public class ShopCommand extends BaseCommand {

    @Dependency
    private LobbyManager lobbyManager;

    @Default
    public void onShop(User user) {
        lobbyManager.openShopMenu(user);
    }
}
