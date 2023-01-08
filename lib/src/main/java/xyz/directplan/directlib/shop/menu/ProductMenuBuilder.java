package xyz.directplan.directlib.shop.menu;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.shop.Product;
import xyz.directplan.directlib.shop.ProductItemDesign;
import xyz.directplan.directlib.shop.ShopHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ProductMenuBuilder<U> {

    private final ShopHandler<U> shopHandler;
    private final U user;
    private final InventoryUI currentMenu;

    public MenuItem buildProduct(Product<U> product) {

        String productName = product.getName();

        ProductItemDesign itemDesign = product.designProduct(user);
        if(itemDesign == null) return null;

        Material material = itemDesign.getMaterial();

        List<String> lore = itemDesign.getLore();
        short durability = itemDesign.getDurability();

        if(product.isCategory()) {
            lore = new ArrayList<>(lore);
            lore.add(" ");
            lore.add("&eClick to browse!");
        }
        ChatColor displayNameColor = ChatColor.RED;
        boolean canAfford = itemDesign.isCanAfford();
        if(canAfford) {
            displayNameColor = ChatColor.GREEN;
            if(itemDesign.getColor() != null) {
                displayNameColor = itemDesign.getColor();
            }
        }
        ProductCategoryAction<U> productCategoryAction = new ProductCategoryAction<>(shopHandler, user, product, itemDesign, currentMenu, itemDesign.isIgnoreConfirmation(), canAfford);

        String displayName = displayNameColor + productName;
        if(itemDesign.hasDisplayName()) {
            displayName = itemDesign.getDisplayName();
        }
        MenuItem menuItem = new MenuItem(material, displayName, durability, productCategoryAction);

        int amount = itemDesign.getAmount();
        if(amount > 1) {
            menuItem.setAmount(amount);
        }
        if(itemDesign.isInvisibleEnchanted()) {
            menuItem.addInvisibleEnchantment();
        }
        String texture = itemDesign.getSkullTexture();
        menuItem.setCustomSkullProperty(texture);
        menuItem.setLore(lore);
        return menuItem;
    }

    public void addGoBackButton(Player player, InventoryUI currentMenu, InventoryUI previousMenu) {
        MenuItem backButton = new MenuItem(Material.ARROW, "&aGo Back");
        backButton.setLore("&7To " + previousMenu.getTitle());
        backButton.setAction((item, clicker, clickedBlock, clickType) -> shopHandler.openInventory(player, previousMenu));
        int lastSlot = currentMenu.getLastSlotIndex();
        currentMenu.setSlot((lastSlot - 4), backButton);
    }
}
