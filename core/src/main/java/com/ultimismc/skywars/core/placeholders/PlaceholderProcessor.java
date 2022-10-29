package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.user.User;

/**
 * @author DirectPlan
 */
public interface PlaceholderProcessor {


    String process(User user, String value);


}
