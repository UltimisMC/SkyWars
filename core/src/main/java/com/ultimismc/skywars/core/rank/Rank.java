package com.ultimismc.skywars.core.rank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Data
@Getter
public abstract class Rank {

    private final String name;
    private final String color;

    @Setter private int priority;
    private boolean defaultRank = false;

    public String getDisplayName() {
        return color + name;
    }

}
