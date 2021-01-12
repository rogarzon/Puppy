package com.example.puppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;

import java.util.Random;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rvPets;
    private PetAdapter petAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = findViewById(R.id.tbFavorite);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        rvPets = findViewById(R.id.rvPets);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvPets.setLayoutManager(manager);
        populateAdapter();

        rvPets.setAdapter(petAdapter);
    }

    private void populateAdapter(){
        petAdapter = new PetAdapter(this);
        Random random = new Random();
        int bound = 6;

        petAdapter.add(new Pet("Butch", R.drawable.butch, random.nextInt(bound)));
        petAdapter.add(new Pet("Jamie", R.drawable.jamie, random.nextInt(bound)));
        petAdapter.add(new Pet("Joe", R.drawable.joe, random.nextInt(bound)));
        petAdapter.add(new Pet("Snoopy", R.drawable.snoopy, random.nextInt(bound)));
        petAdapter.add(new Pet("Tom", R.drawable.tom, random.nextInt(bound)));
    }
}