package xyz.directplan.directlib.inventory;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.function.Function;

/**
 * @author DirectPlan
 */
@Getter
public abstract class PlayerInventoryUI<U extends InventoryUser> extends InventoryUI {

    /* We do not need to set up the following inventory arguments since we're
    only going to edit the player's.
     */
    private final Player player;
    protected final U user;

    public PlayerInventoryUI(U user) {
        super(null, 5); // Default player inventory rows (9 * 5 = 45), so 45 slots
        this.user = user;
        player = user.getPlayer();
//        this.fillInventory(new MenuItem(Material.AIR, ""));
    }

    public void onInteract(PlayerInteractEvent event) {
        if(!(event.hasBlock() || event.hasItem())) return;


        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            // We will only need the Right click interaction at the moment
            // Free choice to edit coming soon.
            return;
        }

        Player player = event.getPlayer();
        // We need a slot.
        int slot = player.getInventory().getHeldItemSlot();

        MenuItem item = getItem(slot);
        if(item == null) {
            return;
        }
        if(item.isCancelAction()) {
            event.setCancelled(true);
        }
        if(!item.hasAction()) {
            return;
        }
        Block clickedBlock = event.getClickedBlock();
        item.performAction(item, player, clickedBlock, ClickType.RIGHT);
    }


    public void apply() {
        build(player);
        buildInventory();
    }

    @Override
    public void updateSlot(int slot, Function<MenuItem, MenuItem> itemFunction) {
        MenuItem item = getItem(slot);
        if(item == null) return;
        setSlot(slot, itemFunction.apply(item));

        buildInventory();
    }

    @Override
    public Inventory buildInventory() {
        PlayerInventory inventory = player.getInventory();
        MenuItem[] buttons = super.getItems();
        for(int i = 0; i < buttons.length; i++) {
            MenuItem button = buttons[i];
            if(button == null) continue;

            ItemStack itemStack = button.getItemStack();
            itemStack.setAmount(button.getItemAmount());

            inventory.setItem(i, itemStack);
        }
        return inventory;
    }


    @Override
    public void updateInventory() {
        player.updateInventory();
    }
}
