package com.example.puppy.api;

public class ApiConstants {
    public static final String URL_ROOT = "https://graph.instagram.com";
    public static final String ACCESS_TOKEN = "IGQVJVdmVoRkdxZAFpHWURLYUg1ZA0hzamliUHpCUU9FMUViLXIxRkN5aEpCTXNJMFhONXJpaEQtdE4wVXgyVktlSllmYjFvWUNOdHd2dWF2eHJNdDJZAMHRhT1MxTkE4bnc1NVZAVT0hMLXNOb1lrOWVaMwZDZD";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    //GET /me?fields={fields}&access_token={access-token}
    public static final String URL_USER_PROFILE = "/me?fields=id,username" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //Get user multimedia content
    //GET /me/media?fields={fields}&access_token={access-token}
    public static final String URL_USER_MEDIA_CONTENT = "/me/media?fields=id,caption,media_type,media_url" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //Firebase
    public static final String SERVER_URL = "https://protected-refuge-34893.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";

}
