package xyz.directplan.directlib.inventory;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.directplan.directlib.inventory.manager.MenuManager;

/**
 * @author DirectPlan
 */
@Getter
public abstract class PlayerInventoryLayout extends InventoryUI {

    public PlayerInventoryLayout() {
        super(InventoryType.PLAYER, null, 5, false, null);
    }

    public void onInteract(MenuManager menuManager, PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!(event.hasBlock() || event.hasItem())) return;

        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            // We will only need the Right click interaction at the moment
            // Free choice to edit coming soon.
            return;
        }

        // We need a slot.
        int slot = player.getInventory().getHeldItemSlot();

        MenuItem item = getItem(slot);
        if(item == null) return;

        if(item.isCancelAction()) event.setCancelled(true);

        if(item.hasOpenInventory()) {
            menuManager.openInventory(player, item.getOpenInventory());
            return;
        }
        if(!item.hasAction()) return;

        item.performAction(item, player, ClickType.RIGHT);
    }

    @Override
    public void setSlot(int slot, MenuItem item) {
        this.setSlot(slot, item, false);
    }

    public void setSlot(int slot, MenuItem item, boolean cancelAction) {
        item.setCancelAction(cancelAction);
        super.setSlot(slot, item);
    }

    public void applyLayout(UserInventory userInventory) {
        Player player = userInventory.getPlayer();

        build(player);
        buildInventory(player);
    }

    public void buildInventory(Player player) {
        PlayerInventory inventory = player.getInventory();
        MenuItem[] buttons = super.getItems();
        for(int i = 0; i < buttons.length; i++) {
            MenuItem button = buttons[i];
            if(button == null) continue;

            ItemStack itemStack = button.getItemStack();
            itemStack.setAmount(button.getItemAmount());

            inventory.setItem(i, itemStack);
        }
    }
}
