package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.shadow.ShadowRenderer;

import org.json.JSONArray;

public class loginActivity extends AppCompatActivity {
Context context;
String ip = "192.168.1.19:80";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = this;
    }


    /* Method that uses Volley to send a GET request to a web service for login. */
    public void loginBtnClick(View view) {
        EditText usernameET = findViewById(R.id.username);
        EditText passwordET = findViewById(R.id.password);

        String enteredUsername = usernameET.getText().toString();
        String enteredPassword = passwordET.getText().toString();

        String URL = "http://"+ip+"/gymproject/login.php?enteredUsername=" + enteredUsername + "&enteredPassword=" + enteredPassword;

        final String[] isAuthenticated = new String[1];

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* start manager interface */
                        System.out.println(response);
                        System.out.println("-----------------------------");
                        if(response.toString().equals("manager")) {
                            Intent intent = new Intent(getApplicationContext(), userActivity.class);
                            intent.putExtra("username",enteredUsername);
                            intent.putExtra("password",enteredPassword);
                            startActivity(intent);
                        }

                        /* start employee interface */
                        else if(response.equals("employee")) {
                            Intent intent = new Intent(getApplicationContext(), userActivity.class);
                            intent.putExtra("username",enteredUsername);
                            intent.putExtra("password",enteredPassword);
                            startActivity(intent);
                        }

                        /* start user interface */
                        else if(response.equals("user")) {
                            Intent intent = new Intent(getApplicationContext(), UserChoice.class);
                            intent.putExtra("username",enteredUsername);
                            intent.putExtra("password",enteredPassword);
                            startActivity(intent);
                        }

                        else {
                            Toast.makeText(loginActivity.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                        }
                }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(loginActivity.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }
}
