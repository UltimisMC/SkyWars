package xyz.directplan.directlib.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * @author DirectPlan
 */
public class ConfirmableAction extends InventoryUI {

    private final List<String> description;
    private final ActionableItem acceptAction;

    public ConfirmableAction(List<String> description, ActionableItem acceptAction) {
        super("Are you sure?", 5);
        this.description = description;
        this.acceptAction = acceptAction;
    }

    public ConfirmableAction(String description, ActionableItem acceptAction) {
        this(Collections.singletonList(description), acceptAction);
    }
    public ConfirmableAction(ActionableItem acceptAction) {
        this(ChatColor.GRAY + "Click here to confirm this action!", acceptAction);
    }

    @Override
    public void build(Player player) {

        MenuItem glassItem = new MenuItem(Material.STAINED_GLASS_PANE, "&c", 15);

        fillInventory(glassItem);

        ActionableItem cancellationAction = (item, clicker, clickedBlock, clickType) -> {
            clicker.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lYou've cancelled this action!"));
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
        MenuItem confirmationItem = new MenuItem(Material.STAINED_CLAY, "&a&lConfirm Action", 13, (item, clicker, clickedBlock, clickType) -> {
            clicker.closeInventory();
            acceptAction.performAction(item, clicker, clickedBlock, clickType);
        });
        confirmationItem.setLore(description);
        MenuItem cancellationItem = new MenuItem(Material.STAINED_CLAY, "&c&lCancel Action", 14, cancellationAction);
        cancellationItem.setLore(ChatColor.translateAlternateColorCodes('&', "&7Click here to cancel this action!"));

        for (int confirmationItemSlot : confirmationItemSlots) {
            setSlot(confirmationItemSlot, confirmationItem);
        }
        for (int cancellationItemSlot : cancellationItemSlots) {
            setSlot(cancellationItemSlot, cancellationItem);
        }
    }
}
