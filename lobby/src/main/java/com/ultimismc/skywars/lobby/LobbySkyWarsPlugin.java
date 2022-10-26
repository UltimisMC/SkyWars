package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.lobby.commands.ShopCommand;
import com.ultimismc.skywars.lobby.commands.UltimisSkyWarsCommand;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.user.LobbyUserLoadedListener;
import com.ultimismc.skywars.lobby.user.LobbyUserSavedListener;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class LobbySkyWarsPlugin extends SkyWarsPlugin {


    private LobbyManager lobbyManager;

    @Override
    public void enable() {
        configHandler.loadConfiguration("messages.yml", MessageConfigKeys.class);
        configHandler.loadConfiguration("shop-messages.yml", ShopMessageKeys.class);

        lobbyManager = new LobbyManager(this);

        lobbyManager.initializeLobby();

        commandHandler.registerDependency(LobbyManager.class, lobbyManager);
        commandHandler.registerCommands(new UltimisSkyWarsCommand(), new ShopCommand());

        userListener.setUserLoadedListener(new LobbyUserLoadedListener(lobbyManager));
        userListener.setUserSavedListener(new LobbyUserSavedListener(lobbyManager));

    }


    @Override
    public void disable() {

    }
}
