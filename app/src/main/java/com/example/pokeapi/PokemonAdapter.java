package com.example.pokeapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.pokeapi.databinding.LvPokemonRowBinding;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {
    public PokemonAdapter(@NonNull Context context, int resource) {
        super(context, resource);
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
}
