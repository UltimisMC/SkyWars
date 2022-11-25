package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 *
 * Maybe change the name, it doesn't look idea
 */
public interface MessageTranslator {

    String translateMessage(User user, User killer);
}
