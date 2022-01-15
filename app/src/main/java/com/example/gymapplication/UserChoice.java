package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.gymapplication.Models.MembershipPreferenceModel;
import com.google.gson.Gson;

public class UserChoice extends AppCompatActivity {
    CheckBox weightLoss;
    CheckBox weightGain;
    CheckBox massGain;
    String username;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice_activity);

        weightLoss = findViewById(R.id.weightLoss);
        weightGain = findViewById(R.id.weightGain);
        massGain = findViewById(R.id.massGain);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        if(sharedPreferences.contains("membershipPreferenceModel")) {
            String membershipPreferenceModelString = sharedPreferences.getString("membershipPreferenceModel", "");
            Gson gson = new Gson();
            MembershipPreferenceModel membershipPreferenceModel = gson.fromJson(membershipPreferenceModelString, MembershipPreferenceModel.class);

              if(membershipPreferenceModel.isWeightGain()){
            weightGain.setChecked(true);
            }
            if(membershipPreferenceModel.isWeightLoss()){
            weightLoss.setChecked(true);
             }

            if(membershipPreferenceModel.isMassGain()){
            massGain.setChecked(true);
            }
        }


        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        System.out.println(username);
    }


    public void proceedBtnClick(View view){
        MembershipPreferenceModel membershipPreferenceModel = new MembershipPreferenceModel();

        if(weightLoss.isChecked()){
            membershipPreferenceModel.setWeightLoss(true);
        }
        if(weightGain.isChecked()){
            membershipPreferenceModel.setWeightGain(true);
        }
        if(massGain.isChecked()){
            membershipPreferenceModel.setMassGain(true);
        }

        Gson gson = new Gson();
        String dataString = gson.toJson(membershipPreferenceModel);

        editor.putString("membershipPreferenceModel",dataString);
        editor.putString("username",username);
        editor.apply();

        Intent intent = new Intent(this,userActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);


    }


}