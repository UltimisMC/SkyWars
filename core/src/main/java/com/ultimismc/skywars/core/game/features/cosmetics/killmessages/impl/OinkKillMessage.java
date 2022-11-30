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
public class OinkKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign(Material.CARROT_STICK);

    public OinkKillMessage() {
        super("Oink", CosmeticRarity.RARE);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&d&lOINK");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was oinked by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e slipped into void for " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e was distracted by a piglet from " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e got attacked by a carrot from " + killer + "&e.");
    }
}
