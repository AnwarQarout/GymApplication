package com.example.gymapplication.UserActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymapplication.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightLossNutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MassGainWorkoutFragment extends Fragment {
    String ip = "10.0.2.2:80";
    String selectedMembership;
    ArrayList<String> nutritionList;
    HashSet<String> hashSet = new HashSet<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MassGainWorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeightLossNutritionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MassGainWorkoutFragment newInstance(String param1, String param2) {
        MassGainWorkoutFragment fragment = new MassGainWorkoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedMembership = getActivity().getIntent().getExtras().getString("selectedMembership");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mass_gain_workout_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView calendarView = getView().findViewById(R.id.calendarViewWorkoutMG);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                hashSet.clear();
                Intent intent = new Intent(getActivity().getBaseContext(),MassGainWorkoutActivity.class);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String identifier = String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth) + "WL-Workout";
                System.out.println(identifier);
                boolean isExist = sharedPreferences.contains(identifier);

                long lastUpdate = sharedPreferences.getLong("LAST_UPDATE", -1);
                if(lastUpdate == -1) {
                    sharedPreferences.edit().putLong("LAST_UPDATE", System.currentTimeMillis()).apply();
                } else {
                    boolean isMoreThanAMonth = System.currentTimeMillis() - sharedPreferences.getLong("LAST_UPDATE", 0) >= 2.628e+9;
                    if(isMoreThanAMonth) {
                        sharedPreferences.edit().clear().apply();
                    }
                }



                if(isExist == false){
                    System.out.println("this day wasnt previously visited");
                    sendRequestAndGetWorkouts();

                    Runnable r = new Runnable() {
                        @Override
                        public void run(){
                            System.out.println("hi");
                            System.out.println(hashSet.toString());
                            editor.putStringSet(identifier,hashSet);
                            editor.apply();
                            intent.putExtra("identifier",identifier);
                            startActivity(intent);
                        }
                    };

                    Handler h = new Handler();
                    h.postDelayed(r, 200);


                }
                else {
                    System.out.println("this day was previously visited");
                    System.out.println(hashSet.toString());
                    intent.putExtra("identifier",identifier);
                    startActivity(intent);
                }
                        /*Gson gson = new Gson();
                JsonObject jsonObject = sharedPreferences.getString("membershipPreferenceModel", "");
                membershipPreferenceModel = gson.fromJson(jsonObject, MembershipPreferenceModel.class);*/

            }
        });
    }

    public void sendRequestAndGetWorkouts(){
        String URL = "http://"+ip+"/gymproject/getWorkouts.php?selectedMembership=" + selectedMembership;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getBaseContext());
        Gson gson = new Gson();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i< response.length();++i) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        hashSet.add(obj.toString());
                        System.out.println("yooooooooooooooooooooooooooo");
                        System.out.println(obj.toString());

                    } catch (JSONException exception) {
                        Log.d("Error", exception.toString());
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(request);
    }
}

