package com.ultimismc.skywars.core.game.features.soulwell;

import com.ultimismc.skywars.core.game.features.perks.AbstractPerk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class XezbethLuckSoulPerk extends AbstractPerk {

    public XezbethLuckSoulPerk() {
        super(Material.GOLD_INGOT, (short)0, "Xezbeth Luck", PerkRarity.SOUL_WELL, false, Arrays.asList("&7Get &c3 &7extra souls on win."));
        setPrice(7500);
    }
}
