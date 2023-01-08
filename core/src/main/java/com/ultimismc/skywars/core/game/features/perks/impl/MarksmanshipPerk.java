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
public class MarksmanshipPerk extends Perk implements PerkEvent<UserKillEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.BOW);
    private final Class<UserKillEvent> eventClass = UserKillEvent.class;

    public MarksmanshipPerk() {
        super("Marksmanship", PerkRarity.LEGENDARY,
                Arrays.asList("&7After getting 2 kills with a",
                        "&7bow, all of your bows will get",
                        "&7enchanted with Power I."));
    }

    @Override
    public void onTrigger(User user, UserAsset asset, UserKillEvent event) {
        Player player = user.getPlayer();

        ItemStack itemStack = player.getItemInHand();
        if(itemStack == null) return;
        if(itemStack.getType() != Material.BOW) return;

        MarksmanshipData marksmanshipData = (MarksmanshipData) asset.getTempObject();
        if(marksmanshipData == null) {
            asset.setTempObject(marksmanshipData = new MarksmanshipData());
        }
        marksmanshipData.incrementBowKills();
        if(marksmanshipData.getBowKills() >= 2) {
            asset.setTempObject(null);
            for(ItemStack content : player.getInventory()) {
                if(content == null || content.getType() != Material.BOW) continue;
                content.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
            }
            user.sendMessage("&eAll your bows are enchanted with Power I! &7(" + getNameWithCategory() + ")");
        }
    }

    @Getter
    static class MarksmanshipData {

        private int bowKills;

        public void incrementBowKills() {
            bowKills++;
        }
    }
}
