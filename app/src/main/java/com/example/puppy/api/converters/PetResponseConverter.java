package com.example.puppy.api.converters;

import com.example.puppy.api.JsonConstants;
import com.example.puppy.api.model.PetResponse;
import com.example.puppy.data.Pet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PetResponseConverter implements JsonDeserializer<PetResponse> {
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse response = gson.fromJson(json, PetResponse.class);

        JsonArray data = json.getAsJsonObject().getAsJsonArray(JsonConstants.DATA);
        response.setPets(parseData(data));

        return response;
    }

    private List<Pet> parseData(JsonArray data) {
        List<Pet> pets = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            JsonObject object = data.get(i).getAsJsonObject();
            String id = object.get(JsonConstants.ID).getAsString();
            String imageUrl = object.get(JsonConstants.MEDIA_URL).getAsString();

            Pet p = new Pet("", 0, 0);
            p.setImageUrl(imageUrl);
            p.setName(id);
            pets.add(p);
        }

        return pets;
    }
}

