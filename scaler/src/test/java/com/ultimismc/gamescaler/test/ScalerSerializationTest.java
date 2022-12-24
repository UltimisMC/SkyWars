package com.ultimismc.gamescaler.test;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
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

        while(count <= 1000) {
            long now = System.nanoTime();
            JsonElement jsonElement = serializer.serialize(new ScalerTest());
//        System.out.println("Serialized Json Element: " + jsonElement);

//        System.out.println("Deserializing...");
            ScalerTest scalerTest = serializer.deserialize(jsonElement.toString(), ScalerTest.class);

//        System.out.println("Deserialized Class: " + scalerTest);
            took += System.nanoTime() - now;
            count++;
        }

        System.out.println(count + " Operations Completed!");
        System.out.println("Speed: " + (took / count) + " ns/s&d");
    }
}
