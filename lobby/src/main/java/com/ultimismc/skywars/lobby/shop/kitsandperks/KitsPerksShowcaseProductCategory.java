package com.ultimismc.skywars.lobby.shop.kitsandperks;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
public class KitsPerksShowcaseProductCategory extends UserProductCategory {

    private final Material material;
    private final GameType gameType;
    private final String category;

    public KitsPerksShowcaseProductCategory(Material material, GameType gameType, String category, boolean paginated, int itemSlot) {
        super(gameType.getName() + " " + category, itemSlot, paginated);

        this.gameType = gameType;
        this.category = category;
        this.material = material;
    }

    public KitsPerksShowcaseProductCategory(Material material, GameType gameType, String category, int itemSlot) {
        this(material, gameType, category, false, itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {

        List<String> lore = Arrays.asList("&7Selection of unique " + category + " for ",
                "&7" + gameType.getName() + " games.");
        return new ProductItemDesign(material, lore);
    }
}
