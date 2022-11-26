package com.ultimismc.skywars.core.game.features.cosmetics.killmessages.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageBundle;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageType;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@Getter
public class FlexKillMessage extends KillMessage {

    private final PurchasableDesign design = new PurchasableDesign(Material.GOLDEN_APPLE);

    public FlexKillMessage() {
        super("Flex", CosmeticRarity.COMMON);
    }

    @Override
    public void buildBundle(KillMessageBundle messageBundle) {
        messageBundle.addResolver(MessageType.SCREEN, (user, killer, statistics) -> user + "&c&lDEATH #" + totalKills(statistics));

        messageBundle.addResolver(MessageType.KILL, (user, killer, statistics) -> user + "&e became victim #" + totalKills(statistics) + " of " + killer + "&e.");
        messageBundle.addResolver(MessageType.VOID, (user, killer, statistics) -> user + "&e was void victim #" + totalKills(statistics) +" of " + killer + "&e.");
        messageBundle.addResolver(MessageType.FALL_DAMAGE, (user, killer, statistics) -> user + "&e became victim #" + totalKills(statistics) +" of " + killer + "&e.");
        messageBundle.addResolver(MessageType.BOW, (user, killer, statistics) -> user + "&e was bow kill #" + totalKills(statistics) + " by " + killer + "&e.");
    }

    private String totalKills(UserStatistics statistics) {
        return StringUtil.getReadableNumber(statistics.getTotalKills());
    }
}
