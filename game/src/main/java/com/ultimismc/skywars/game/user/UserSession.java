package com.ultimismc.skywars.game.user;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Data;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Data
@Setter
public class UserSession {

    private final User user;
    private final GameServer gameServer;

    private boolean spectator;

    private final UserStatistics userStatistics = new UserStatistics();


}
