package com.unikom.ujiantengahsemester.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikom.ujiantengahsemester.LoginActivity;
import com.unikom.ujiantengahsemester.R;
import com.unikom.ujiantengahsemester.models.User;
import com.unikom.ujiantengahsemester.viewModel.UserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
/**tanggal : 11-08-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */
public class HomeFragment extends Fragment {

    private ImageView mUserPict;
    private TextView textNim;
    private TextView textName;
    private TextView textDesc;
    private UserViewModel userViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUserPict = view.findViewById(R.id.profile_pict);
        textNim = view.findViewById(R.id.text_nim);
        textName = view.findViewById(R.id.text_name);
        textDesc = view.findViewById(R.id.text_description);

        //SharedPreferences
        SharedPreferences sharedPref = (SharedPreferences) getActivity().getSharedPreferences( "com.unikom.ujiantengahsemester",Context.MODE_PRIVATE);
        final String spUsername = sharedPref.getString("username", "none");
        final String spPassword = sharedPref.getString("password", "none");

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users) {
                    if (item.getUsername().equals(spUsername) && item.getPassword().equals(spPassword)){
                        setMyData();
                        break;
                    }
                }
            }
        });

        return view;
    }

    private void setMyData(){
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User item : users) {
                    Log.d("Boom Fairuz List", "onChanged: "+item.getFname());
                    if (item.getUsername().equals("fairuzday") && item.getPassword().equals("fairuzday")){
                        Log.d("Boom Fairuz", "onChanged: "+item.getFname());
                        mUserPict.setImageDrawable(getResources().getDrawable(Integer.parseInt(item.getUpict())));
                        textNim.setText(item.getFnim());
                        textName.setText(item.getFname());
                        textDesc.setText(item.getUdesc());
                    }
                }
            }
        });
    }
}
