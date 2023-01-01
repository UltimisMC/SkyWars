package com.ultimismc.gamescaler.test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class DockerTest {

    @Test
    public void testContainers() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
        for (Container container : dockerClient.listContainersCmd().exec()) {
            System.out.println(container.getImage());
        }
        dockerClient.createContainerCmd("")
                .withEnv("MODE=NORMAL", "TEAM=SOLO")
                .withName("SkyWars")
                .withHostName("Bitch").exec().getRawValues();
    }

}
