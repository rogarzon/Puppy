package com.example.puppy.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.puppy.R;
import com.example.puppy.data.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String PET_TABLE = "pet";
    public static final String PET_ID = "id";
    public static final String PET_NAME = "name";
    public static final String PET_IMAGE = "image";
    public static final String PET_RATING = "rating";

    private static Database instance = null;

    private Database(@Nullable Context context) {
        super(context, "pet_database", null, VERSION);
    }

    public static Database getInstance(Context context){

        if (instance == null) {
            instance = new Database(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + PET_TABLE + "( " +
                PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PET_NAME + " TEXT, " +
                PET_IMAGE + " INTEGER, " +
                PET_RATING + " INTEGER);";
        db.execSQL(query);

        //DUMMY DATA
        List<Pet> pets = new ArrayList<>();
        Random random = new Random();
        int bound = 6;

        pets.add(new Pet("Butch", R.drawable.butch, random.nextInt(bound)));
        pets.add(new Pet("Hannah", R.drawable.hannah, random.nextInt(bound)));
        pets.add(new Pet("Jamie", R.drawable.jamie, random.nextInt(bound)));
        pets.add(new Pet("Jingles", R.drawable.jingles, random.nextInt(bound)));
        pets.add(new Pet("Joe", R.drawable.joe, random.nextInt(bound)));
        pets.add(new Pet("Marliese", R.drawable.marliese, random.nextInt(bound)));
        pets.add(new Pet("Snoopy", R.drawable.snoopy, random.nextInt(bound)));
        pets.add(new Pet("Tom", R.drawable.tom, random.nextInt(bound)));

        long id = 0L;

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);

            ContentValues values = new ContentValues();
            values.put(Database.PET_NAME, pet.getName());
            values.put(Database.PET_IMAGE, pet.getImage());
            values.put(Database.PET_RATING, pet.getRating());

            id = db.insert(Database.PET_TABLE, null, values);
            Log.d(Database.PET_TABLE,  "Pet ID: " + id);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.rawQuery("DROP TABLE IF EXISTS " + PET_TABLE, null);
        onCreate(db);
    }
}
