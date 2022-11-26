package xyz.directplan.directlib.shop;

import lombok.Getter;
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

    @Setter private String skullTexture;
    @Setter private String displayName;
    private final Material material;
    private final short durability;
    private final ChatColor color;
    private final List<String> lore;
    @Setter private int amount = 1;
    @Setter private boolean canAfford;
    @Setter private String purchaseStatus, purchasedStatus;
    @Setter private boolean ignoreConfirmation;

    public ProductItemDesign(Material material, short durability, ChatColor color, List<String> lore, boolean canAfford) {
        this.material = material;
        this.durability = durability;
        this.color = color;
        this.lore = lore;
        this.canAfford = canAfford;
    }

    public ProductItemDesign(String skullTexture, ChatColor color, List<String> lore, boolean canAfford) {
        this(Material.SKULL_ITEM, (short)0, color, lore, canAfford);
        this.skullTexture = skullTexture;
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
