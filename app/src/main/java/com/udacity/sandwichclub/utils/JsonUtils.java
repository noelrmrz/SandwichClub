package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static List<String> alsoKnownAs;
    private static String mainName;
    private static String placeOfOrigin;
    private static String description;
    private static String image;
    private static List<String> ingredients;

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONObject names = jsonObject.getJSONObject("name");
            mainName = names.getString("mainName");
            JSONArray alsoKnownAsJson = names.getJSONArray("alsoKnownAs");
            alsoKnownAs = getList(alsoKnownAsJson);
            JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
            ingredients = getList(ingredientsJson);
            description = jsonObject.getString("description");
            placeOfOrigin = jsonObject.getString("placeOfOrigin");
            image = jsonObject.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static List<String> getList(JSONArray jsonArray) throws JSONException {
        List<String> myList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            myList.add(jsonArray.getString(i));
        }
        return myList;
    }
}
