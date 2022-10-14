package com.example.pokeapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pokeapi.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle datos = getArguments();

        if(datos != null) {
            Pokemon pokemon = (Pokemon) datos.getSerializable("item");

            binding.txtNombreDetalle.setText(pokemon.getName());
            binding.txtAlturaDetalle.(pokemon.getHeight());
            binding.txtPesoDetalle.setText(pokemon.getWeight());

            Glide.with(getContext()).load(pokemon.getImage()).into(binding.ivPokemonGrande);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}