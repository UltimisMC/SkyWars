package com.ultimismc.skywars.api;

/**
 * @author DirectPlan
 */
public class SkyWarsApi {

    private static SkyWars api;

    public static SkyWars getApi() {
        return api;
    }

    public static void setApi(SkyWars skyWars) {
        api = skyWars;
    }
}
