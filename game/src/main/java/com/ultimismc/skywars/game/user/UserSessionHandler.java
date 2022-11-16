package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class UserSessionHandler {

    private final Map<UUID, UserGameSession> userSessions = new HashMap<>();

    private final GameServer gameServer;
    public UserSessionHandler(GameServer gameServer) {
        this.gameServer = gameServer;
    }

    public void addUser(User user) {
        UUID uuid = user.getUuid();
        userSessions.put(uuid, new UserGameSession(user, gameServer));
    }

    public UserGameSession getSession(UUID uuid) {
        return userSessions.get(uuid);
    }

    public UserGameSession removeSession(UUID uuid) {
        return userSessions.remove(uuid);
    }

    public UserGameSession getSession(User user) {
        UUID uuid = user.getUuid();
        return getSession(uuid);
    }

    public UserGameSession removeSession(User user) {
        UUID uuid = user.getUuid();
        return removeSession(uuid);
    }

}
