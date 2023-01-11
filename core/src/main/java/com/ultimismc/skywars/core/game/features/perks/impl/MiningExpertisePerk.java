package com.ultimismc.skywars.core.game.features.perks.impl;

import com.ultimismc.skywars.core.events.BlockMinedEvent;
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
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author DirectPlan
 */
@Getter
public class MiningExpertisePerk extends Perk implements PerkEvent<BlockMinedEvent> {

    private final PurchasableDesign design = new PurchasableDesign(Material.IRON_PICKAXE);
    private final Class<BlockMinedEvent> eventClass = BlockMinedEvent.class;


    public MiningExpertisePerk() {
        super("Mining Expertise", PerkRarity.COMMON,
                "&7Get 1 extra ore per block mined.");
    }

    @Override
    public void onTrigger(User user, GameConfig config, UserAsset asset, BlockMinedEvent event) {
        Block block = event.getBlock();

        int dropAmount = event.getDropAmount();

        ItemStack drop = block.getDrops().iterator().next();

        drop.setAmount(dropAmount + 1);
        World world = block.getWorld();
        world.dropItemNaturally(block.getLocation(), drop);
    }
}
