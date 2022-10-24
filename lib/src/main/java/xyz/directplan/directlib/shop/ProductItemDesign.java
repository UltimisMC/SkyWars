package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;

import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class ProductItemDesign {

    private final Material material;
    private final short durability;
    private final String displayName;
    private final List<String> lore;

    public ProductItemDesign(Material material, String displayName, List<String> lore) {
        this(material, (short) 0, displayName, lore);
    }
}
