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
public class BBQKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRiM2JkMDhmZTVmNWM5NTMxMzg4ZWQ0ZThmMGI4Mjg2MWNhOWY5YmZjOGRjNGM4NGQwZDU1MDJiMzAwNzI5ZSJ9fX0=");

    public BBQKillMessage() {
        super("BQQ", CosmeticRarity.RARE);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> "&c&lYOU'RE BBQed!");

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e was glazed in BBQ sauce by " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e slipped in BBQ sauce off the edge spilled by " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e was not spicy enough for " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e was thrown chili powder at by " + killer + "&e.");
    }
}
