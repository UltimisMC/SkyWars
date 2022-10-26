package com.ultimismc.skywars.core.game;

import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public enum TeamType {


    SOLO("Solo", 1),
    DOUBLES("Solo",2),
    ;

    private final String name;
    private final int maximumTeam;

    TeamType(String name, int maximumTeam) {
        this.name = name;
        this.maximumTeam = maximumTeam;
    }

    public boolean isSolo() {
        return maximumTeam <= 1;
    }
}
