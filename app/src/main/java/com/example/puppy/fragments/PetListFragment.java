package com.example.puppy.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.puppy.R;
import com.example.puppy.apdapter.PetAdapter;
import com.example.puppy.data.Pet;
import com.example.puppy.presenter.IPetPresenter;
import com.example.puppy.presenter.PetPresenter;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetListFragment extends Fragment implements IPetPresenter, PetAdapter.OnPetClickListener {
    private RecyclerView rvPets;
    private PetAdapter petAdapter;
    private SwipeRefreshLayout refreshLayout;
    private OnPetListEvents petListEvents;
    private PetPresenter petPresenter;

    public PetListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PetListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetListFragment newInstance() {
        PetListFragment fragment = new PetListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_list, container, false);
        refreshLayout = v.findViewById(R.id.srl);
        setUpRecyclerView(v);

        petPresenter = new PetPresenter(getContext(), this);

        return v;
    }

    private void setUpRecyclerView(View view) {
        rvPets = view.findViewById(R.id.rvPets);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,
                false);
        rvPets.setLayoutManager(manager);
    }

    @Override
    public void onPetClick(Pet pet) {
        if (petListEvents != null) {
            petListEvents.onPetClick(pet);
        }
    }

    @Override
    public void onPetRated(Pet pet, int rate) {
        petPresenter.updatePetRate(pet, rate);
    }

    @Override
    public PetAdapter createPetAdapter(@NonNull List<Pet> pets) {
        petAdapter = new PetAdapter(getContext());
        petAdapter.addAll(pets);
        return petAdapter;
    }

    @Override
    public void setPetAdapter(PetAdapter adapter) {
        rvPets.setAdapter(adapter);
        adapter.setPetClickListener(this);
    }

    @Override
    public void presentPetRate(Pet pet) {
        Pet p = petAdapter.get(pet);

        if (p != null) {
            p.setRating(pet.getRating());
            petAdapter.notifyDataSetChanged();
        }
    }

    void populatePets() {
        petPresenter.populatePets();
    }

    @Override
    public void onStart() {
        super.onStart();

        refreshLayout.setOnRefreshListener(() -> {
            populatePets();
            refreshLayout.setRefreshing(false);
        });

        if (petAdapter != null) {
            petAdapter.setPetClickListener(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        populatePets();
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshLayout.setOnRefreshListener(null);

        if (petAdapter != null) {
            petAdapter.setPetClickListener(null);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPetListEvents){
            petListEvents = (OnPetListEvents) context;
        } else {
            throw new RuntimeException(context.toString() + " It must implement OnPetListEvents");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        petListEvents = null;
    }

    public interface OnPetListEvents {
        void onPetClick(Pet pet);
    }
}