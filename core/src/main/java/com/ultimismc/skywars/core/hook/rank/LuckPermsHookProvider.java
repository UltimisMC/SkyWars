package com.ultimismc.skywars.core.hook.rank;

import com.ultimismc.skywars.core.hook.RankHookProvider;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

import java.util.OptionalInt;

/**
 * @author DirectPlan
 */
public class LuckPermsHookProvider implements RankHookProvider {

    private final LuckPerms api = LuckPermsProvider.get();

    @Override
    public String getName() {
        return "LuckPerms";
    }

    @Override
    public String getGroup(Player player) {
        User user = api.getUserManager().getUser(player.getUniqueId());
        if(user == null) {
            System.out.println("A flaw has happened in the LuckPerms API");
            return "Default";
        }
        return user.getPrimaryGroup();
    }

    @Override
    public int getGroupPriority(String groupName) {
        GroupManager groupManager = api.getGroupManager();
        Group group = groupManager.getGroup(groupName);
        if(group != null) {
            OptionalInt optionalInt = group.getWeight();
            if(!optionalInt.isPresent()) {
                return -1;
            }
            return optionalInt.getAsInt();
        }
        return -1;
    }
}
