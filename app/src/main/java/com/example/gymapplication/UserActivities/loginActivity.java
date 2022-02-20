package com.example.gymapplication.UserActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymapplication.R;

public class loginActivity extends AppCompatActivity {
Context context;
    EditText usernameET;
    EditText passwordET;
    Switch rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
String ip = "10.0.2.2:80";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

         usernameET = findViewById(R.id.username);
         passwordET = findViewById(R.id.password);
         rememberMe = findViewById(R.id.switch1);
         rememberMe.setChecked(true);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        if(sharedPreferences.contains("loginUsername")){
            usernameET.setText(sharedPreferences.getString("loginUsername",""));
            passwordET.setText(sharedPreferences.getString("loginPassword",""));
        }

        context = this;
    }


    /* Method that uses Volley to send a GET request to a web service for login. */
    public void loginBtnClick(View view) {


        String enteredUsername = usernameET.getText().toString();
        String enteredPassword = passwordET.getText().toString();

        String URL = "http://"+ip+"/gymproject/login.php?enteredUsername=" + enteredUsername + "&enteredPassword=" + enteredPassword;

        final String[] isAuthenticated = new String[1];
        if(rememberMe.isChecked()){
            editor.putString("loginUsername",enteredUsername);
            editor.putString("loginPassword",enteredPassword);
            editor.apply();
        }
        else {
            editor.remove("loginUsername");
            editor.remove("loginPassword");
            editor.apply();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* start manager interface */
                        System.out.println(response);
                        System.out.println("-----------------------------");
                        if(response.contains("manager")) {
                            System.out.println(response + "------------------------------ssss----------");
                            String[] split = response.toString().split(":");
                            int id = Integer.parseInt(split[1]);
                            System.out.println(id);
                            Intent intent = new Intent(getApplicationContext(), UserChoice.class); //ManagerHomeActivity
                            intent.putExtra("id",id);
                            intent.putExtra("username",enteredUsername);
                            intent.putExtra("password",enteredPassword);
                            startActivity(intent);
                        }

                        /* start employee interface */
                        else if(response.contains("employee")) {
                            System.out.println(response + "------------------------------ssss----------");
                            String[] split = response.toString().split(":");
                            int id = Integer.parseInt(split[1]);
                            System.out.println(id);
                            Intent intent = new Intent(getApplicationContext(), UserChoice.class); //EmployeeHomeActivity
                            intent.putExtra("id",id);
                            intent.putExtra("username",enteredUsername);
                            intent.putExtra("password",enteredPassword);
                            startActivity(intent);
                        }

                        /* start user interface */
                        else if(response.contains("user")) {
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
