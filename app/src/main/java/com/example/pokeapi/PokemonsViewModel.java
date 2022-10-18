package com.example.pokeapi;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonsViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<Pokemon>> pokemons;

    public PokemonsViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
    }

    public MutableLiveData<List<Pokemon>> getPokemons() {
        if (pokemons == null) {
            pokemons = new MutableLiveData<>();
            refresh();
        }
        return pokemons;
    }

    private void refresh() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                app.getApplicationContext()
        );

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = null;
            try {
                pokemons = api.getPokemons();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.pokemons.postValue(pokemons);
        });
    }
}
