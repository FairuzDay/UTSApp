package com.unikom.ujiantengahsemester.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unikom.ujiantengahsemester.LoginActivity;
import com.unikom.ujiantengahsemester.R;

public class SignoutFragment extends Fragment {

    Button mBtnSignout;

    public SignoutFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signout, container, false);

        mBtnSignout = v.findViewById(R.id.btn_signout);

        SharedPreferences sharedPref = (SharedPreferences) getActivity().getSharedPreferences( "com.unikom.ujiantengahsemester", Context.MODE_PRIVATE);
        final String spUsername = sharedPref.getString("username", "none");
        final String spPassword = sharedPref.getString("password", "none");


        mBtnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSharedPreferences();
                Toast.makeText(getContext(), "Thank You "+spUsername, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void setSharedPreferences(){
        SharedPreferences sharedPref =  getActivity().getSharedPreferences("com.unikom.ujiantengahsemester", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", "none");
        editor.putString("password", "none");
        editor.apply();
        editor.commit();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }
}
