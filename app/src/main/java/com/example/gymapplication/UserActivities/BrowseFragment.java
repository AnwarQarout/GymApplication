package com.example.gymapplication.UserActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymapplication.Models.MembershipPreferenceModel;
import com.example.gymapplication.R;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrowseFragment extends Fragment {
    SharedPreferences sharedPreferences;
    Gson gson = new Gson();
    String jsonObject;
    MembershipPreferenceModel membershipPreferenceModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BrowseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrowseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrowseFragment newInstance(String param1, String param2) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.browse_fragment,
                container, false);
        Button massGainBt = (Button) rootView.findViewById(R.id.MassGainBt);
        Button weightGainBt = (Button) rootView.findViewById(R.id.weightGainBt);
        Button weightLossBt = (Button) rootView.findViewById(R.id.weightLossBt);
        massGainBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                massGainBtClick();
            }
        });

        weightGainBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGainBtClick();
            }
        });

        weightLossBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightLossBtClick();
            }
        });


        return rootView;
    }

    /* cant findViewById in fragment OnCreate, so this method makes it work. */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        gson = new Gson();
        jsonObject = sharedPreferences.getString("membershipPreferenceModel", "");
        membershipPreferenceModel = gson.fromJson(jsonObject, MembershipPreferenceModel.class);

    }

    public void massGainBtClick( ){
        if(!membershipPreferenceModel.isMassGain()){
            Toast.makeText(getActivity(), "You are not enrolled in this membership",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getActivity(), MassGainActivity.class);
            intent.putExtra("selectedMembership","MassGain");
            startActivity(intent);
        }
    }

    public void weightGainBtClick( ){
        if(!membershipPreferenceModel.isWeightGain()){
            Toast.makeText(getActivity(), "You are not enrolled in this membership",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getActivity(), WeightGainActivity.class);
            intent.putExtra("selectedMembership","WeightGain");
            startActivity(intent);
        }

    }

    public void weightLossBtClick( ){

        if(!membershipPreferenceModel.isWeightLoss()){
            Toast.makeText(getActivity(), "You are not enrolled in this membership",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getActivity(), WeightLossActivity.class);
            intent.putExtra("selectedMembership","WeightLoss");
            startActivity(intent);
        }

    }

    }

