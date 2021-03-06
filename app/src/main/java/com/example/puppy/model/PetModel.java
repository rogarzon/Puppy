package com.example.puppy.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.puppy.api.ApiConstants;
import com.example.puppy.api.ApiEndpoint;
import com.example.puppy.api.ApiService;
import com.example.puppy.api.RetrofitClient;
import com.example.puppy.api.model.PetResponse;
import com.example.puppy.api.model.UserResponse;
import com.example.puppy.data.Pet;
import com.example.puppy.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetModel {
    private final String[] PET_FIELDS = {
            Database.PET_ID,
            Database.PET_NAME,
            Database.PET_IMAGE,
            Database.PET_RATING
    };
    private Context context;
    private IPetModel presenter;

    public PetModel(@Nullable Context context, @NonNull IPetModel presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    private List<Pet> getPets(boolean favorite) {
        List<Pet> pets = new ArrayList<>();

        SQLiteDatabase db = Database.getInstance(context).getReadableDatabase();
        Cursor c = db.query(Database.PET_TABLE, PET_FIELDS, null, null,
                null, null,
                favorite ? Database.PET_RATING + " DESC" : Database.PET_NAME + " ASC",
                favorite ? "5" : null);

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(Database.PET_ID));
                String name = c.getString(c.getColumnIndex(Database.PET_NAME));
                int image = c.getInt(c.getColumnIndex(Database.PET_IMAGE));
                int rating = c.getInt(c.getColumnIndex(Database.PET_RATING));

                Pet p = new Pet(name, image, rating);
                p.setId(id);
                pets.add(p);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return pets;
    }

    public int ratePet(Pet pet, int rate) {
        int rowAffected = 0;

        ContentValues values = new ContentValues();
        values.put(Database.PET_RATING, rate);

        SQLiteDatabase db = Database.getInstance(context).getWritableDatabase();
        rowAffected = db.update(Database.PET_TABLE, values,
                Database.PET_ID + " = ?",
                new String[]{String.valueOf(pet.getId())});

        db.close();
        return rowAffected;
    }

    public void getPets() {
        ApiEndpoint endpoint = ApiService.getInstance(ApiConstants.URL_ROOT);
        endpoint.getMediaContent().enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse result = response.body();
                presenter.setPets(result.getPets());
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Log.e("NETWORK_ERROR", t.getMessage());
            }
        });
    }

    public void getFavoritePets() {
        this.presenter.setFavoritePets(getPets(true));
    }

    public void updatePetRate(Pet pet, int rate) {
        /*if(ratePet(pet, rate) > 0){
            pet.setRating(rate);
            this.presenter.setPetRate(pet);
        }*/
        String token = Preferences.getInstance(context).getToken();

        RetrofitClient.newInstance(ApiConstants.SERVER_URL)
                .create(ApiEndpoint.class)
                .registerLikesImage(token, pet.getName(), pet.getImageUrl())
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        UserResponse userRes = response.body();
                        pet.setRating(rate);
                        presenter.setPetRate(pet);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(context, "NETWORK ERROR: " + t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
