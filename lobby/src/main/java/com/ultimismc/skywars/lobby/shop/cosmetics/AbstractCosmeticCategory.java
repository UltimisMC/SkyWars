package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRegistry;
import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public abstract class AbstractCosmeticCategory<T extends PurchasableRegistry<?>> extends UserProductCategory {

    private final T cosmeticRegistry;
    private final List<String> description;

    public AbstractCosmeticCategory(T cosmeticRegistry, List<String> description, int itemSlot) {
        super(cosmeticRegistry.getName(), itemSlot, true);
        this.cosmeticRegistry = cosmeticRegistry;
        this.description = description;
    }

    @Override
    public ProductItemDesign designCategory(User user) {
        String settingKey = cosmeticRegistry.getSettingKey();
        Cosmetic cosmetic = user.getSetting(Cosmetic.class, settingKey);
        List<? extends Cosmetic> ownedCosmetics = user.getAssets(cosmetic.getClass());

        PurchasableDesign design = getDesign();
        List<String> lore = new ArrayList<>(this.description);
        int ownedCosmeticsSize = ownedCosmetics.size();
        int registrySize = cosmeticRegistry.getSize();
        int completionPercentage = PluginUtility.getPercentage(ownedCosmeticsSize, registrySize);

        lore.add(" ");
        lore.add("&7Unlocked: &a" + ownedCosmeticsSize + "/" + registrySize + " &8(" + completionPercentage + "%)");
        lore.add("&7Currently Selected:");
        lore.add(ChatColor.GREEN + cosmetic.getName());

        ProductItemDesign productItemDesign = new ProductItemDesign(design.getMaterial(), (short) design.getDurability(), lore);
        productItemDesign.setSkullTexture(design.getTexture());
        return productItemDesign;
    }

    public abstract PurchasableDesign getDesign();
}
