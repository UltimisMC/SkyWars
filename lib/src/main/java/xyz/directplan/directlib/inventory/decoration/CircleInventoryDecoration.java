package xyz.directplan.directlib.inventory.decoration;

import org.bukkit.Material;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

/**
 * @author DirectPlan
 */
public class CircleInventoryDecoration implements InventoryDecoration {

    private final MenuItem menuItem;

    public CircleInventoryDecoration(Material material, int durability) {
        menuItem = new MenuItem(material, "&c", durability);
    }

    public CircleInventoryDecoration(Material material) {
        this(material, 0);
    }

    @Override
    public MenuItem getDecorationAt(InventoryUI inventoryUI, int slot) {
        int size = inventoryUI.getSize();
        if(slot >= 0 && slot <= 8) return menuItem;
        if(slot >= size - 9 && slot <= size) return menuItem;

        double currentRow = slot / 9.0;
        int currentRowFraction = (int) ((currentRow % 1) * 100);
        if(currentRowFraction == 0 || currentRowFraction == 88) return menuItem;
        return null;
    }
}
