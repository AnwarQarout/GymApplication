package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymapplication.Models.MembershipPreferenceModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserInfoActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText name;
    EditText phone;
    EditText height;
    EditText weight;
    Button apply;

    String ip = "192.168.1.19:80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_info_activity);

         username = findViewById(R.id.UsernameET);
         username.setText(getIntent().getExtras().getString("username"));

         password = findViewById(R.id.PasswordET);
         password.setText(getIntent().getExtras().getString("password"));

         name = findViewById(R.id.NameET);
         name.setText(getIntent().getExtras().getString("name"));

         phone = findViewById(R.id.PhoneET);
         phone.setText(getIntent().getExtras().getString("phone"));

         height = findViewById(R.id.HeightET);
         height.setText(String.valueOf(getIntent().getExtras().getDouble("height")));

         weight = findViewById(R.id.WeightET);
         weight.setText(String.valueOf(getIntent().getExtras().getDouble("weight")));

         apply = findViewById(R.id.applyBtn);
    }
    public void applyBtnClick(View view){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String enteredUsername = sharedPreferences.getString("username",null);
        String enteredPassword = password.getText().toString();
        String enteredName = name.getText().toString();
        //enteredName = enteredName.replace(" ","-");
        String enteredPhone = phone.getText().toString();
        double enteredHeight = Double.parseDouble(height.getText().toString());
        double enteredWeight = Double.parseDouble(weight.getText().toString());

        System.out.println(enteredUsername);
        System.out.println(enteredWeight);
        System.out.println(enteredName);

        applyBtn( enteredUsername,  enteredPassword,  enteredName,  enteredPhone,  enteredHeight,  enteredWeight);
    }
    public void applyBtn(String enteredUsername, String enteredPassword, String enteredName, String enteredPhone, double enteredHeight, double enteredWeight){
    /*
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String enteredUsername = sharedPreferences.getString("username",null);
        String enteredPassword = password.getText().toString();
        String enteredName = name.getText().toString();
        enteredName = enteredName.replace(" ","");
        String enteredPhone = phone.getText().toString();
        double enteredHeight = Double.parseDouble(height.getText().toString());
        double enteredWeight = Double.parseDouble(weight.getText().toString());

        System.out.println(enteredUsername);
        System.out.println(enteredWeight);*/
     //   String URL = "http://"+ip+"/gymproject/updateUsernameInfo.php?enteredUsername=" + enteredUsername + "&enteredPassword=" + enteredPassword + "&enteredName="+enteredName+"&enteredPhone="+enteredPhone+"&enteredHeight="+enteredHeight+"&enteredWeight="+enteredWeight;
        String URL = "http://"+ip+"/gymproject/updateUsernameInfo.php";
        System.out.println(URL);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("-------------");
                        System.out.println(response);
                            Toast.makeText(UpdateUserInfoActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(UpdateUserInfoActivity.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("enteredUsername", enteredUsername);
                params.put("enteredPassword", enteredPassword);
                params.put("enteredName", enteredName);
                params.put("enteredPhone", enteredPhone);
                params.put("enteredHeight", String.valueOf(enteredHeight));
                params.put("enteredWeight", String.valueOf(enteredWeight));

                // at last we are returning our params.
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}