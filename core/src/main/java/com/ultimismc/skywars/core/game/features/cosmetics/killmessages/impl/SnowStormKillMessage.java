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
public class SnowStormKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTExNWM3OTY4ZWMzNzcxZWU5ZmY2YWU2YmNhMmQ1YmEzOTYyYWE3MjdhNGZhOGQzNzYwOGU0YzliZjE1MTJiYiJ9fX0=");

    public SnowStormKillMessage() {
        super("Snow Storm", CosmeticRarity.EPIC);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&c&lPOOF!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was locked outside during a snow storm by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e was shoved down an icy slope by" + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e was made into a snowman by " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e was hit with a snowball from " + killer + "&e.");
    }
}
