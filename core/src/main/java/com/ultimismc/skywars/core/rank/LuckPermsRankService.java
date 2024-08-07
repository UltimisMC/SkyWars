package com.ultimismc.skywars.core.rank;

import com.google.common.collect.ImmutableList;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author DirectPlan
 */
public class LuckPermsRankService implements RankService {

    private final LuckPerms api = LuckPermsProvider.get();

    @Override
    public String getName() {
        return "LuckPerms";
    }

    @Override
    public String getRank(Player player) {
        User user = api.getUserManager().getUser(player.getUniqueId());
        if(user == null) throw new RuntimeException("An error occurred whilst retrieving LuckPerms User");

        return user.getPrimaryGroup();
    }

    @Override
    public int getRankPriority(String groupName) {
        int unknownPriority = -1;

        GroupManager groupManager = api.getGroupManager();
        Group group = groupManager.getGroup(groupName);
        if(group != null) {
            return group.getWeight()
                    .orElse(unknownPriority);
        }
        return unknownPriority;
    }

    @Override
    public ImmutableList<Rank> getRanks() {
        GroupManager groupManager = api.getGroupManager();
        List<Rank> ranks = new ArrayList<>();
        for (Group group : groupManager.getLoadedGroups()) {
            String name = group.getName();
            String prefix = group.getCachedData()
                    .getMetaData()
                    .getPrefix();
            String color = Optional.ofNullable(prefix)
                    .map(PluginUtility::getColors)
                    .orElse(ChatColor.GRAY.toString());
            int weight = group.getWeight().orElse(-1);
            Rank rank = Rank.create(name, color, weight);
            ranks.add(rank);
        }
        return ImmutableList.copyOf(ranks);
    }
}
