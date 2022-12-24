package com.ultimismc.gamescaler.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DirectPlan
 */
public class ScalerTest {

    private final String name = "DirectPlan";
    private final int age = 36;

    private final ScalerTestMetadata metadata = new ScalerTestMetadata();
    private final List<String> statuses = Arrays.asList("Programmer", "Abderrahim", "OK");

    private final Map<String, Object> settings = new HashMap<>();

    public ScalerTest() {
        settings.put("kit", "Armorer");
        settings.put("spectator", true);
        settings.put("kill-message", "Rainbow Kill Message");
    }

    @Override
    public String toString() {
        return "ScalerTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", metadata=" + metadata +
                ", statuses=" + statuses +
                ", settings=" + settings +
                '}';
    }
}
