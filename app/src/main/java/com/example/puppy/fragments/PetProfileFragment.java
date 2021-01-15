package com.example.puppy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.puppy.R;
import com.example.puppy.apdapter.PetProfileAdapter;
import com.example.puppy.data.Pet;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetProfileFragment extends Fragment {
    private RecyclerView rvPets;
    private PetProfileAdapter adapter;
    private CircularImageView civImage;
    private TextView tvPetName;

    public PetProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PetProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetProfileFragment newInstance() {
        PetProfileFragment fragment = new PetProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_profile, container, false);
        setUpUI(v);

        return v;
    }

    private void setUpRecyclerView(View view){
        rvPets = view.findViewById(R.id.rvPets);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                3, GridLayoutManager.VERTICAL,
                false);
        rvPets.setLayoutManager(gridLayoutManager);
    }

    private void populateList(Pet pet){
        Random random = new Random();
        int times = random.nextInt(random.nextInt(10)) + 1;
        List<Pet> petPhotos = new ArrayList<>();

        for (int i = 0; i <= times; i++){
            petPhotos.add(new Pet(pet.getName(), pet.getImage(), random.nextInt(6)));
        }

        adapter = new PetProfileAdapter(petPhotos, getActivity());
        rvPets.setAdapter(adapter);
    }

    private void setUpUI(View view){
        civImage = view.findViewById(R.id.ivImage);
        tvPetName = view.findViewById(R.id.tvPetName);

        setUpRecyclerView(view);
    }

    public void petProfile(Pet pet){
        tvPetName.setText(pet.getName());
        civImage.setImageResource(pet.getImage());
        populateList(pet);
    }
}