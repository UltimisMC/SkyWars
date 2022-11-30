package com.ultimismc.skywars.core.game.features.cosmetics.killmessages.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageBundle;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageType;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class NoKillerKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign(Material.AIR);

    public NoKillerKillMessage() {
        super("N/A Killer", CosmeticRarity.RARE);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&c&lYOU DIED!");
        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e died.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e fell into the void.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e fell to their death.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e died.");
    }
}
