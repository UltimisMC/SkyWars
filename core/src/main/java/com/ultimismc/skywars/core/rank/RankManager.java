package com.ultimismc.skywars.core.rank;

import com.ultimismc.skywars.core.user.User;

import java.util.*;

/**
 * @author DirectPlan
 */
public class RankManager {

    private final Map<String, Rank> ranks = new HashMap<>();

    private final LuckPermsRankService rankService = new LuckPermsRankService();

    public RankManager() {
        rankService.getRanks().forEach(this::registerRank);
    }

    public void setupUserRank(User user) {
        String rankName = rankService.getRank(user);
        Rank rank = getRank(rankName);
        user.setRank(rank);
    }

    public final Rank getRank(String name) {
        return ranks.get(name.toLowerCase(Locale.ROOT));
    }

    public void registerRank(Rank rank) {
        String name = rank.getName();
        name = name.toLowerCase(Locale.ROOT);
        ranks.put(name, rank);
    }
}
