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
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class BlackMagicPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.CAULDRON_ITEM);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public BlackMagicPerk() {
        super("Black Magic", PerkRarity.LEGENDARY,
                Arrays.asList("&730% chance to get an enderpearl",
                        "&7after throwing a player in the",
                        "&7void."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        if(!event.isVoidKill()) return;
        if(!PluginUtility.hasChanceOccurred(30)) return;

        Player player = user.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
    }
}
