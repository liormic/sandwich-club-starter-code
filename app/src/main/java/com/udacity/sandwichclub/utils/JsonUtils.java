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
        String unavilableMessage = "Sorry, this field is unavailable";
        String mainName;
        String placeOfOrigin;
        String description;
        String imageUrl;
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonObjectName = jsonObject.getJSONObject("name");
        JSONArray jsonKnownAsArray = jsonObjectName.getJSONArray("alsoKnownAs");
        JSONArray jsonIngridentsArray = jsonObject.getJSONArray("ingredients");

        //Checking if arrays are empty
        if(jsonKnownAsArray.length()==0){
            alsoKnownAs.add(unavilableMessage);
        }else {
            for (int i = 0; i < jsonKnownAsArray.length(); i++) {
                alsoKnownAs.add(i, jsonKnownAsArray.getString(i));
            }
        }

        if(jsonIngridentsArray.length()==0){
            ingredients.add(unavilableMessage);
        }else{
        for(int i = 0 ; i<jsonIngridentsArray.length();i++){

                ingredients.add(i, jsonIngridentsArray.getString(i));
            }
        }

        //Checking if the value are empty
        if(jsonObjectName.getString("mainName").equals("")){
            mainName = unavilableMessage;
        }else {
            mainName = jsonObjectName.getString("mainName");
        }

        if(jsonObject.getString("placeOfOrigin").equals("")){
            placeOfOrigin = unavilableMessage;
        }else {
            placeOfOrigin = jsonObject.getString("placeOfOrigin");
        }

        if(jsonObject.getString("description").equals(" ")){
            description = unavilableMessage;
        }else {
            description = jsonObject.getString("description");
        }


            imageUrl = jsonObject.getString("image");


         return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,imageUrl,ingredients);

    }
}
