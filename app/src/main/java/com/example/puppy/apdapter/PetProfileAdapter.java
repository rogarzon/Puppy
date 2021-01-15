package com.example.puppy.apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puppy.R;
import com.example.puppy.data.Pet;

import java.util.List;

public class PetProfileAdapter extends RecyclerView.Adapter<PetProfileAdapter.PetHolder> {
    private List<Pet> pets;
    private Context context;

    public PetProfileAdapter(@NonNull List<Pet> pets, @NonNull Context context) {
        this.pets = pets;
        this.context = context;
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
        return new PetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.bind(pet);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class PetHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvRating;

        public PetHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivPet);
            tvRating = itemView.findViewById(R.id.tvRating);
        }

        public void bind(Pet pet) {
            ivImage.setImageResource(pet.getImage());
            tvRating.setText(String.valueOf(pet.getRating()));
        }
    }
}
