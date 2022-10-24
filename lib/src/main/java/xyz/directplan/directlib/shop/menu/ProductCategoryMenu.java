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

import java.util.List;

/**
 * @author DirectPlan
 */
public class ProductCategoryMenu<U> extends InventoryUI {

    private final ShopHandler<U> shopHandler;

    private final ProductCategory<U> productCategory;
    private final U user;

    public ProductCategoryMenu(ShopHandler<U> shopHandler, U user, ProductCategory<U> productCategory) {
        super(productCategory.getName(), productCategory.getInventoryRows());

        this.shopHandler = shopHandler;
        this.productCategory = productCategory;
        this.user = user;
    }

    @Override
    public void build(Player player) {
        List<Product<U>> products = productCategory.getProducts();
        for(Product<U> product : products) {

            ProductItemDesign itemDesign = product.designItem(user);

            Material material = itemDesign.getMaterial();
            String displayName = itemDesign.getDisplayName();
            List<String> lore = itemDesign.getLore();
            short durability = itemDesign.getDurability();

            ProductCategoryAction<U> categoryAction = new ProductCategoryAction<>(shopHandler, user, product);
            MenuItem menuItem = new MenuItem(material, ChatColor.GREEN + displayName, durability, categoryAction);
            menuItem.setLore(lore);

            setSlot(product.getItemSlot(), menuItem);
        }
        addCloseButton();
    }
}

