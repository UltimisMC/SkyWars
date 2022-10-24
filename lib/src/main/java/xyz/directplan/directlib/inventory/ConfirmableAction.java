package xyz.directplan.directlib.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

import java.util.Collections;
import java.util.List;

/**
 * @author DirectPlan
 */
public class ConfirmableAction extends InventoryUI {

    private final List<String> description;
    private final ActionableItem acceptAction;
    private final Object itemKey;

    public ConfirmableAction(List<String> description, ActionableItem acceptAction, Object itemKey) {
        super("Please confirm", 5);
        this.description = description;
        this.acceptAction = acceptAction;
        this.itemKey = itemKey;
    }

    public ConfirmableAction(String description, ActionableItem acceptAction, Object itemKey) {
        this(Collections.singletonList(description), acceptAction, itemKey);
    }
    public ConfirmableAction(ActionableItem acceptAction, Object itemKey) {
        this(ChatColor.GRAY + "Click here to confirm this action!", acceptAction, itemKey);
    }

    @Override
    public void build(Player player) {

        MenuItem glassItem = new MenuItem(Material.STAINED_GLASS_PANE, "&c", 15);

        fillInventory(glassItem);

        ActionableItem cancellationAction = (item, clicker, clickType) -> {
            clicker.sendMessage(PluginUtility.translateMessage("&b&l(!) &fYou've cancelled this action!"));
            clicker.closeInventory();
        };

        int[] confirmationItemSlots = {
                10, 11, 12,
                19, 20, 21,
                28, 29, 30
        };
        int[] cancellationItemSlots = {
                14, 15, 16,
                23, 24, 25,
                32, 33, 34
        };
        MenuItem confirmationItem = new MenuItem(Material.STAINED_CLAY, "&a&lConfirm Action", 13, acceptAction);
        confirmationItem.setItemKey(itemKey);
        confirmationItem.setLore(description);
        MenuItem cancellationItem = new MenuItem(Material.STAINED_CLAY, "&c&lCancel Action", 14, cancellationAction);
        cancellationItem.setLore(PluginUtility.translateMessage("&7Click here to cancel this action!"));

        for (int confirmationItemSlot : confirmationItemSlots) {
            setSlot(confirmationItemSlot, confirmationItem);
        }
        for (int cancellationItemSlot : cancellationItemSlots) {
            setSlot(cancellationItemSlot, cancellationItem);
        }
    }
}
