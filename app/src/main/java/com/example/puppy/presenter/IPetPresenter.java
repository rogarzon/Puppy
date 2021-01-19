package com.example.puppy.presenter;

import androidx.annotation.NonNull;

import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;

import java.util.List;

public interface IPetPresenter {
    PetAdapter createPetAdapter(@NonNull List<Pet> pets);
    void setPetAdapter(PetAdapter adapter);
    void presentPetRate(Pet pet);
}
