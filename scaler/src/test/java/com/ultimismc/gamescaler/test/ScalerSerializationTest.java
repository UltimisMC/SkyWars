package com.ultimismc.gamescaler.test;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.serializer.GsonSerializer;

/**
 * @author DirectPlan
 */
public class ScalerSerializationTest {


    public static void main(String[] args) {

        GsonBuilder builder = new GsonBuilder();
        GsonSerializer serializer = new GsonSerializer(builder);

        System.out.println("Calculating speed of serialization & deserialization");

        long took = 0;
        int count = 0;

        while(count <= 3) {
            long now = System.nanoTime();
            JsonElement jsonElement = serializer.serialize(new Server(new DummyServerPlugin(), "Nordine", "ND1", true));
        System.out.println("Serialized Json Element: " + jsonElement);

//        System.out.println("Deserializing...");
            Server scalerTest = serializer.deserialize(jsonElement.toString(), Server.class);

        System.out.println("Deserialized Class: " + scalerTest);
            took += System.nanoTime() - now;
            count++;
        }

        System.out.println(count + " Operations Completed!");
        System.out.println("Speed: " + (took / count) + " ns/s&d");
    }
}
