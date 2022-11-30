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
public class WOOFKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEwZDA1ODZjNjlhZDljNDIxMTJmYWMyODhhNTAyNzI3YzlkNTljOTU5MWQyMzk5YTg1MWViNjQ0Nzc0NjJjNyJ9fX0=");

    public WOOFKillMessage() {
        super("WOOF", CosmeticRarity.EPIC);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&c&lWOOF!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was bit by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e got WOOF'd by " + killer + "&e into the void.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e was growled off an edge by " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e was thrown a frisbee by " + killer + "&e.");
    }
}
