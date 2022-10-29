package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class ProductItemDesign {

    @Setter private String displayName;
    private final Material material;
    private final short durability;
    private final ChatColor color;
    private final List<String> lore;
    @Setter private boolean canAfford;

    public ProductItemDesign(Material material, short durability, ChatColor color, List<String> lore, boolean canAfford) {
        this.material = material;
        this.durability = durability;
        this.color = color;
        this.lore = lore;
        this.canAfford = canAfford;
    }

    public ProductItemDesign(Material material, short durability, ChatColor color, List<String> lore) {
        this(material, durability, color, lore, true);
    }

    public ProductItemDesign(Material material, ChatColor color, List<String> lore, boolean canAfford) {
        this(material, (short) 0, color, lore, canAfford);
    }

    public ProductItemDesign(Material material, ChatColor color, List<String> lore) {
        this(material, (short) 0, color, lore, true);
    }

    public ProductItemDesign(Material material, List<String> lore, boolean canAfford) {
        this(material, null, lore, canAfford);
    }

    public ProductItemDesign(Material material, List<String> lore) {
        this(material, null, lore, true);
    }

    public ProductItemDesign(Material material) {
        this(material, null, new ArrayList<>(), true);
    }

    public boolean hasDisplayName() {
        return displayName != null;
    }
}
