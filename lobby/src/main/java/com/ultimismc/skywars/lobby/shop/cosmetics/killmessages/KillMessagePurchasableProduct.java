package com.ultimismc.skywars.lobby.shop.cosmetics.killmessages;

import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessage;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageBundle;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageResolver;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.MessageType;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.cosmetics.CosmeticPurchasableProduct;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

import java.util.Map;

/**
 * @author DirectPlan
 */
public class KillMessagePurchasableProduct extends CosmeticPurchasableProduct {

    public KillMessagePurchasableProduct(PurchasableRegistry<?> registry, Cosmetic cosmetic) {
        super(registry, cosmetic);
    }

    @Override
    public void onRightClick(User user) {
        KillMessage killMessage = (KillMessage) cosmetic;

        Player player = user.getPlayer();
        PluginUtility.playSound(player, Sound.SUCCESSFUL_HIT);
        player.closeInventory();
        // We need to copy the output of this, so we can add it to config or something

        String repeatLine = StringUtils.repeat("▬", 70);
        KillMessageBundle killMessageBundle = killMessage.getMessageBundle();
        user.sendMessage(ChatColor.GREEN + repeatLine);
        user.sendMessage(" ");
        user.sendMessage("                             &e&l&nChat Messages:");
        user.sendMessage(" ");

        String userDisplayName = user.getDisplayName();
        String victimActor = "&7Player";

        for(Map.Entry<MessageType, MessageResolver> messageEntry : killMessageBundle.getMessageResolvers().entrySet()) {
            MessageType messageType = messageEntry.getKey();
            if(messageType == MessageType.SCREEN) continue;
            MessageResolver messageResolver = messageEntry.getValue();
            String message = messageResolver.resolveMessage(victimActor, userDisplayName, null);
            user.sendMessage(message);
        }
        MessageResolver screenResolver = killMessageBundle.getResolver(MessageType.SCREEN);
        String screenMessage = screenResolver.resolveMessage(victimActor, userDisplayName, null);
        user.sendMessage("&eAppears on victim screen: " + screenMessage);
        user.sendMessage(" ");
        user.sendMessage(ChatColor.GREEN + repeatLine);
    }
}
