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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetHolder> {
    private List<Pet> pets;
    private Context context;
    private OnPetClickListener petClickListener;

    public PetAdapter(Context context) {
        this.context = context;
        this.pets = new ArrayList<>();
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        PetHolder holder = new PetHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        holder.bind(pets.get(position));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public void add(Pet pet) {
        pets.add(pet);
    }

    public void addAll(Collection<Pet> pets) {
        pets.addAll(pets);
        notifyDataSetChanged();
    }

    public void clear() {
        pets.clear();
        notifyDataSetChanged();
    }

    public void setPetClickListener(OnPetClickListener listener){
        this.petClickListener = listener;
    }

    public class PetHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPet;
        private ImageView ivLess;
        private ImageView ivPlus;
        private TextView tvName;
        private TextView tvRating;

        public PetHolder(@NonNull View itemView) {
            super(itemView);
            ivPet = itemView.findViewById(R.id.ivPet);
            ivLess = itemView.findViewById(R.id.ivLess);
            ivPlus = itemView.findViewById(R.id.ivPlus);
            tvName = itemView.findViewById(R.id.tvPetName);
            tvRating = itemView.findViewById(R.id.tvRating);
            ivLess.setOnClickListener(this);
            ivPlus.setOnClickListener(this);

            itemView.setOnClickListener(v -> {
                if (petClickListener != null){
                    int position = getAdapterPosition();
                    Pet p = pets.get(position);
                    petClickListener.onPetClick(p);
                }
            });
        }

        public void bind(Pet pet) {
            ivPet.setImageResource(pet.getImage());
            tvName.setText(pet.getName());
            tvRating.setText(String.valueOf(pet.getRating()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Pet pet = pets.get(position);
            int rate = pet.getRating();

            switch (v.getId()) {
                case R.id.ivLess:
                    rate -= 1;
                    break;
                case R.id.ivPlus:
                    rate += 1;
                    break;
                default:
                    return;
            }

            if (rate >= 0 && rate <= 5) {
                pet.setRating(rate);
                notifyDataSetChanged();
            } else if (rate < 0) {
                Snackbar.make(v, context.getResources().getString(R.string.less_zero_rating),
                        Snackbar.LENGTH_SHORT).show();
            } else if (rate > 5) {
                Snackbar.make(v, context.getResources().getString(R.string.grater_five_rating),
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public interface OnPetClickListener {
        void onPetClick(Pet pet);
    }
}
