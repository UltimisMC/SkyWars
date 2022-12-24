package com.ultimismc.gamescaler.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author DirectPlan
 */
public class GsonSerializer implements Serializer {

    private final JsonParser parser = new JsonParser();

    private final Gson gson;

    public GsonSerializer(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.setPrettyPrinting().create();
    }

    public GsonSerializer() {
        this(new GsonBuilder());
    }

    @Override
    public JsonElement serialize(Object obj) {
        return parser.parse(gson.toJson(obj));
    }

    @Override
    public <T> T deserialize(String json, Class<T> cast) {
        return gson.fromJson(json, cast);
    }
}