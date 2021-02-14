package com.example.puppy.api;

public class ApiService {
    private static ApiEndpoint instance = null;

    public static ApiEndpoint getInstance(String baseUrl) {
        if (instance == null) {
            instance = RetrofitClient.getInstance(baseUrl).create(ApiEndpoint.class);
        }
        return instance;
    }
}
