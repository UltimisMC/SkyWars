package com.ultimismc.skywars.core.game;

import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public enum TeamType {

    SOLO("Solo", 12, 1),
    DOUBLES("Doubles", 24,2),
    ;

    private final String name;
    private final int maximumPlayers, maximumTeam;

    TeamType(String name, int maximumPlayers, int maximumTeam) {
        this.name = name;
        this.maximumPlayers = maximumPlayers;
        this.maximumTeam = maximumTeam;
    }

    public boolean isSolo() {
        return maximumTeam <= 1;
    }
}
