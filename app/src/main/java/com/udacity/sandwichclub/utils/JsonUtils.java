package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonObjectName = jsonObject.getJSONObject("name");
        JSONArray jsonKnownAsArray = jsonObjectName.getJSONArray("alsoKnownAs");
        JSONArray jsonIngridentsArray = jsonObject.getJSONArray("ingredients");

        for(int i = 0 ; i<jsonKnownAsArray.length();i++){
            alsoKnownAs.add(i,jsonKnownAsArray.getString(i));
        }

        for(int i = 0 ; i<jsonIngridentsArray.length();i++){
            ingredients.add(i,jsonIngridentsArray.getString(i));
        }

        String mainName = jsonObjectName.getString("mainName");
        String placeOfOrigin = jsonObject.getString("placeOfOrigin");
        String description = jsonObject.getString("description");
        String imageUrl = jsonObject.getString("image");

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,imageUrl,ingredients);

        return sandwich;
    }
}
