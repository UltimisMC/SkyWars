package com.ultimismc.skywars.lobby.shop.kitsandperks.kits;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class KitProduct extends UserPurchasableProduct {

    private final Kit kit;
    private final GameType gameType;

    public KitProduct(Kit kit, GameType gameType) {
        super(0, kit, gameType);
        this.kit = kit;
        this.gameType = gameType;
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        List<String> lore = new ArrayList<>(kit.getDisplayItems(gameType)); // <- Kit display items

        PurchasableRarity rarity = kit.getRarity();
        lore.add(" ");
        lore.add("&7Rarity: " + rarity.getDisplayName());

        PurchasableDesign kitDesign = kit.getKitDesign(gameType);
        // "&eClick here to preview!"
        ProductItemDesign productItemDesign = new ProductItemDesign(kitDesign.getMaterial(), (short) kitDesign.getDurability(), lore);
        productItemDesign.setSkullTexture(kitDesign.getTexture());
//        productItemDesign.setPurchasedStatus("&eClick here to preview!");
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user, GameType gameType, ClickType clickType) {}
}
