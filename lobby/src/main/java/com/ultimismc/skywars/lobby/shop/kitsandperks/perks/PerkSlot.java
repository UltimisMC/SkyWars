package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import com.ultimismc.skywars.lobby.shop.UserTypedProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DirectPlan
 */
public class PerkSlot extends UserTypedProduct<UserAsset> {

    private final UserProductCategory purchaseCategory;
    private final int perkSlot;

    public PerkSlot(UserProductCategory purchaseCategory, int perkSlot, int itemSlot) {
        super("Perk Slot", itemSlot, UserAsset.class);
        this.purchaseCategory = purchaseCategory;
        this.perkSlot = perkSlot;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        List<UserAsset> userAssets = user.getAssets(Perk.class).stream().filter(UserAsset::isActivated).collect(Collectors.toList());
        if(userAssets.size() > perkSlot) {
            UserAsset userAsset = userAssets.get(perkSlot);
            Perk perk = (Perk) userAsset.getPurchasable();
            PerkProduct perkProduct = new PerkProduct(perk);
            ProductItemDesign productItemDesign = perkProduct.designProduct(user);
            productItemDesign.setData(userAsset);
            return productItemDesign;
        }
        List<String> lore = Arrays.asList("&eClick here to view your",
                "&aPurchased Perks &ein the chest",
                "&eicon below to equip perks in",
                "&ethis slot!");
        String displayName = "&cPerk Slot #" + perkSlot + " - " + "Empty";


        ProductItemDesign productItemDesign = new ProductItemDesign(Material.STAINED_GLASS_PANE, (short) 14, ChatColor.RED, lore, true);
        productItemDesign.setDisplayName(displayName);

        setProductPath(purchaseCategory);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, UserAsset asset, ClickType clickType) {
        asset.toggleAsset();
    }
}
