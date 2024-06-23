package com.ultimismc.skywars.lobby.shop.kitsandperks.perks;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.lobby.shop.UserTypedProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PerkSlot extends UserTypedProduct<UserAsset> {

    private final OwnedPerksCategory ownedPerksCategory;
    private final int perkSlot;
    private final GameType gameType;

    public PerkSlot(OwnedPerksCategory ownedPerksCategory, GameType gameType, int perkSlot, int itemSlot) {
        super("Perk Slot", itemSlot, UserAsset.class);
        this.ownedPerksCategory = ownedPerksCategory;
        this.gameType = gameType;
        this.perkSlot = perkSlot;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        List<UserAsset> userAssets = user.getActivatedAssets(Perk.class, gameType);
        int assetIndex = (perkSlot - 1);
        if(userAssets.size() > assetIndex) {
            UserAsset userAsset = userAssets.get(assetIndex);
            Perk perk = (Perk) userAsset.getPurchasable();
            ProductItemDesign productItemDesign = PerkProductDesigner.designProduct(perk, user);
            productItemDesign.addLore("&eRight-Click to toggle &cOFF");
            productItemDesign.setData(userAsset);
            setProductPath(null);
            return productItemDesign;
        }
        List<String> lore = Arrays.asList("&eClick here to view your",
                "&aPurchased Perks &ein the chest",
                "&eicon below to equip perks in",
                "&ethis slot!");
        String displayName = "&cPerk Slot #" + perkSlot + " - " + "Empty";


        ProductItemDesign productItemDesign = new ProductItemDesign(Material.STAINED_GLASS_PANE, (short) 14, ChatColor.RED, lore, true);
        productItemDesign.setDisplayName(displayName);

        setProductPath(ownedPerksCategory);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, UserAsset asset, ClickType clickType) {
        if(clickType != ClickType.RIGHT) return;

        ownedPerksCategory.togglePerk(user, asset);
    }

    @Override
    public boolean isRefreshInventoryEnabled() {
        return true;
    }
}
