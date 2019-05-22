package com.unikom.ujiantengahsemester.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unikom.ujiantengahsemester.R;
import com.unikom.ujiantengahsemester.adapters.FriendAdapter;
import com.unikom.ujiantengahsemester.models.Friends;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
/**tanggal : 21-05-2019
*Nim     : 10116568
*Nama    : Muh.Fairuz Hadi Day
*Kelas    : IF-13
 */
public class ContactFragment extends Fragment {

    public ContactFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        return view;
    }

}
