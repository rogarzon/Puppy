package com.example.puppy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.puppy.R;
import com.example.puppy.apdapter.PetPagerAdapter;
import com.example.puppy.data.Pet;
import com.example.puppy.fragments.PetListFragment;
import com.example.puppy.fragments.PetProfileFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PetListFragment.OnPetListEvents {
    private TabLayout tabLayout;
    private ViewPager pager;
    private PetPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        setUpViewPager();
    }

    private void setUpViewPager(){
        tabLayout = findViewById(R.id.tabLayout);
        pager = findViewById(R.id.pager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PetListFragment.newInstance());
        fragments.add(PetProfileFragment.newInstance());

        pagerAdapter = new PetPagerAdapter(getSupportFragmentManager(),
                fragments, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_poodle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.fab).setOnClickListener(v -> Toast.makeText(getApplicationContext(),
                "Hello", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_start:
                startActivity(new Intent(this, FavoriteActivity.class));
                break;
            case R.id.action_contact:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.action_about:
                startActivity(new Intent(this, BioActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPetClick(Pet pet) {
        if (pagerAdapter.getCount() > 1){
            Fragment fragment = pagerAdapter.getItem(1);
            ((PetProfileFragment)fragment).petProfile(pet);
        }
    }
}