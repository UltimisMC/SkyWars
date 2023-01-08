package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.UserKillEvent;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.perks.Perk;
import com.ultimismc.skywars.core.game.features.perks.PerkRarity;
import com.ultimismc.skywars.core.game.features.perks.event.PerkEvent;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
@Getter
public class BarbarianPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_AXE);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public BarbarianPerk() {
        super("Barbarian", PerkRarity.LEGENDARY,
                Arrays.asList("&7Gain a Sharpness level after",
                        "&7getting 3 Axe Kills."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {

        Player player = user.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if(itemStack == null) return;
        if(!itemStack.getType().name().endsWith("_AXE")) return;

        BarbarianData barbarianData = (BarbarianData) asset.getTempObject();
        if(barbarianData == null) {
            asset.setTempObject(barbarianData = new BarbarianData());
        }
        barbarianData.incrementAxeKills();
        if(barbarianData.getAxeKills() >= 3) {
            asset.setTempObject(null);
            // Gain sharpness level
            int enchantmentLevel = itemStack.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
            itemStack.addEnchantment(Enchantment.DAMAGE_ALL, enchantmentLevel + 1);
        }
    }

    @RequiredArgsConstructor
    @Getter
    static class BarbarianData {

        private int axeKills;

        public void incrementAxeKills() {
            axeKills++;
        }
    }
}
