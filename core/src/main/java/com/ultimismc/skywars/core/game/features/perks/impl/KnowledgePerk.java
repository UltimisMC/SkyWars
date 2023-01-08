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

/**
 * @author DirectPlan
 */
@Getter
public class KnowledgePerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.BOOK);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;


    public KnowledgePerk() {
        super("Knowledge", PerkRarity.COMMON,
                "&7Every kill you gain 3 EXP Level.");
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        Player player = user.getPlayer();
        player.giveExpLevels(3);
    }
}
