package com.ultimismc.skywars.lobby.shop.soulwell;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserPurchasableProduct;
import com.ultimismc.skywars.lobby.shop.soulwell.roll.SoulWellRollMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.inventory.manager.MenuManager;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class RollSoulWellProduct extends UserPurchasableProduct {

    private final SkyWarsPlugin plugin;
    private final MenuManager menuManager;

    public RollSoulWellProduct(SkyWarsPlugin plugin, int itemSlot) {
        super("Roll Soul Well", itemSlot, 25, Currency.SOUL_CURRENCY);

        this.plugin = plugin;
        this.menuManager = plugin.getMenuManager();
    }

    @Override
    public ProductItemDesign designPurchasableProduct(User user) {

        String status = "&eClick to roll!";

        List<String> lore = Arrays.asList("&7Rolls for a random kit, perk,",
                "&7or coin bonus.",
                " ");
        ProductItemDesign productItemDesign = new ProductItemDesign(Material.ENDER_PORTAL_FRAME, lore);
        productItemDesign.setPurchaseStatus(status);
        return productItemDesign;
    }

    @Override
    public void executePurchasableProduct(User user, GameType gameType, ClickType clickType) {
        Player player = user.getPlayer();
        menuManager.openInventory(player, new SoulWellRollMenu(plugin, user));
    }
}
