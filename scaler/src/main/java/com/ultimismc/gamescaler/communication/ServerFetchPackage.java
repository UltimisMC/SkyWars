package com.ultimismc.gamescaler.communication;

import com.ultimismc.gamescaler.Server;
import lombok.Data;

import java.util.List;

/**
 * @author DirectPlan
 */
@Data
public class ServerFetchPackage {

    private final Server destination;

    private final List<Server> availableServers;
}
