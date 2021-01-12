package com.example.puppy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvPets;
    private PetAdapter petAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        refreshLayout = findViewById(R.id.srl);

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
        petAdapter.add(new Pet("Hannah", R.drawable.hannah, random.nextInt(bound)));
        petAdapter.add(new Pet("Jamie", R.drawable.jamie, random.nextInt(bound)));
        petAdapter.add(new Pet("Jingles", R.drawable.jingles, random.nextInt(bound)));
        petAdapter.add(new Pet("Joe", R.drawable.joe, random.nextInt(bound)));
        petAdapter.add(new Pet("Marliese", R.drawable.marliese, random.nextInt(bound)));
        petAdapter.add(new Pet("Snoopy", R.drawable.snoopy, random.nextInt(bound)));
        petAdapter.add(new Pet("Tom", R.drawable.tom, random.nextInt(bound)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshLayout.setOnRefreshListener(() -> {
            populateAdapter();
            refreshLayout.setRefreshing(false);
        });

        findViewById(R.id.fab).setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshLayout.setOnRefreshListener(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_start:
                startActivity(new Intent(this, FavoriteActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}