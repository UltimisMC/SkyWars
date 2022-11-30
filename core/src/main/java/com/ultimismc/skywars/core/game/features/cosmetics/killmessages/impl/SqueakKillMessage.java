package com.ultimismc.skywars.core.game.features.cosmetics.killmessages.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageBundle;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageType;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class SqueakKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjU4MWQ4MjhjYjM3MGJhYzExZWE0YTUwYmY3MmU1YjRkZjRiMDI1YzY2MjFlZmIwN2Y5NjRjYWRlZDI2OTdiOCJ9fX0=");

    public SqueakKillMessage() {
        super("Squeak", CosmeticRarity.RARE);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&e&lSQUEAK!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was chewed up by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e was squeaked off the edge by " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e was distracted by a rat dragging pizza from " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e was squeaked from a distance by " + killer + "&e.");
    }
}
