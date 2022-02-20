package com.example.gymapplication.UserActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gymapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WeightGainActivity extends AppCompatActivity {
    WeightGainNutritionFragment weightGainNutritionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_gain_activity);

        weightGainNutritionFragment = WeightGainNutritionFragment.newInstance("","");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_WG);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_WG, new WeightGainNutritionFragment()).commit();


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
                case R.id.nutritionWG:
                    selectedFragment = new WeightGainNutritionFragment();
                    break;
                case R.id.workoutsWG:
                    selectedFragment = new WeightGainWorkoutFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_WG, selectedFragment)
                    .commit();
            return true;
        }
    };

}