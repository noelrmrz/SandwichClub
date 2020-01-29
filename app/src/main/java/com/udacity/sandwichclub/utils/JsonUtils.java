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

            // Retrieve the JSONObject containing the name key-value pairs
            JSONObject names = jsonObject.getJSONObject("name");
            mainName = names.getString("mainName");

            // Retrieve the JSONArray containing other names
            JSONArray alsoKnownAsJson = names.getJSONArray("alsoKnownAs");
            alsoKnownAs = getList(alsoKnownAsJson);

            // Retrieve the JSONArray containing the ingredients
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

    /**
     * This method extracts the elements in the JSONArray and copies them in a List
     * @param jsonArray the array to convert to a List
     * @return the converted JSONArray
     * @throws JSONException
     */
    private static List<String> getList(JSONArray jsonArray) throws JSONException {
        List<String> myList = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            myList.add(jsonArray.getString(i));
        }
        return myList;
    }
}
