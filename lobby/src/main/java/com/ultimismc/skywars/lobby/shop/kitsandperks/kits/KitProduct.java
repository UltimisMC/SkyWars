package com.ultimismc.skywars.lobby.shop.kitsandperks.kits;

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

    public KitProduct(Kit kit) {
        super(0, kit);
        this.kit = kit;
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        List<String> lore = new ArrayList<>(kit.getDisplayItems()); // <- Kit display items

        PurchasableRarity rarity = kit.getRarity();
        lore.add(" ");
        lore.add("&7Rarity: " + rarity.getDisplayName());

        PurchasableDesign design = kit.getDesign();
        // "&eClick here to preview!"
        ProductItemDesign productItemDesign = new ProductItemDesign(design.getMaterial(), (short) design.getDurability(), lore);
        productItemDesign.setSkullTexture(design.getTexture());
        productItemDesign.setPurchasedStatus("&eClick here to preview!");
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user, ClickType clickType) {
        Player player = user.getPlayer();
        user.sendMessage("&aPreviewing &e" + kit.getName() + " &akit...");

    }
}
