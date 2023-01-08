package com.ultimismc.skywars.core.game.features.perks.event;

import com.ultimismc.skywars.core.events.UserSkyWarsEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;

/**
 * @author DirectPlan
 */
public interface PerkEvent<T extends UserSkyWarsEvent> {

    Class<T> getEventClass();

    void onTrigger(User user, UserAsset asset, T event);
}
