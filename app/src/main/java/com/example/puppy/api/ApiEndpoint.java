package com.example.puppy.api;

import com.example.puppy.api.model.PetResponse;
import com.example.puppy.api.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoint {
    @GET(ApiConstants.URL_USER_MEDIA_CONTENT)
    Call<PetResponse> getMediaContent();

    //Heroku Server
    @FormUrlEncoded
    @POST(ApiConstants.KEY_POST_ID_TOKEN)
    Call<UserResponse> registerTokenID(@Field("token") String token,
                                       @Field("user_id") String user_id);

}
