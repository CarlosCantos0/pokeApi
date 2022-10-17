package com.example.pokeapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.pokeapi.databinding.FragmentFirstBinding;
import com.example.pokeapi.databinding.LvPokemonRowBinding;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayAdapter adapter;

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

        PokemonApi api = new PokemonApi();
        try {
            api.getPokemons();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<Pokemon>(
                getContext(),
                R.layout.lv_pokemon_row,
                items
        );

        binding.lvPokemons.setAdapter(adapter);
        binding.lvPokemons.setOnClickListener((adapterView, view1, i, l) -> {
            Pokemon item = (Pokemon) adapterView.getItemAtPosition(i);

            Bundle datos = new Bundle();
            datos.putSerializable("item", item);


            NavHostFragment.findNavController(
                    this).navigate(R.id.action_FirstFragment_to_SecondFragment, datos);

        });

        refresh();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pokemon pokemon = getItem(position);
        Log.w("XXXX", pokemon.toString());

        LvPokemonRowBinding binding = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_row, parent, false);
        }

        TextView txtpokemon = convertView.findViewById(R.id.txtPokemon);
        ImageView imgPokemon = convertView.findViewById(R.id.imgPokemon);

        txtpokemon.setText(pokemon.getName());
        Glide.with(getContext()).load(pokemon.getImage()).into(imgPokemon);

        return convertView;

    }

    private void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            handler.post(()-> {
                adapter.clear();
                adapter.addAll(pokemons);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}