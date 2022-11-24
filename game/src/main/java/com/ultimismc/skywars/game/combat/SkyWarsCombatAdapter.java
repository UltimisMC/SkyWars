package com.ultimismc.skywars.game.combat;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.directplan.directlib.combat.CombatAdapter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SkyWarsCombatAdapter implements CombatAdapter<User> {

    private final GameHandler gameHandler;

    @Override
    public boolean onAttack(Player player, Player attacker) {
        player.sendMessage("You were attacked!");
        return false;
    }

    @Override
    public boolean onDeath(User user, User killer, EntityDamageEvent.DamageCause damageCause) {
        return false;
    }

    @Override
    public void onAssist(User user, User assistant, EntityDamageEvent.DamageCause damageCause) {

    }
}
