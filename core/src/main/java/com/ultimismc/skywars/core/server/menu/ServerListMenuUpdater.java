package com.ultimismc.skywars.core.server.menu;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ServerListMenuUpdater implements Runnable {

    private final Player player;
    private final ServerListMenu listMenu;

    @Override
    public void run() {
        listMenu.refresh(player);
    }
}
