package com.ultimismc.skywars.game.combat;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCryHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.killeffects.KillEffectHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.combat.AttackCause;
import xyz.directplan.directlib.combat.CombatAdapter;

import java.util.List;

/**
 * @author DirectPlan
 */
public class SkyWarsCombatAdapter implements CombatAdapter<UserGameSession> {

    private final GameHandler gameHandler;
    private final FeatureHandler featureHandler;

    public SkyWarsCombatAdapter(GameHandler gameHandler, FeatureHandler featureHandler) {
        this.gameHandler = gameHandler;
        this.featureHandler = featureHandler;
    }

    @Override
    public boolean onAttack(UserGameSession userGameSession, UserGameSession attackerGameSession, AttackCause attackCause) {
        User user = userGameSession.getUser();
        User attacker = (attackerGameSession != null ? attackerGameSession.getUser() : null);

        if(!gameHandler.hasStarted()) return true;
        if(!gameHandler.hasTimePassed(5)) return true;

        if(userGameSession.isSetupMode()) return true;
        if(userGameSession.isSpectator()) {
            if(attackCause.isVoid()) {
                userGameSession.teleportToIsland();
            }
            return true;
        }
        if(attacker != null && attackCause.isBow()) {
            Player player = user.getPlayer();
            double health = player.getHealth();
            attacker.sendMessage(user.getDisplayName() + "&e is on &c" + PluginUtility.formatDoubleDecimal(health) + "&e HP!");
        }
        return false;
    }

    @Override
    public void onDeath(UserGameSession userGameSession, UserGameSession killerGameSession, List<ItemStack> drops, AttackCause attackCause) {
        User user = userGameSession.getUser();
        User killer = (killerGameSession != null ? killerGameSession.getUser() : null);
        Player player = user.getPlayer();

        CosmeticManager cosmeticManager = featureHandler.getCosmeticManager();
        KillMessageHandler killMessageHandler = cosmeticManager.getKillMessageHandler();
        DeathCryHandler deathCryHandler = cosmeticManager.getDeathCryHandler();
        KillEffectHandler killEffectHandler = cosmeticManager.getKillEffectHandler();

        killMessageHandler.triggerKillMessage(attackCause, user, killer);
        killEffectHandler.playKillEffect(user, killer);
        deathCryHandler.playDeathCry(user);

        gameHandler.terminateUser(user);
        if(killer == null) return;
        Player killerPlayer = killer.getPlayer();

        if(attackCause.isProjectile()) {
            double shootDistance = killerPlayer.getLocation().distanceSquared(player.getLocation());
            if(shootDistance >= 30.0) {
                PluginUtility.translateMessage(user.getDisplayName() + "&e got shot from " + PluginUtility.formatDoubleDecimal(shootDistance) + " blocks!");
            }
        }
    }

    @Override
    public void onAssist(UserGameSession user, UserGameSession assistant, AttackCause attackCause) {
        assistant.sendMessage("&eYou have assisted killing " + user.getDisplayName() + "&e!");
    }
}
