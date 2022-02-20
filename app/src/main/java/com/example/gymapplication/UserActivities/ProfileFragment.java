package com.example.gymapplication.UserActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gymapplication.Models.MemberModel;
import com.example.gymapplication.Models.MembershipPreferenceModel;
import com.example.gymapplication.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    List<String> list;
    String username;
    String ip = "10.0.2.2:80";
    /* String password;
     String name;
     String phone;
     String image;
     double height;
     double weight;*/
    MemberModel memberModel = new MemberModel();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /* public void onStart(){
         super.onStart();
         parentActivity = getActivity();
     }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        username = getActivity().getIntent().getExtras().getString("username");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String jsonObject = sharedPreferences.getString("membershipPreferenceModel", "");
        MembershipPreferenceModel membershipPreferenceModel = gson.fromJson(jsonObject, MembershipPreferenceModel.class);

        System.out.println(membershipPreferenceModel.toString());

        list = new ArrayList<>();

        if (membershipPreferenceModel.isMassGain()) {
            list.add("Mass Gain");
        }
        if (membershipPreferenceModel.isWeightGain()) {
            list.add("Weight Gain");
        }
        if (membershipPreferenceModel.isWeightLoss()) {
            list.add("Weight Loss");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.profile_fragment,
                container, false);
        Button button = (Button) rootView.findViewById(R.id.updateBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBtnClick();
            }
        });
        return rootView;
    }




 /*   @Override
 public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }*/


    /* cant findViewById in fragment OnCreate, so this method makes it work. */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ArrayAdapter<String> adapter = new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,list){
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

                // Generate ListView Item using TextView
                return view;
            }
        };
        ListView listView = Objects.requireNonNull(getView()).findViewById(R.id.preferenceListView);
        listView.setAdapter(adapter);

        TextView username = getView().findViewById(R.id.usernameTV);
        TextView password = getView().findViewById(R.id.passwordTV);
        TextView name = getView().findViewById(R.id.nameTV);
        TextView phone = getView().findViewById(R.id.phoneTV);
        TextView height = getView().findViewById(R.id.heightTV);
        TextView weight = getView().findViewById(R.id.weightTV);
        TextView bmi = getView().findViewById(R.id.bmiTV);
        Button button = getView().findViewById(R.id.updateBtn);




        System.out.println("sending request now..");
        memberModel = sendRequestAndGetUserInfo();
        System.out.println("request sent!");

        /* to make the code wait for the other thread (sendRequestAndGetUserInfo) to fetch data before continuing */
        Runnable r = new Runnable() {
            @Override
            public void run(){
                System.out.println(memberModel.getUsername());
                System.out.println(memberModel.getHeight());

                username.setText(memberModel.getUsername());
                password.setText(memberModel.getPassword());
                name.setText(memberModel.getName());
                phone.setText(memberModel.getPhone());
                height.setText(String.valueOf(memberModel.getHeight()));
                weight.setText(String.valueOf(memberModel.getWeight()));
                bmi.setText(String.valueOf(memberModel.calculateBMI()));
            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 100);



      //  image.setImageDrawable(R.drawable.something);



    }

    public MemberModel sendRequestAndGetUserInfo(){
        MemberModel member = new MemberModel();
        System.out.println("ENTERED FUNCTION!!!");
        String URL = "http://"+ip+"/gymproject/getUsernameInfo.php?enteredUsername=" + username;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getBaseContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {



                ArrayList<String> books = new ArrayList<>();
                    try {
                        JSONObject obj = response.getJSONObject(0);
                        member.setUsername(username);
                        member.setPassword(obj.getString("password"));
                        member.setName(obj.getString("name"));
                        member.setPhone(obj.getString("phone").toString());
                        member.setImage(obj.getString("image"));
                        member.setHeight(Double.parseDouble(obj.getString("height")));
                        member.setWeight(Double.parseDouble(obj.getString("weight")));
                       System.out.println(member.getName() + memberModel.getPassword());
                        System.out.println(obj.getString("password"));

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

        return member;
    }


    public void updateBtnClick(){


                Intent intent = new Intent(getActivity(), UpdateUserInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("username", memberModel.getUsername());
                intent.putExtra("password", memberModel.getPassword());
                intent.putExtra("name", memberModel.getName());
                intent.putExtra("phone", memberModel.getPhone());
                intent.putExtra("height", memberModel.getHeight());
                intent.putExtra("weight", memberModel.getWeight());
                startActivity(intent);

    }

    }