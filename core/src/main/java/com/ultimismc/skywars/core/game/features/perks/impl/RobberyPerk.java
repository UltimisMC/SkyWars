package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserDamagedEvent;
import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.directplan.directlib.PluginUtility;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class RobberyPerk extends Perk implements PerkEvent<UserDamagedEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_BARDING);
    private final Class<UserDamagedEvent> eventClass = UserDamagedEvent.class;

    public RobberyPerk() {
        super("Robbery", PerkRarity.LEGENDARY,
                Arrays.asList("&720% chance to drop a player's",
                        "&7held item by hitting them with",
                        "&7your fist."));
    }

    // %zaml-displayname% &emade you drop your item with &bRobbery Perk


    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, UserDamagedEvent event) {
        Player player = user.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if(itemStack.getType() != Material.AIR) return;

        if(!PluginUtility.hasChanceOccurred(20)) return;
        User damaged = event.getDamaged();

        Player damagedPlayer = damaged.getPlayer();
        ItemStack itemInHand = damagedPlayer.getItemInHand();

        World world = damagedPlayer.getWorld();
        damagedPlayer.setItemInHand(null);
        world.dropItemNaturally(damagedPlayer.getLocation(), itemInHand);
        damaged.sendMessage(user.getDisplayName() + " &emade you drop your item in hand with &b" + getNameWithCategory());
        user.sendMessage("&eYou made " + damaged.getDisplayName() + " &edrop their item in hand with &b" + getNameWithCategory());
    }
}
