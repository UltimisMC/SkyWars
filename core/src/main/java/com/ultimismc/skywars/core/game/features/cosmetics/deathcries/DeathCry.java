package com.ultimismc.skywars.core.game.features.cosmetics.deathcries;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@Getter
public abstract class DeathCry extends Cosmetic {

    private final String category = "Death Cry";
    private final Sound crySound;

    public DeathCry(String name, CosmeticRarity rarity, Sound crySound) {
        super(name, rarity);
        this.crySound = crySound;
    }

    public void playDeathCry(User user) {
        if(crySound == null) return;
        if(!user.isOnline()) return;
        Player player = user.getPlayer();
        PluginUtility.playSound(player, crySound);
    }
}
