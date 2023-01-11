package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserKillEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.TeamType;
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
public class BulldozerPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.ANVIL);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public BulldozerPerk() {
        super("Bulldozer", PerkRarity.LEGENDARY,
                Arrays.asList("&7Enemy kills give you strength I",
                        "&7for 5s in Solo (2s in Team Mode)."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserKillEvent event) {
        int duration = (2 * 20); // 2 seconds for Team Mode
        TeamType teamType = config.getTeamType();
        if(teamType.isSolo()) {
            duration = (5 * 20); // 5 seconds for Solo Mode.
        }
        Player player = user.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, duration, 0));
    }
}
