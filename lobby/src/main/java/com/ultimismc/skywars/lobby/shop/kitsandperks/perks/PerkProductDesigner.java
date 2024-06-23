package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PerkProductDesigner {

    public static ProductItemDesign designProduct(Perk perk, User user) {
        PurchasableDesign purchasableDesign = perk.getDesign();
        Material material = purchasableDesign.getMaterial();
        int durability = purchasableDesign.getDurability();
        List<String> description = perk.getDescription();
        PurchasableRarity rarity = perk.getRarity();

        List<String> lore = new ArrayList<>(description);
        lore.add(" ");
        if(rarity != null && !perk.isSoulWellPerk()) {
            lore.add("&7Rarity: " + rarity.getDisplayName());
            lore.add(" ");
        }
        ProductItemDesign productItemDesign = new ProductItemDesign(material, (short) durability, lore);
        productItemDesign.setSkullTexture(purchasableDesign.getTexture());
        return productItemDesign;
    }
}
