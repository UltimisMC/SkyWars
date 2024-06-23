package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public class RequiredProgressProcessor implements UserPlaceholderProcessor {

    @Override
    public String process(User user, String value) {
        int nextRequiredExp = user.getNextRequiredExp();
        if(nextRequiredExp == -1) {
            return "MAXED";
        }
        return String.valueOf(nextRequiredExp);
    }
}
