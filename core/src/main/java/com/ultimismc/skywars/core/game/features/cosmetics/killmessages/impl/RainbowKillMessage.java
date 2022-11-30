package com.ultimismc.skywars.core.game.features.cosmetics.killmessages.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageBundle;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageType;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@Getter
public class RainbowKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign(Material.WOOL, 4);

    public RainbowKillMessage() {
        super("Rainbow", CosmeticRarity.LEGENDARY);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> StringUtil.toRainbow("YOU DIED!"));

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> StringUtil.toRainbow(user + " was killed by " + killer + "."));
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> StringUtil.toRainbow(user + " was thrown into the void by " + killer + "."));
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> StringUtil.toRainbow(user + "&e was thrown off a cliff by " + killer + "."));
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> StringUtil.toRainbow(user + "&e was shot by " + killer + "."));
    }
}
