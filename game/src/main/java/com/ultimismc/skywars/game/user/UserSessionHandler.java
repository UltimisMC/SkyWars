package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class UserSessionHandler {

    private final Map<UUID, UserGameSession> userSessions = new HashMap<>();

    private final GameConfig gameConfig;
    public UserSessionHandler(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    public UserGameSession addUser(User user) {
        UUID uuid = user.getUuid();
        UserGameSession userGameSession = new UserGameSession(user, gameConfig);
        userSessions.put(uuid, userGameSession);
        return userGameSession;
    }

    public UserGameSession getSession(UUID uuid) {
        return userSessions.get(uuid);
    }

    public UserGameSession getSession(Player player) {
        return getSession(player.getUniqueId());
    }

    public UserGameSession getSession(User user) {
        UUID uuid = user.getUuid();
        return getSession(uuid);
    }

    public UserGameSession removeSession(UUID uuid) {
        return userSessions.remove(uuid);
    }

    public UserGameSession removeSession(User user) {
        UUID uuid = user.getUuid();
        return removeSession(uuid);
    }

    public Collection<UserGameSession> getUserSessions() {
        return userSessions.values();
    }
}
