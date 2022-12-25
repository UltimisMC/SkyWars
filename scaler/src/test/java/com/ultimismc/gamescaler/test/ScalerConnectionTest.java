package com.ultimismc.gamescaler.test;

import com.ultimismc.gamescaler.communication.ConnectionData;
import com.ultimismc.gamescaler.test.connection.DummyServerManager;

/**
 * @author DirectPlan
 */
public class ScalerConnectionTest {

    public static void main(String[] args) {

        DummyServerManager serverManager = new DummyServerManager();

        ConnectionData connectionData = new ConnectionData("redis-17429.c44.us-east-1-2.ec2.cloud.redislabs.com", 17429);

        connectionData.setPassword("N9jvuUwBTKg2INJsEhJXt4JbnMNsFJjH");
        serverManager.connect(connectionData);
    }
}
