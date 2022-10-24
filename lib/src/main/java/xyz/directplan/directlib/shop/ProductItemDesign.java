package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
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
    private final ChatColor color;
    private final List<String> lore;

    public ProductItemDesign(Material material, ChatColor color, List<String> lore) {
        this(material, (short) 0, color, lore);
    }

    public ProductItemDesign(Material material, List<String> lore) {
        this(material, null, lore);
    }
}
