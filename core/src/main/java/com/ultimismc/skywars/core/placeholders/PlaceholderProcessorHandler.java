package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class PlaceholderProcessorHandler {

    private final Map<String, PlaceholderProcessor> processors = new HashMap<>();

    public void registerProcessor(String name, PlaceholderProcessor processor) {
        processors.put(name, processor);
    }

    public PlaceholderProcessor getProcessor(String name) {
        return processors.get(name);
    }

    public String process(String name, User user, String value) {
        PlaceholderProcessor processor = getProcessor(name);
        if(processor == null) {
            return "Invalid processor '" + name+"'";
        }
        return processor.process(user, value);
    }
}
