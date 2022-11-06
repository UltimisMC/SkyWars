package com.ultimismc.skywars.core.rank.impl;

import com.ultimismc.skywars.core.rank.Rank;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class DefaultRank extends Rank {

    public DefaultRank() {
        super("Default", "&7");

        setDefaultRank(true);
    }
}
