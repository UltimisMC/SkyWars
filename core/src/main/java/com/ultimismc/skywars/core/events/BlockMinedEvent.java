package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
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

    public BlockMinedEvent(GameConfig gameConfig, User user, Block block, int dropAmount) {
        super(gameConfig, user);
        this.block = block;
        this.dropAmount = dropAmount;
    }
}
