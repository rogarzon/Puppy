package com.example.puppy.api;

import com.example.puppy.api.model.PetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET(ApiConstants.URL_USER_MEDIA_CONTENT)
    Call<PetResponse> getMediaContent();
}
