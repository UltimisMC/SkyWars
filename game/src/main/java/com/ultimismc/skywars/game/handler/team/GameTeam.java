package com.ultimismc.skywars.game.handler.team;

import com.ultimismc.skywars.game.user.UserGameSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class GameTeam implements Iterable<UserGameSession> {

    private final String tagGroup;
    private final int maximumTeam;
    private final Map<UUID, UserGameSession> players = new HashMap<>();

    public void addPlayer(UserGameSession userGameSession) {
        players.put(userGameSession.getUuid(), userGameSession);
    }

    public void removePlayer(UserGameSession userGameSession) {
        players.remove(userGameSession.getUuid());
    }

    public boolean isMember(UserGameSession userGameSession) {
        return players.containsKey(userGameSession.getUuid());
    }

    public boolean isFull() {
        return players.size() >= maximumTeam;
    }

    public boolean isAlive() {
        return players.values()
                .stream()
                .anyMatch(userGameSession -> !userGameSession.isSpectator());
    }

    public int getCombinedKills() {
        int combinedKills = 0;
        for(UserGameSession user : players.values()) {
            combinedKills += user.getKills();
        }
        return combinedKills;
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    @NotNull
    @Override
    public Iterator<UserGameSession> iterator() {
        return players.values().iterator();
    }
}
