package xyz.directplan.directlib.shop.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.shop.Product;
import xyz.directplan.directlib.shop.ProductCategory;
import xyz.directplan.directlib.shop.ProductItemDesign;
import xyz.directplan.directlib.shop.ShopHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class ProductCategoryMenu<U> extends InventoryUI {

    private final ShopHandler<U> shopHandler;

    private final ProductCategory<U> productCategory;
    private final InventoryUI previousMenu;
    private final U user;

    public ProductCategoryMenu(ShopHandler<U> shopHandler, U user, InventoryUI previousMenu, ProductCategory<U> productCategory) {
        super(productCategory.getName(), productCategory.getInventoryRows());

        this.shopHandler = shopHandler;
        this.productCategory = productCategory;
        this.previousMenu = previousMenu;
        this.user = user;
    }

    public ProductCategoryMenu(ShopHandler<U> shopHandler, U user, ProductCategory<U> productCategory) {
        this(shopHandler, user, null, productCategory);
    }

    @Override
    public void build(Player player) {
        List<Product<U>> products = productCategory.getProducts();
        for(Product<U> product : products) {

            String productName = product.getName();

            ProductItemDesign itemDesign = product.designItem(user);

            Material material = itemDesign.getMaterial();
            ChatColor color = itemDesign.getColor();

            List<String> lore = itemDesign.getLore();
            short durability = itemDesign.getDurability();

            String displayName = ChatColor.GREEN + productName;
            if(color != null) displayName = color + productName;

            if(product.isCategory()) {
                lore = new ArrayList<>(lore);
                lore.add(" ");
                lore.add("&eClick to browse!");
            }
            ProductCategoryAction<U> categoryAction = new ProductCategoryAction<>(shopHandler, user, this, product);
            MenuItem menuItem = new MenuItem(material, ChatColor.GREEN + displayName, durability, categoryAction);
            menuItem.setLore(lore);

            setSlot(product.getItemSlot(), menuItem);
        }
        if(previousMenu != null) {
            MenuItem backButton = new MenuItem(Material.ARROW, "&aGo Back");
            backButton.setLore("&7To " + previousMenu.getTitle());
            backButton.setAction((item, clicker, clickType) -> shopHandler.openInventory(player, previousMenu));
            int lastSlot = getLastSlotIndex();
            setSlot((lastSlot - 4), backButton);
            return;
        }
        addCloseButton();
    }
}

