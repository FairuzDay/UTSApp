package com.unikom.ujiantengahsemester.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unikom.ujiantengahsemester.R;

/**
 * A simple {@link Fragment} subclass.
 */
/**tanggal : 21-05-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
