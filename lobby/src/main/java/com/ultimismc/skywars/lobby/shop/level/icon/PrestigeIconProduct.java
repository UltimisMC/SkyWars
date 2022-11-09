package com.ultimismc.skywars.lobby.shop.level.icon;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class PrestigeIconProduct extends UserProduct {

    private final Prestige prestige;
    public PrestigeIconProduct(Prestige prestige) {
        super("Prestige Icon", 0);

        this.prestige = prestige;
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        Player player = user.getPlayer();
        Level level = user.getLevel();
        String userChatLevelPrefix = level.getDisplayName(prestige, true);

        String iconDisplayName = prestige.getIconDisplayName();
        Material material = prestige.getPrestigeMaterial();
        String icon = prestige.getIcon();
        String status = "&eClick to select!";
        if(user.getSelectedPrestigeIcon() == prestige) {
            status = "&aSELECTED";
        }
        if(!user.hasPrestigeAccess(prestige)) {
            status = "&cRequires " + prestige.getUncoloredDisplayName() + ".";
        }

        List<String> lore = Arrays.
                asList("&7Sets your current Prestige Icon",
                        "&7to: &f" + icon,
                        " ",
                        userChatLevelPrefix + " " + player.getDisplayName(),
                        " ",
                        status);

        ProductItemDesign productItemDesign = new ProductItemDesign(material, lore);
        String prestigeTexture = prestige.getPrestigeTexture();
        if(prestigeTexture != null) {
            productItemDesign.setSkullTexture(prestigeTexture);
        }
        productItemDesign.setDisplayName(iconDisplayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {
        Player player = user.getPlayer();
        if(!user.hasPrestigeAccess(prestige)) {
            String prestigeDisplayName = prestige.getUncoloredDisplayName();

            MessageConfigKeys.LEVEL_PRESTIGE_ICON_REQUIRED.sendMessage(player,
                    new Replacement("prestige", prestigeDisplayName),
                    new Replacement("level", prestige.getRequiredLevel()));
            return;
        }
        if(user.getSelectedPrestigeIcon() == prestige) {
            MessageConfigKeys.LEVEL_PRESTIGE_ICON_ALREADY_SELECTED.sendMessage(player);
            return;
        }
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
        String prestigeDisplayName = prestige.getDisplayName();
        MessageConfigKeys.LEVEL_PRESTIGE_ICON_SUCCESS.sendMessage(player, new Replacement("prestige-icon", prestigeDisplayName));

        user.setSelectedPrestigeIcon(prestige);
    }
}
