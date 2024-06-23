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
import com.ultimismc.skywars.lobby.shop.soulwell.SoulWellProductCategoryBuilder;
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

        SoulWellProductCategoryBuilder soulWellProductCategoryBuilder = new SoulWellProductCategoryBuilder(featureHandler, 40);
        productCategory.addProduct(soulWellProductCategoryBuilder.buildCategory());

        return productCategory;
    }

    public void addKitsPerksCategoryMode(FeatureHandler featureHandler, KitsPerksProductCategory productCategory, GameType gameType, Material kitMaterial, Material perkMaterial, int kitSlot) {
        KitManager kitManager = featureHandler.getKitManager();
        PerkManager perkManager = featureHandler.getPerkManager();

        int perkSlot = (kitSlot + 9);
        KitsPerksShowcaseProductCategory kitShowcaseCategory = new KitsPerksShowcaseProductCategory(kitMaterial, gameType, "Kits", true, kitSlot);
        addKitProducts(kitShowcaseCategory, kitManager, gameType);
        productCategory.addProduct(kitShowcaseCategory);

        KitsPerksShowcaseProductCategory perkShowcaseCategory = new KitsPerksShowcaseProductCategory(perkMaterial, gameType, "Perks", perkSlot);
        addPerkProducts(perkShowcaseCategory, perkManager, gameType);
        productCategory.addProduct(perkShowcaseCategory);
    }

    public void addKitProducts(KitsPerksShowcaseProductCategory productCategory, KitManager kitManager, GameType gameType) {
        for(Kit kit : kitManager) {
            productCategory.addProduct(new KitProduct(kit, gameType));
        }
    }

    public void addPerkProducts(KitsPerksShowcaseProductCategory productCategory, PerkManager perkManager, GameType gameType) {

        List<String> perksSlotsLore = ShopMessageKeys.PERKS_SLOTS_ITEM_LORE.getStringList();
        productCategory.addProduct(new DisplayProduct<>(Material.REDSTONE_TORCH_ON, "Perks Slots", perksSlotsLore, 19));


        PerkShopCategory perkShopCategory = new PerkShopCategory(50);
        OwnedPerksCategory ownedPerksCategory = new OwnedPerksCategory(51);
        for(Perk perk : perkManager) {
            if(perk.isDefault() || perk.isSoulWellPerk()) continue;
            perkShopCategory.addProduct(new ShopPerkProduct(perk, gameType));
            ownedPerksCategory.addProduct(new OwnedPerkProduct(ownedPerksCategory, perk, gameType));
        }
        productCategory.addProduct(perkShopCategory);


        productCategory.addProduct(ownedPerksCategory);

        int[] perkItemSlots = {20, 21, 22, 23, 24, 25};
        for(int slotIndex = 0; slotIndex < perkItemSlots.length; slotIndex++) {
            int perkSlot = (slotIndex + 1);
            int itemSlot = perkItemSlots[slotIndex];
            PerkSlot perkSlotProduct = new PerkSlot(ownedPerksCategory, gameType, perkSlot, itemSlot);
            productCategory.addProduct(perkSlotProduct);
        }
    }
}
