package xyz.directplan.directlib.inventory.decoration;

import org.bukkit.Material;
import xyz.directplan.directlib.inventory.InventoryUI;
import xyz.directplan.directlib.inventory.MenuItem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
public interface InventoryDecoration {

    InventoryDecoration CIRCLE_DARK_PLASTIC = new CircleInventoryDecoration(Material.STAINED_GLASS_PANE, 15);

    MenuItem getDecorationAt(InventoryUI inventory, int slot);

    class InventoryDecorationBuilder implements InventoryDecoration {

        private final Map<Integer, MenuItem> decoration = new HashMap<>();

        public InventoryDecorationBuilder fill(MenuItem item, int... slots) {
            for(int slot : slots) {
                add(slot, item);
            }
            return this;
        }

        public InventoryDecorationBuilder add(int slot, MenuItem item) {
            decoration.put(slot, item);
            return this;
        }

        @Override
        public MenuItem getDecorationAt(InventoryUI inventory, int slot) {
            return decoration.get(slot);
        }

        public static InventoryDecorationBuilder builder() {
            return new InventoryDecorationBuilder();
        }
    }
}
