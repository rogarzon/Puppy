package com.example.puppy.api;

import com.example.puppy.api.converters.PetResponseConverter;
import com.example.puppy.api.model.PetResponse;
import com.example.puppy.api.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getInstance(String baseUrl) {
        if (retrofit == null) {
            retrofit = newInstance(baseUrl);
        }
        return retrofit;
    }

    public static Retrofit newInstance(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(createConverter()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Gson createConverter() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(PetResponse.class, new PetResponseConverter());
        return builder.create();
    }
}
