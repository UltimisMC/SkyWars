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
public class ResistanceBoostPerk extends Perk implements PerkEvent<GameStartedEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_CHESTPLATE);

    private final Class<GameStartedEvent> eventClass = GameStartedEvent.class;

    public ResistanceBoostPerk() {
        super("Resistance Boost", PerkRarity.COMMON,
                Arrays.asList("&7Gain 15s of resistance II when",
                        "&7the game starts."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, GameStartedEvent event) {
        Player player = user.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 15, 1));
    }
}
