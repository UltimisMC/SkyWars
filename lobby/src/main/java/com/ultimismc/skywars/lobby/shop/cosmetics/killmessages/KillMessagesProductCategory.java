package com.ultimismc.skywars.lobby.shop.cosmetics.killmessages;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.killmessages.KillMessageHandler;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.AbstractCosmeticCategory;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class KillMessagesProductCategory extends AbstractCosmeticCategory<KillMessageHandler> {

    private final PurchasableDesign design = new PurchasableDesign(Material.SIGN);

    public KillMessagesProductCategory(KillMessageHandler cosmeticRegistry, int itemSlot) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_KILL_MESSAGES_LORE.getStringList(), itemSlot);
    }
}
