package xyz.directplan.directlib.inventory;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class ConfirmableActionMenu extends InventoryUI {

    private final ConfirmedAction confirmedAction;
    private final MenuItem menuItem;

    public ConfirmableActionMenu(MenuItem menuItem, ConfirmedAction confirmedAction) {
        super("Are you sure?", 3);
        this.confirmedAction = confirmedAction;
        this.menuItem = menuItem;
    }

    @Override
    public void build(Player player) {

        ActionableItem cancellationAction = (item, clicker, clickedBlock, clickType) -> {
            clicker.sendMessage(PluginUtility.translateMessage("&cYou've cancelled this action."));
            clicker.closeInventory();
            clicker.playSound(clicker.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
        };

        menuItem.setAction((item, clicker, clickedBlock, clickType) -> {
            clicker.playSound(clicker.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
            confirmedAction.executeAction();
        });
        MenuItem cancellationItem = new MenuItem(Material.STAINED_CLAY, "&cCancel Action", 14, cancellationAction);
        cancellationItem.setLore(PluginUtility.translateMessage("&7Click here to cancel this action!"));

        setSlot(11, menuItem);
        setSlot(15, cancellationItem);
    }
}
