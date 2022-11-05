package com.ultimismc.skywars.core.hook;

import com.ultimismc.skywars.core.user.User;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public interface RankHookProvider {

    String getName();

    String getGroup(Player player);

    int getGroupPriority(String groupName);

    default String getGroup(User user) {
        return getGroup(user.getPlayer());
    };

    default int getPlayerPriority(Player player) {
        String group = getGroup(player);
        return getGroupPriority(group);
    }
}
