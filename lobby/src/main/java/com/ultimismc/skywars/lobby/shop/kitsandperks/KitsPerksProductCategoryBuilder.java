package com.ultimismc.skywars.lobby.shop.kitsandperks;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.core.game.features.kits.KitManager;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.kitsandperks.kits.KitProduct;
import com.ultimismc.skywars.lobby.shop.kitsandperks.perks.*;
import com.ultimismc.skywars.lobby.shop.soulwell.SoulWellProductCategory;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.DisplayProduct;
import xyz.directplan.directlib.shop.ProductCategory;

import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class KitsPerksProductCategoryBuilder implements UserProductCategoryBuilder {

    private final FeatureHandler featureHandler;

    @Override
    public ProductCategory<User> buildCategory() {

        KitsPerksProductCategory productCategory = new KitsPerksProductCategory(10);


        addKitsPerksCategoryMode(featureHandler, productCategory, GameType.NORMAL, Material.STONE_SWORD, Material.BREWING_STAND_ITEM, 12);
        addKitsPerksCategoryMode(featureHandler, productCategory, GameType.INSANE, Material.IRON_SWORD, Material.CAULDRON_ITEM, 14);

        productCategory.addProduct(new SoulWellProductCategory(40));

        return productCategory;
    }

    public void addKitsPerksCategoryMode(FeatureHandler featureHandler, KitsPerksProductCategory productCategory, GameType gameType, Material kitMaterial, Material perkMaterial, int kitSlot) {
        KitManager kitManager = featureHandler.getKitManager();
        PerkManager perkManager = featureHandler.getPerkManager();

        int perkSlot = (kitSlot + 9);
        KitsPerksShowcaseProductCategory kitShowcaseCategory = new KitsPerksShowcaseProductCategory(kitMaterial, gameType, "Kits", true, kitSlot);
        addKitProducts(kitShowcaseCategory, kitManager);
        productCategory.addProduct(kitShowcaseCategory);

        KitsPerksShowcaseProductCategory perkShowcaseCategory = new KitsPerksShowcaseProductCategory(perkMaterial, gameType, "Perks", perkSlot);
        addPerkProducts(perkShowcaseCategory, perkManager);
        productCategory.addProduct(perkShowcaseCategory);
    }

    public void addKitProducts(KitsPerksShowcaseProductCategory productCategory, KitManager kitManager) {
        for(Kit kit : kitManager.getKits()) {
            productCategory.addProduct(new KitProduct(kit));
        }
    }

    public void addPerkProducts(KitsPerksShowcaseProductCategory productCategory, PerkManager perkManager) {

        List<String> perksSlotsLore = ShopMessageKeys.PERKS_SLOTS_ITEM_LORE.getStringList();
        productCategory.addProduct(new DisplayProduct<>(Material.REDSTONE_TORCH_ON, "Perks Slots", perksSlotsLore, 19));


        PerkShopCategory perkShopCategory = new PerkShopCategory(50);
        PurchasedPerksCategory purchasedPerksCategory = new PurchasedPerksCategory(51);
        for(Perk perk : perkManager.getPerks()) {
            perkShopCategory.addProduct(new ShopPerkProduct(perk));
            purchasedPerksCategory.addProduct(new PurchasedPerkProduct(perk));
        }
        productCategory.addProduct(perkShopCategory);


        productCategory.addProduct(purchasedPerksCategory);

        int[] perkItemSlots = {20, 21, 22, 23, 24, 25};
        for(int i = 0; i < perkItemSlots.length; i++) {
            int perkSlot = (i + 1);
            int itemSlot = perkItemSlots[i];
            PerkSlot perkSlotProduct = new PerkSlot(perkSlot, itemSlot);

            perkSlotProduct.setProductPath(purchasedPerksCategory);

            productCategory.addProduct(perkSlotProduct);
        }


    }
}
