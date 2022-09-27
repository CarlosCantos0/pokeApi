package com.example.pokeapi;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PokemonApi {
    void getPokemons() throws IOException {
        String url = "https://pokeapi.co/api/v2/pokemon";

        try {
            String result = HttpUtils.get(url);


            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            for (int i = 0; i <   results.length(); i++) {
                JSONObject pokemonJson = results.getJSONObject(i);

                Pokemon pokemon = new Pokemon();
                pokemon.setName(pokemonJson.getString("name"));
                pokemon.setDetailsURL(pokemonJson.getString("url"));

                String resultDetails = HttpUtils.get(pokemon.getDetailsURL());
                JSONObject jsondetails = new JSONObject(resultDetails);

                pokemon.setHeight(jsondetails.getInt("height"));

                //pokemonsLista.add(pokemon);
            }

            Log.e("XXXX POKEMONS XXXX", pokemons.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
