package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.gymapplication.Models.CaptionedAdapterNutritionWL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;

public class WeightGainNutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_gain_nutrition_activity);

        String identifier = getIntent().getExtras().get("identifier").toString();

        HashSet<String> temp = new HashSet<String>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        HashSet<String> hashSet = new HashSet<>();

        hashSet = (HashSet<String>) sharedPreferences.getStringSet(identifier,temp);
        ArrayList<String> tempList = new ArrayList<>(hashSet);
        Gson gson = new Gson();

        String[] names = new String[tempList.size()];
        String[] types = new String[tempList.size()];
        String[] urls = new String[tempList.size()];

        for(int i=0;i<tempList.size();++i){
            System.out.println("----------------");
            System.out.println(tempList.get(i));
            JsonObject jsonObject = gson.fromJson(tempList.get(i),JsonObject.class);
            if(jsonObject.get("type").toString().replaceAll("\"","").equals("breakfast")) {
                names[0] = jsonObject.get("name").toString().replaceAll("\"", "");
                types[0] = jsonObject.get("type").toString().replaceAll("\"", "");
                urls[0] = jsonObject.get("image").toString().replaceAll("\"", "");
            }
            else if(jsonObject.get("type").toString().replaceAll("\"","").equals("lunch")) {
                names[1] = jsonObject.get("name").toString().replaceAll("\"", "");
                types[1] = jsonObject.get("type").toString().replaceAll("\"", "");
                urls[1] = jsonObject.get("image").toString().replaceAll("\"", "");
            }

            else if(jsonObject.get("type").toString().replaceAll("\"","").equals("dinner")) {
                names[2] = jsonObject.get("name").toString().replaceAll("\"", "");
                types[2] = jsonObject.get("type").toString().replaceAll("\"", "");
                urls[2] = jsonObject.get("image").toString().replaceAll("\"", "");
            }

          /*  NutritionModel nutritionModel = new NutritionModel(jsonObject.get("name").toString().replaceAll("\"",""),jsonObject.get("type").toString().replaceAll("\"",""));
            System.out.println(nutritionModel.getName() + nutritionModel.getType());*/



            // nutritionList.add(new NutritionModel(jsonObject.get("name").toString(),jsonObject.get("type").toString()));
        }
        RecyclerView recyclerView = findViewById(R.id.nutritionRecyclerWL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CaptionedAdapterNutritionWL adapter = new CaptionedAdapterNutritionWL(names,types,urls);
        recyclerView.setAdapter(adapter);

    }
}