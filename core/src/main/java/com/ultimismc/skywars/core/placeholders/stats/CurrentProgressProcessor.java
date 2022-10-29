package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.PlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public class CurrentProgressProcessor implements PlaceholderProcessor {

    @Override
    public String process(User user, String value) {
        return "???";
    }
}
