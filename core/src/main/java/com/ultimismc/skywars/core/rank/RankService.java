package com.ultimismc.skywars.core.rank;

import com.google.common.collect.ImmutableList;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public interface RankService {

    String getName();

    String getRank(Player player);

    int getRankPriority(String groupName);

    ImmutableList<Rank> getRanks();

    default String getRank(User user) {
        return getRank(user.getPlayer());
    };
}
