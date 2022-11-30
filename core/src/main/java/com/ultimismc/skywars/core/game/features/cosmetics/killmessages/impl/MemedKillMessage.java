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
public class MemedKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAyZTZiMDljNzE0YzliOTNhMGY2Y2JiZDZjMTM0YTJlYTc1OTYyM2ZkMTgyNjY4MzEzMjUxYTkyNjM3YWFlZSJ9fX0=");

    public MemedKillMessage() {
        super("Memed", CosmeticRarity.EPIC);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&c&lGET REKT!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e got rekt by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e took the L to " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e got dabbed on by " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e got bamboozled by " + killer + "&e.");
    }
}
