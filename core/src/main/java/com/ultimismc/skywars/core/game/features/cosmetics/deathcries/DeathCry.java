package com.ultimismc.skywars.core.game.features.cosmetics.deathcries;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
@Getter
public abstract class DeathCry extends Cosmetic {

    private final String category = "Death Cry";
    private Sound crySound;
    private String crySoundString;

    public DeathCry(String name, CosmeticRarity rarity, Sound crySound) {
        super(name, rarity);

        addDescription("Plays the " + getNameWithCategory());
        addDescription("when you die.");

        this.crySound = crySound;
    }

    public DeathCry(String name, CosmeticRarity rarity, String crySound) {
        super(name, rarity);
        this.crySoundString = crySound;
    }

    public void playDeathCry(User user) {
        if(crySound == null && crySoundString == null) return;
        if(!user.isOnline()) return;
        Player player = user.getPlayer();
        Location playerLocation = player.getLocation();
        for(Player online : Bukkit.getOnlinePlayers()) {
            if(crySound != null) {
                online.playSound(playerLocation, crySound, 5f, 5f);
                continue;
            }
            online.playSound(playerLocation, crySoundString, 5f, 5f);
        }
    }
}
