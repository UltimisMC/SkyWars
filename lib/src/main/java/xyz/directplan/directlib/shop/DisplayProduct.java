package xyz.directplan.directlib.shop;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

/**
 * @author DirectPlan
 */
public class DisplayProduct<U> extends AbstractProduct<U> {

    private final Material displayMaterial;
    private final List<String> lore;

    public DisplayProduct(Material displayMaterial, String name, List<String> lore, int itemSlot) {
        super(name, itemSlot);
        this.displayMaterial = displayMaterial;
        this.lore = lore;
    }

    @Override
    public boolean isDisplay() {
        return true;
    }

    @Override
    public ProductItemDesign designProduct(U unused) {
        return new ProductItemDesign(displayMaterial, lore);
    }

    @Override
    public void executeAction(U unused, ClickType clickType) {}
}
