package com.example.pokeapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pokeapi.databinding.FragmentFirstBinding;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        ArrayList<Pokemon> items = new ArrayList<>();


        ArrayAdapter<Pokemon> adapter = new ArrayAdapter<Pokemon>(
                getContext(),
                R.layout.lv_pokemon_row,
                R.id.txtListName,
                items
        );

        binding.lvPokemons.setAdapter(adapter);

        PokemonApi api = new PokemonApi();
        try {
            api.getPokemons();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onViewCreated(view, savedInstanceState);
    }

    private void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            handler.post(()-> {
                Adapter.clear();
                Adapter.addAll(pokemons);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}