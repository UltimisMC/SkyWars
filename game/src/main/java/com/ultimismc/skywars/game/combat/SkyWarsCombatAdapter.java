package com.ultimismc.skywars.game.combat;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.directplan.directlib.combat.CombatAdapter;

/**
 * @author DirectPlan
 */
public class SkyWarsCombatAdapter implements CombatAdapter<User> {

    private final GameHandler gameHandler;
    private final FeatureHandler featureHandler;

    public SkyWarsCombatAdapter(GameHandler gameHandler, FeatureHandler featureHandler) {
        this.gameHandler = gameHandler;
        this.featureHandler = featureHandler;
    }

    @Override
    public boolean onAttack(Player player, Player attacker) {
        player.sendMessage("You were attacked!");
        return false;
    }

    @Override
    public void onDeath(User user, User killer, EntityDamageEvent.DamageCause damageCause) {
        user.sendMessage("&eYou died brother!");

        CosmeticManager cosmeticManager = featureHandler.getCosmeticManager();
        KillMessageHandler killMessageHandler = cosmeticManager.getKillMessageHandler();

        KillMessage killMessage = killMessageHandler.getDefaultPurchasable();
        if(killer != null) {
            killMessage = killer.getSetting(KillMessage.class, "killmessage");
        }
        killMessage.triggerKillMessage(damageCause, user, killer);
    }

    @Override
    public void onAssist(User user, User assistant, EntityDamageEvent.DamageCause damageCause) {

    }
}
