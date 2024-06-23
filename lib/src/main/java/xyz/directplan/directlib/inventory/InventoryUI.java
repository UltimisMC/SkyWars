package xyz.directplan.directlib.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import xyz.directplan.directlib.inventory.decoration.InventoryDecoration;
import xyz.directplan.directlib.inventory.manager.MenuManager;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author DirectPlan
 */
@Getter
public abstract class InventoryUI {

    protected Inventory inventory;

    @Setter private String title;
    private final MenuItem[] items;
    private final int size;
    private final boolean displayUi;
    private Object lock;
    private final InventoryDecoration decoration;

    @Setter private InventoryUI previousInventory;

    public InventoryUI(InventoryType inventoryType, String title, int size, boolean displayUi, InventoryDecoration decoration) {
        this.title = title != null ? title : inventoryType.getDefaultTitle();
        this.size = inventoryType != InventoryType.CHEST ? inventoryType.getDefaultSize() : size;
        this.displayUi = displayUi;
        this.decoration = decoration;

        items = new MenuItem[this.size];

        if(inventoryType == InventoryType.PLAYER) return;
        if(title == null) throw new NullPointerException("Inventory title cannot be null");

        this.inventory = inventoryType == InventoryType.CHEST ? Bukkit.createInventory(null, this.size, title) : Bukkit.createInventory(null, inventoryType, title);
    }

    public InventoryUI(String title, int rows, boolean displayUi, InventoryDecoration decoration) {
        this(InventoryType.CHEST, title, rows * 9, displayUi, decoration);
    }

    public InventoryUI(String title, int rows, InventoryDecoration decoration) {
        this(title, rows, false, decoration);
    }

    public InventoryUI(String title, int rows) {
        this(title, rows, false, null);
    }

    public int getSize() {
        return size;
    }

    public void setSlot(int slot, MenuItem item){
        items[slot] = item;
        item.setSlot(slot);
    }

    public void setSlot(MenuItem item) {
        int slot = item.getSlot();
        setSlot(slot, item);
    }

    public void clearItems() {
        Arrays.fill(items, null);
    }

    public MenuItem getItem(int slot){
        if(slot > items.length - 1) {
            return null;
        }
        return items[slot];
    }

    public void fillInventory(MenuItem item) {
        Arrays.fill(items, item);
    }

    public void onClick(MenuManager menuManager, InventoryClickEvent event) {
        if(displayUi) {
            event.setCancelled(true);
            return;
        }

        Player clicker = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if(slot < 0) return;
        onClick(slot);

        MenuItem item = getItem(slot);
        if(item == null) return;

        if(item.isCancelAction()) event.setCancelled(true);

        ClickType clickType = event.getClick();
        item.performAction(item, clicker, event.getClick());

        InventoryUI itemInventory = item.getOpenInventory();
        if(item.getOpenInventory() != null) {
            if(itemInventory.getPreviousInventory() == null) itemInventory.setPreviousInventory(this);

            clicker.playSound(clicker.getLocation(), Sound.CLICK, 1, 1);

            lock();
            menuManager.openInventory(clicker, itemInventory);
            unlock();
            return;
        }
        if(item.isRefreshable(clicker, clickType)) refresh(clicker);
    }

    public void onClick(int slot) {}

    public void onClose(Player player, Inventory inventory) {}

    public Inventory buildInventory(){
        int size = items.length;
        for(int i = 0; i < size; i++){
            MenuItem button = items[i];
            if(button == null) {
                if(decoration == null) continue;
                MenuItem decorationItem = decoration.getDecorationAt(this, i);
                if(decorationItem == null) continue;

                button = decorationItem;
            }
            this.inventory.setItem(i, button.getItemStack());
        }
        return inventory;
    }

    public void refresh(Player player) {
        inventory.clear();
        build(player);
        buildInventory();
        player.updateInventory();
    }

    public void updateSlot(int slot, Function<MenuItem, MenuItem> itemFunction) {
        MenuItem item = getItem(slot);
        if(item == null) return;
        setSlot(slot, itemFunction.apply(item));

        buildInventory();
    }

    /**
     * This override methods, clones the {@parameter other}
     * @param other The inventory to swap
     */
    @Deprecated
    public void override(Player player, InventoryUI other) {
        for(int i = 0; i < other.getItems().length; i++) {
            MenuItem item = other.getItem(i);
            this.items[i] = item;
        }
        this.open(player);
    }

    public int buildHeader() {
        MenuItem glassItem = new MenuItem(Material.STAINED_GLASS_PANE, "&c", 15);

        for(int firstRow = 0; firstRow < 9; firstRow++) {
            setSlot(firstRow, glassItem);
        }

        return 9;
    }

    public void addCloseButton() {
        MenuItem closeButton = new MenuItem(Material.BARRIER, "&cClose");
        closeButton.setAction((item, clicker, clickedBlock, clickType) -> clicker.closeInventory());

        int lastSlot = size - 1;
        setSlot((lastSlot - 4), closeButton);
    }

    public void addBackButton(int buttonSlot) {
        if(previousInventory == null) return;

        MenuItem backItem = new MenuItem(Material.ARROW, "&cBack");
        backItem.setLore("&7Click to return to &e" + previousInventory.getTitle() + "&7.");
        backItem.setOpenInventory(previousInventory);

        setSlot(buttonSlot, backItem);
    }

    public String getInventoryId() {
        return "Inventory";
    }

    public void open(Player player){
        inventory.clear();
        build(player);
        Inventory inventory = buildInventory();
        player.openInventory(inventory);
    }

    public abstract void build(Player player);

    public void lock() {
        lock = new Object();
    }
    public void unlock() {
        lock = null;
    }
    public boolean isLocked() {
        return lock != null;
    }
}