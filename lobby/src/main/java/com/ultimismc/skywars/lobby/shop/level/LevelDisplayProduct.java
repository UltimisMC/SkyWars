package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

/**
 * @author DirectPlan
 */
public class LevelDisplayProduct extends UserProduct {

    public LevelDisplayProduct(int itemSlot) {
        super("Level Display", itemSlot);
    }

    @Override
    public ProductItemDesign designProduct(User user) {

        Level level = user.getLevel();

        ChatColor prestigeColor = level.getPrestigeColor();
        String displayName = prestigeColor + "Level " + level.getOrder();

        Material material = level.getPrestigeMaterial();
        ProductItemDesign productItemDesign = new ProductItemDesign(material);
        String prestigeTexture = level.getPrestigeTexture();
        if(prestigeTexture != null) {
            productItemDesign.setSkullTexture(prestigeTexture);
        }
        productItemDesign.setDisplayName(displayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {}
}
