package xyz.directplan.directlib.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@Getter
public class ItemEnchantment {

    private final Enchantment enchantment;
    private String displayName;
    private final String name;
    @Setter private int level;
    private final int maxLevel;

    public ItemEnchantment(Enchantment enchantment, String name, int level) {
        this.enchantment = enchantment;
        this.name = name;
        this.displayName = name + " " + StringUtil.getRomanLevel(level);
        this.level = level;
        this.maxLevel = enchantment.getMaxLevel();
    }

    public ItemEnchantment(Enchantment enchantment, int level) {
        this(enchantment, null, level);
    }

    public ItemEnchantment(String enchantmentName, String displayName, int level) {
        this(Enchantment.getByName(enchantmentName), displayName, level);
    }

    public ItemEnchantment(String enchantmentName, int level) {
        this(Enchantment.getByName(enchantmentName), null, level);
    }


    public void increaseLevel(int level) {
        if((this.level + level) <= maxLevel) {
            this.level += level;
            displayName = name + " " + StringUtil.getRomanLevel(this.level);
        }
    }

    public void increaseLevel() {
        increaseLevel(1);
    }
}
