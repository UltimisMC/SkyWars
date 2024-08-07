package xyz.directplan.directlib.placeholder;

import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public interface PlaceholderProcessor {

    Object process(Player player, String value);
}
