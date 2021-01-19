package com.example.puppy.model;

import com.example.puppy.data.Pet;

import java.util.List;

public interface IPetModel {
    void setFavoritePets(List<Pet> pets);
    void setPets(List<Pet> pets);
    void setPetRate(Pet pet);
    //int likePet(Pet pet, boolean increase);
}
