package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.GameStartedEvent;
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
public class FatPerk extends Perk implements PerkEvent<GameStartedEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.GOLDEN_APPLE);
    private final Class<GameStartedEvent> eventClass = GameStartedEvent.class;

    public FatPerk() {
        super("Fat", PerkRarity.RARE,
                Arrays.asList("&7Gain 20s of absorption I when",
                        "&7the game starts."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, GameStartedEvent event) {
        Player player = user.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 400, 0)); // 20 seconds
    }
}
