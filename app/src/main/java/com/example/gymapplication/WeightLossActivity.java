package com.example.gymapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WeightLossActivity extends AppCompatActivity {
    WeightLossNutritionFragment weightLossNutritionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_loss_activity);

        weightLossNutritionFragment = WeightLossNutritionFragment.newInstance("","");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_WL);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_WL, new WeightLossNutritionFragment()).commit();


    }

    /*public void profileFragmentUpdateClick(View v){
        profileFragment.updateBtnClick(v,getApplicationContext());
    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nutritionWL:
                    selectedFragment = new WeightLossNutritionFragment();
                    break;
                case R.id.workoutsWL:
                    selectedFragment = new WeightLossWorkoutFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_WL, selectedFragment)
                    .commit();
            return true;
        }
    };

}