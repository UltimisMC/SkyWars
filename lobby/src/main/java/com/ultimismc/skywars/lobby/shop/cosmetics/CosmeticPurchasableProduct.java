package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import org.bukkit.Sound;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author DirectPlan
 */
public class CosmeticPurchasableProduct extends UserPurchasableProduct {

    protected final PurchasableRegistry<?> registry;
    protected final Cosmetic cosmetic;
    protected final boolean rightclickable;

    public CosmeticPurchasableProduct(PurchasableRegistry<?> registry, Cosmetic cosmetic, boolean rightclickable) {
        super(0, cosmetic);
        this.registry = registry;
        this.cosmetic = cosmetic;
        this.rightclickable = rightclickable;
    }

    public CosmeticPurchasableProduct(PurchasableRegistry<?> registry, Cosmetic cosmetic) {
        this(registry, cosmetic, true);
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {
        PurchasableRarity rarity = cosmetic.getRarity();

        List<String> lore = new ArrayList<>(); // <- Kit display items
        String category = cosmetic.getCategory();

        lore.add("&8" + category);
        lore.add(" ");
        List<String> cosmeticDescription = cosmetic.getDescription();
        if(!cosmeticDescription.isEmpty()) {
            lore.addAll(cosmeticDescription);
            lore.add(" ");
        }
        if(rightclickable) {
            lore.add("&eRight-Click to preview!");
        }
        if(!cosmetic.isDefault()) {
            lore.add("&7Rarity: " + rarity.getDisplayName());
        }

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
    public void executePurchasableProduct(User user, GameType gameType, ClickType clickType) {
        user.setSetting(registry.getSettingKey(), cosmetic);
        PluginUtility.playSound(user.getPlayer(), Sound.CLICK);
    }

    @Override
    public boolean isRefreshInventoryEnabled() {
        return true;
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
