package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;

/**
 * @author DirectPlan
 */
@Getter
public class BlockPlacedEvent extends UserSkyWarsEvent implements Cancellable {

    @Setter private boolean cancelled;

    private final Block block;

    public BlockPlacedEvent(GameConfig gameConfig, User user, Block block) {
        super(gameConfig, user);

        this.block = block;
    }
}
