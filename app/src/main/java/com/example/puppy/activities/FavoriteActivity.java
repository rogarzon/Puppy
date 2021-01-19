package com.example.puppy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.puppy.R;
import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;
import com.example.puppy.presenter.IPetPresenter;
import com.example.puppy.presenter.PetPresenter;

import java.util.List;
import java.util.Random;

public class FavoriteActivity extends AppCompatActivity implements IPetPresenter {
    private RecyclerView rvPets;
    private PetAdapter petAdapter;
    private PetPresenter petPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = findViewById(R.id.tbFavorite);
        toolbar.setTitle(R.string.favorite_activity_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        petPresenter = new PetPresenter(getApplicationContext(), this);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        rvPets = findViewById(R.id.rvPets);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvPets.setLayoutManager(manager);
    }

    private void populateFavoritePets(){
        petPresenter.populateFavoritePets();
    }

    @Override
    public PetAdapter createPetAdapter(@NonNull List<Pet> pets) {
        petAdapter = new PetAdapter(this);
        petAdapter.addAll(pets);
        return petAdapter;
    }

    @Override
    public void setPetAdapter(PetAdapter adapter) {
        rvPets.setAdapter(adapter);
    }

    @Override
    public void presentPetRate(Pet pet) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        populateFavoritePets();
    }
}