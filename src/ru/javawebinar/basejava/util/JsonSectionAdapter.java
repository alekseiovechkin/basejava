package ru.javawebinar.basejava.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";


    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = jsonPrimitive.getAsString();

        try {
            Class clazz = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(T section, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject retValue = new JsonObject();
        retValue.addProperty(CLASSNAME, section.getClass().getName());
        JsonElement element =  jsonSerializationContext.serialize(section);
        retValue.add(INSTANCE, element);
        return retValue;
    }
}