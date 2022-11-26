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
public class BananaKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZGFlNTFmNTc2YjIzYTZmMGQyNjk1ZWQxZmQ1NzM5ODEyYzNiYTgzMjUyZjBjODc2ODQ0ZDI4YTEzYyJ9fX0=");

    public BananaKillMessage() {
        super("Banana", CosmeticRarity.RARE);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> user + "&c&lYOU DIED!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was mushed by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e was peeled by " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e slipped on " + killer + "&e's banana peel off a cliff.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e got banana pistol'd by " + killer + "&e.");
    }
}
