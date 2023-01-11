package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserKillEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class LuckyCharmPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.SPECKLED_MELON);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;


    public LuckyCharmPerk() {
        super("Lucky Charm", PerkRarity.COMMON,
                Arrays.asList("&730% chance to get a Golden Apple",
                                "&7after a kill."));
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserKillEvent event) {
        if(!PluginUtility.hasChanceOccurred(30)) return;

        Player player = user.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
    }
}
