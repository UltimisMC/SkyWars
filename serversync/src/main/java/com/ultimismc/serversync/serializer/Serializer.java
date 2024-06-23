package com.ultimismc.serversync.serializer;

import com.google.gson.JsonElement;

/**
 * @author DirectPlan
 */
public interface Serializer {

    JsonElement serialize(Object obj);

    <T> T deserialize(String json, Class<T> cast);
}
