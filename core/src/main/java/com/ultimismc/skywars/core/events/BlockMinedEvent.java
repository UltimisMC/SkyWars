package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import org.bukkit.block.Block;

/**
 * @author DirectPlan
 */
@Getter
public class BlockMinedEvent extends UserSkyWarsEvent {

    private final Block block;
    private final int dropAmount;

    public BlockMinedEvent(User user, Block block) {
        super(user);
        this.block = block;
        this.dropAmount = block.getDrops().size();
    }
}
