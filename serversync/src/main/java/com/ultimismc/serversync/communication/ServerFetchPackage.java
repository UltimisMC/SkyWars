package com.ultimismc.serversync.communication;

import com.ultimismc.serversync.Server;
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
