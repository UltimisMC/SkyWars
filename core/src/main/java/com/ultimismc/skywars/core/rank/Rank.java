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

    public String getDisplayName() {
        return color + name;
    }

    static Rank create(String name, String color, int priority) {
        return new RankWrapper(name, color, priority);
    }

}
class RankWrapper extends Rank {

    public RankWrapper(String name, String color, int priority) {
        super(name, color);
        setPriority(priority);
    }
}
