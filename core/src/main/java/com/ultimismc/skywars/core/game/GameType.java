package com.ultimismc.skywars.core.game;

import lombok.Getter;

/**
 * @author DirectPlan
 */
public enum GameType {

    NORMAL("Normal"),
    INSANE("Insane"),
    ;

    @Getter private final String name;

    GameType(String name) {
        this.name = name;
    }

}
