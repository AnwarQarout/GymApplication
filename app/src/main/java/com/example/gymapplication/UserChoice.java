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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice_activity);

        weightLoss = findViewById(R.id.weightLoss);
        weightGain = findViewById(R.id.weightGain);
        massGain = findViewById(R.id.massGain);

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

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