package com.example.pokeapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.pokeapi.databinding.FragmentFirstBinding;
import com.example.pokeapi.databinding.LvPokemonRowBinding;

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

        adapter = new ArrayAdapter<Pokemon>(
                getContext(),
                R.layout.lv_pokemon_row,
                items
        );

        binding.lvPokemons.setAdapter(adapter);
        /*binding.lvPokemons.setOnClickListener((adapterView, view1, i, l) -> {
            Pokemon item = (Pokemon).adapterView.getItemAtPosition(i);

            Bundle datos = new Bundle();
            datos.putSerializable("item", item);


            NavHostFragment.findNavController(
                    this).navigate(R.id.action_FirstFragment_to_SecondFragment, datos);

        }); */

        refresh();

        PokemonsViewModel viewModel = new ViewModelProvider(getActivity()).get(PokemonsViewModel.class);
        viewModel.getPokemons().observe(getActivity(), pokemons -> {
            adapter.clear();
            adapter.addAll(pokemons);
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        refresh();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* if (item.getItemId() == R.id.refresh) {
            refresh();
        } */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}