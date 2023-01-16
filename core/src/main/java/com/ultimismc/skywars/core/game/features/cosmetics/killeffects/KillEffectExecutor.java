package com.ultimismc.skywars.core.game.features.cosmetics.killeffects;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public abstract class KillEffectExecutor extends BukkitRunnable {

    protected final User user, killer;
    private final int frequency;
    private final int periodTicks;

    public KillEffectExecutor(User user, User killer, int frequency) {
        this(user, killer, frequency, 20);
    }

    private int tick;

    @Override
    public void run() {
        animateEffect(tick);

        if(tick > (100)) cancel(); // Exceeded the time limit
        tick++;
//        tick += (periodTicks / frequency);
    }

    public abstract void animateEffect(int tick);
}
