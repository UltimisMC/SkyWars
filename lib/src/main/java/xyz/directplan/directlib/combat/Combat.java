package xyz.directplan.directlib.combat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class Combat {

    private final Player player;
    private final long attackedAt;

    public Combat(Player player) {
        this(player, System.currentTimeMillis() + 10000L);
    }

    public boolean hasExpired() {
        return (System.currentTimeMillis() - attackedAt) > 0;
    }
}
