package com.ultimismc.skywars.core.rank;

import com.ultimismc.skywars.core.hook.RankHookProvider;
import com.ultimismc.skywars.core.hook.rank.LuckPermsHookProvider;
import com.ultimismc.skywars.core.rank.impl.*;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;

import java.util.*;

/**
 * @author DirectPlan
 */
public class RankManager {

    private final Map<String, Rank> ranks = new HashMap<>();
    private final List<Rank> sortedRanks = new ArrayList<>();

    @Getter private final Rank defaultRank;
    private final RankHookProvider hookProvider;

    public RankManager() {
        hookProvider = new LuckPermsHookProvider();

        registerRanks(defaultRank = new DefaultRank(), new VipRank(), new EliteRank(), new SupremeRank(), new MythRank(), new UltimisRank());
    }

    public void setupUserRank(User user) {
        String group = hookProvider.getGroup(user);
        Rank rank = getRank(group);
        if(rank == null) {
            // Player is not listed there! Comparing ranks weight with player weight...
            rank = defaultRank;
            Collection<Rank> values = ranks.values();
            int priority = hookProvider.getPlayerPriority(user.getPlayer());
            for(Rank r : values) {
                // Had a brain malfunction thinking about this condition...
                int iteratedRankPriority = r.getPriority();
                int rankPriority = rank.getPriority();
                if(iteratedRankPriority > rankPriority && priority >= iteratedRankPriority) {
                    rank = r;
                }
            }
        }
        // Checking if the rank is still not found, then set default.
        user.setRank(rank);
    }

    public final Rank getRank(String name) {
        return ranks.get(name.toLowerCase(Locale.ROOT));
    }

    public void registerRanks(Rank... ranks) {
        Arrays.asList(ranks).forEach(this::registerRank);
    }

    public void registerRank(Rank rank) {
        String name = rank.getName();
        name = name.toLowerCase(Locale.ROOT);
        int priority = hookProvider.getGroupPriority(name);
        if(priority != -1) {
            rank.setPriority(priority);
        }
        ranks.put(name, rank);
        sortedRanks.add(rank);
    }

    public List<Rank> getRanks() {
        return sortedRanks;
    }
}
