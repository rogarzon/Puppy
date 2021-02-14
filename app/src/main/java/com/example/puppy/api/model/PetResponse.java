package com.example.puppy.api.model;

import com.example.puppy.data.Pet;

import java.util.List;

public class PetResponse {
    private List<Pet> pets;

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
