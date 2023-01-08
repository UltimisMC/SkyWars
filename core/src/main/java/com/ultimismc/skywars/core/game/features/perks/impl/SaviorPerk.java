package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserKillEvent;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class SaviorPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.APPLE);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public SaviorPerk() {
        super("Savior", PerkRarity.COMMON,
                Arrays.asList("&7Enemy kills give you absorption I",
                        "&7for 7 seconds."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        Player player = user.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 7, 0));
    }
}
