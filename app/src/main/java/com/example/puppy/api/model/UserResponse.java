package com.example.puppy.api.model;

public class UserResponse {
    private String id;
    private String token;
    private String userId;

    public UserResponse(String id, String token, String userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public UserResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
