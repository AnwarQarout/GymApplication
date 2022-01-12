package com.example.gymapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MassGainActivity extends AppCompatActivity {
    MassGainNutritionFragment massGainNutritionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mass_gain_activity);

        massGainNutritionFragment = MassGainNutritionFragment.newInstance("","");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_MG);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_MG, new MassGainNutritionFragment()).commit();


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
                case R.id.nutritionMG:
                    selectedFragment = new MassGainNutritionFragment();
                    break;
                case R.id.workoutsMG:
                    selectedFragment = new MassGainWorkoutFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_MG, selectedFragment)
                    .commit();
            return true;
        }
    };

}