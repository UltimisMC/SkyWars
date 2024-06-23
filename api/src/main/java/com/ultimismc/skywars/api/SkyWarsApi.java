package com.ultimismc.skywars.api;

/**
 * @author DirectPlan
 */
@Deprecated
public class SkyWarsApi {

    private static SkyWars api;

    public static SkyWars getApi() {
        return api;
    }

    public static void setApi(SkyWars skyWars) {
        api = skyWars;
    }
}
