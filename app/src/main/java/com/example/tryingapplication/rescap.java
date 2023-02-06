package com.example.tryingapplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class rescap {


    public static ArrayList<String> id = new ArrayList<>();
    public static ArrayList<String> url = new ArrayList<>();
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> bathrooms = new ArrayList<>();
    public static ArrayList<String> bedrooms = new ArrayList<>();
    public static ArrayList<String> city = new ArrayList<>();
    public static ArrayList<ArrayList<String>> images = new ArrayList<>();
    public static ArrayList<String> host_thumbnail = new ArrayList<>();
    public static ArrayList<String> lat = new ArrayList<>();
    public static ArrayList<String> lng = new ArrayList<>();
    public static ArrayList<String> rating = new ArrayList<>();
    public static ArrayList<String> property_type = new ArrayList<>();
    public static ArrayList<String> address = new ArrayList<>();



    public static void store_Data(JsonArray jsonArray)
    {
        for (int i = 0; i < jsonArray.size()/ 2; i++) {
            JsonObject obj = jsonArray.get(i).getAsJsonObject();

            //get list of all ids
            id.add(obj.get("id").getAsString());

            //get list of all urls
            url.add(obj.get("url").getAsString());

            //get list of all names
            names.add(obj.get("name").getAsString());

            //get list of no. of bathrooms in every accommodation
            bathrooms.add(obj.get("bathrooms").getAsString());

            //get list of bhk no. for every corresponding accommodation
            bedrooms.add(obj.get("bedrooms").getAsString());

            //get list of city
            city.add(obj.get("city").getAsString());


            //get json images array convert it into arraylist of arraylists
            ArrayList<String> a1 = new ArrayList<>();
            JsonArray image_url = obj.get("images").getAsJsonArray();
            for(int x = 0; x < image_url.size(); x++)
            {
                a1.add(String.valueOf(image_url.get(x)));
            }
            images.add(a1);


        }
    }


}
