package com.ultimismc.skywars.core.game.features.soulwell;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class XezbethLuckSoulPerk extends AbstractPerk {

    private final PurchasableDesign design = new PurchasableDesign(Material.GOLD_INGOT);

    public XezbethLuckSoulPerk() {
        super("Xezbeth Luck", PerkRarity.SOUL_WELL, false, Arrays.asList("&7Get &c3 &7extra souls on win."));
        setPrice(7500);
    }
}
