package com.example.puppy.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.puppy.data.Pet;

import java.util.ArrayList;
import java.util.List;

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
        this.presenter.setPets(getPets(false));
    }

    public void getFavoritePets() {
        this.presenter.setFavoritePets(getPets(true));
    }

    public void updatePetRate(Pet pet, int rate) {
        if(ratePet(pet, rate) > 0){
            pet.setRating(rate);
            this.presenter.setPetRate(pet);
        }
    }
}
