package xyz.directplan.directlib.inventory.manager;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.directplan.directlib.inventory.InventoryUser;
import xyz.directplan.directlib.inventory.PlayerInventoryUI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author DirectPlan
 */
public class PlayerMenuManager {

    private final Map<UUID, InventoryUser> activePlayerInventories = new HashMap<>();
    private final Map<UUID, CachedInventoryUser> previousInventories = new HashMap<>();

    public void applyDesign(PlayerInventoryUI design) {
        InventoryUser inventoryUser = design.getUser();
        inventoryUser.setCurrentInventoryUi(design);
        Player player = inventoryUser.getPlayer();

        activePlayerInventories.put(player.getUniqueId(), inventoryUser);
        design.apply();
    }

    public void applyDesign(PlayerInventoryUI design, boolean cacheForLater) {
        InventoryUser inventoryUser = design.getUser();
        Player player = inventoryUser.getPlayer();
        if(cacheForLater) {
            PlayerInventory playerInventory = player.getInventory();

            PlayerInventoryUI playerInventoryUI = inventoryUser.getInventoryUi();
            previousInventories.put(player.getUniqueId(), new CachedInventoryUser(playerInventory.getContents(), playerInventory.getArmorContents(), playerInventoryUI));
        }
        // Applying after the cache to not cause inventory conflicts
        applyDesign(design);
    }

    public void applyDesign(PlayerInventoryUI design, boolean clearInventory, boolean cacheForLater) {
        InventoryUser inventoryUser = design.getUser();
        Player player = inventoryUser.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        if(cacheForLater) {
            PlayerInventoryUI playerInventoryUI = inventoryUser.getInventoryUi();

            previousInventories.put(player.getUniqueId(), new CachedInventoryUser(playerInventory.getContents(), playerInventory.getArmorContents(), playerInventoryUI));
        }
        if(clearInventory) {
            playerInventory.setHelmet(null);
            playerInventory.setChestplate(null);
            playerInventory.setLeggings(null);
            playerInventory.setBoots(null);
            playerInventory.clear();
        }
        // Applying after the cache to not cause inventory conflicts
        applyDesign(design);
    }

    public void revertInventory(InventoryUser inventoryUser) {

        Player player = inventoryUser.getPlayer();

        UUID uuid = player.getUniqueId();
        CachedInventoryUser cachedInventoryUser = previousInventories.get(uuid);
        if(cachedInventoryUser == null) return;


        PlayerInventory inventory = player.getInventory();
        inventory.clear();

        inventory.setContents(cachedInventoryUser.getContents());
        inventory.setArmorContents(cachedInventoryUser.getArmorContents());
        PlayerInventoryUI inventoryUI = cachedInventoryUser.getPlayerInventoryUI();

        inventoryUser.setCurrentInventoryUi(inventoryUI);

        player.updateInventory();
        previousInventories.remove(uuid);
    }

    public InventoryUser getInventoryUser(UUID uuid) {
        return activePlayerInventories.get(uuid);
    }

    public InventoryUser getInventoryUser(Player player) {
        return getInventoryUser(player.getUniqueId());
    }

}

@Getter
class CachedInventoryUser {

    private final ItemStack[] contents, armorContents;
    private final PlayerInventoryUI playerInventoryUI;

    public CachedInventoryUser(ItemStack[] contents, ItemStack[] armorContents, PlayerInventoryUI playerInventoryUI) {
        this.contents = contents;
        this.armorContents = armorContents;
        this.playerInventoryUI = playerInventoryUI;
    }
}
