package xyz.directplan.directlib.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.enchantments.Enchantment;
import xyz.directplan.directlib.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class ItemEnchantment {

    private static final Map<Enchantment, String> ENCHANTMENT_NAMES = new HashMap<>();

    static {
        ENCHANTMENT_NAMES.put(Enchantment.PROTECTION_ENVIRONMENTAL, "Protection");
        ENCHANTMENT_NAMES.put(Enchantment.PROTECTION_FIRE, "Fire Protection");
        ENCHANTMENT_NAMES.put(Enchantment.PROTECTION_FALL, "Feather Falling");
        ENCHANTMENT_NAMES.put(Enchantment.PROTECTION_EXPLOSIONS, "Blast Protection");
        ENCHANTMENT_NAMES.put(Enchantment.PROTECTION_PROJECTILE, "Projectile Protection");
        ENCHANTMENT_NAMES.put(Enchantment.OXYGEN, "Respiration");
        ENCHANTMENT_NAMES.put(Enchantment.WATER_WORKER, "Aqua Affinity");
        ENCHANTMENT_NAMES.put(Enchantment.THORNS, "Thorns");
        ENCHANTMENT_NAMES.put(Enchantment.DEPTH_STRIDER, "Depth Strider");
        ENCHANTMENT_NAMES.put(Enchantment.DAMAGE_ALL, "Sharpness");
        ENCHANTMENT_NAMES.put(Enchantment.DAMAGE_UNDEAD, "Smite");
        ENCHANTMENT_NAMES.put(Enchantment.DAMAGE_ARTHROPODS, "Bane of Arthropods");
        ENCHANTMENT_NAMES.put(Enchantment.KNOCKBACK, "Knockback");
        ENCHANTMENT_NAMES.put(Enchantment.FIRE_ASPECT, "Fire Aspect");
        ENCHANTMENT_NAMES.put(Enchantment.LOOT_BONUS_MOBS, "Looting");
        ENCHANTMENT_NAMES.put(Enchantment.DIG_SPEED, "Efficiency");
        ENCHANTMENT_NAMES.put(Enchantment.SILK_TOUCH, "Silk Touch");
        ENCHANTMENT_NAMES.put(Enchantment.DURABILITY, "Unbreaking");
        ENCHANTMENT_NAMES.put(Enchantment.LOOT_BONUS_BLOCKS, "Fortune");
        ENCHANTMENT_NAMES.put(Enchantment.ARROW_DAMAGE, "Power");
        ENCHANTMENT_NAMES.put(Enchantment.ARROW_KNOCKBACK, "Punch");
        ENCHANTMENT_NAMES.put(Enchantment.ARROW_FIRE, "Flame");
        ENCHANTMENT_NAMES.put(Enchantment.ARROW_INFINITE, "Infinity");
        ENCHANTMENT_NAMES.put(Enchantment.LUCK, "Luck of the Sea");
        ENCHANTMENT_NAMES.put(Enchantment.LURE, "Lure");
    }

    private final Enchantment enchantment;
    private String displayName;
    private final String name;
    @Setter private int level;
    private final int maxLevel;

    public ItemEnchantment(Enchantment enchantment, int level) {
        this.enchantment = enchantment;
        this.name = ENCHANTMENT_NAMES.get(enchantment);
        this.displayName = name + " " + StringUtil.getRomanLevel(level);
        this.level = level;
        this.maxLevel = enchantment.getMaxLevel();
    }

    public ItemEnchantment(String enchantmentName, int level) {
        this(Enchantment.getByName(enchantmentName), level);
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
