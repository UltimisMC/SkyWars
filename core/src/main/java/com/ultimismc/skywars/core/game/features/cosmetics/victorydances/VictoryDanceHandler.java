package com.ultimismc.skywars.core.game.features.cosmetics.victorydances;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.impl.FireworksVictoryDance;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * @author DirectPlan
 */
@Getter
public class VictoryDanceHandler extends PurchasableRegistry<VictoryDance> {

    private final String name = "Victory Dances";

    private final SkyWarsPlugin plugin;

    public VictoryDanceHandler(SkyWarsPlugin plugin) {
        super("victorydance");
        this.plugin = plugin;
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {
        registerPurchasable(new FireworksVictoryDance());

        super.initializeFeature(plugin);
    }

    public void playVictoryDance(User user, VictoryDance victoryDance) {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        int interval = victoryDance.getIntervalTicks();
        scheduler.runTaskTimer(plugin, new VictoryDanceRunnable(user, victoryDance), 0, interval);
    }

    public void playVictoryDance(User user) {
        VictoryDance victoryDance = user.getSetting(VictoryDance.class, settingKey);
        playVictoryDance(user, victoryDance);
    }
}
