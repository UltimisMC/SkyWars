package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;

/**
 * @author DirectPlan
 */
public class RequiredProgressProcessor extends UserPlaceholderProcessor {

    public RequiredProgressProcessor(UserManager userManager) {
        super(userManager);
    }

    @Override
    public String process(User user, String value) {
        int nextRequiredExp = user.getNextRequiredExp();
        if(nextRequiredExp == -1) {
            return "MAXED";
        }
        return String.valueOf(nextRequiredExp);
    }
}
