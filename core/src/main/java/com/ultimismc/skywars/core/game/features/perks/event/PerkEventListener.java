package com.ultimismc.skywars.core.game.features.perks.event;

import com.ultimismc.skywars.core.events.UserSkyWarsEvent;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class PerkEventListener implements Listener {

    private final PerkEventHandler eventHandler;

    @EventHandler
    public void onSkyWarsEvent(UserSkyWarsEvent event) {
        User user = event.getUser();
        if(user == null) return;
        eventHandler.triggerPerk(user, event);
    }
}
