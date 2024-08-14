package xyz.directplan.directlib.combat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class Combat {

    private final Player player;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combat combat = (Combat) o;
        return player.equals(combat.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
