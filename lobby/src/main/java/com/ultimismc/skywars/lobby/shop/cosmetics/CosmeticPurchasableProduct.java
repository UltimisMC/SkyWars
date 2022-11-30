package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author DirectPlan
 */
public class CosmeticPurchasableProduct extends UserPurchasableProduct {

    protected final Cosmetic cosmetic;
    protected final boolean rightclickable;

    public CosmeticPurchasableProduct(Cosmetic cosmetic, boolean rightclickable) {
        super(0, cosmetic);
        this.cosmetic = cosmetic;
        this.rightclickable = rightclickable;
    }

    public CosmeticPurchasableProduct(Cosmetic cosmetic) {
        this(cosmetic, true);
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        PurchasableRarity rarity = cosmetic.getRarity();

        List<String> lore = new ArrayList<>(); // <- Kit display items
        String category = cosmetic.getCategory();

        lore.add("&8" + category);
        lore.add(" ");
        lore.add("&7Select the " + cosmetic.getNameWithCategory());
        lore.add("&7for in-game chat messages!");
        if(rightclickable) {
            lore.add(" ");
            lore.add("&eRight-Click to preview!");
        }
        lore.add(" ");
        lore.add("&7Rarity: " + rarity.getDisplayName());

        PurchasableDesign design = cosmetic.getDesign();
        ProductItemDesign productItemDesign = new ProductItemDesign(design.getMaterial(), (short) design.getDurability(), lore);
        productItemDesign.setSkullTexture(design.getTexture());

        String settingKey = category.toLowerCase(Locale.ROOT).replace(" ", "");
        Cosmetic selectedCosmetic = user.getSetting(Cosmetic.class, settingKey);
        String purchasedStatus = "&eClick to select!";
        if(selectedCosmetic == cosmetic) {
            purchasedStatus = "&aSELECTED!";
        }
        productItemDesign.setPurchasedStatus(purchasedStatus);
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user, ClickType clickType) {
        // cosmetic selection code here
    }

    @Override
    public boolean hasRightClickSupport() {
        return rightclickable;
    }

    @Override
    public boolean ignoreDefaults() {
        return false;
    }
}
