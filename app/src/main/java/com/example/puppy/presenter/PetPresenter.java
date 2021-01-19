package com.example.puppy.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;
import com.example.puppy.model.IPetModel;
import com.example.puppy.model.PetModel;

import java.util.List;

public class PetPresenter implements IPetModel {
    private IPetPresenter view;
    private PetModel petModel;

    public PetPresenter(@NonNull Context context, @NonNull IPetPresenter view) {
        this.view = view;
        this.petModel = new PetModel(context, this);
    }

    @Override
    public void setFavoritePets(List<Pet> pets) {
        view.setPetAdapter(view.createPetAdapter(pets));
    }

    @Override
    public void setPets(List<Pet> pets) {
        view.setPetAdapter(view.createPetAdapter(pets));
    }

    @Override
    public void setPetRate(Pet pet) {
        view.presentPetRate(pet);
    }

    public void populatePets(){
         this.petModel.getPets();
    }

    public void populateFavoritePets(){
        this.petModel.getFavoritePets();
    }

    public void updatePetRate(Pet pet, int rate){
        this.petModel.updatePetRate(pet, rate);
    }
}
