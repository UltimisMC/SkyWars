package com.ultimismc.skywars.core.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author DirectPlan
 */
@Data
@Getter
@Setter
public class User {

    private final UUID uuid;

    private Player player;
}
