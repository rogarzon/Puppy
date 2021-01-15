package com.example.puppy.apdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PetPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public PetPagerAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragments,
                           int behavior) {
        super(fm, behavior);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
