package com.unikom.ujiantengahsemester.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
public class FriendFragment extends Fragment {

    private RecyclerView listFriend;
    private FriendAdapter friendAdapter;
    private ArrayList<Friends> friendsData;
    private FloatingActionButton fab ;

    public FriendFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        listFriend = view.findViewById(R.id.list_view);
        fab = view.findViewById(R.id.fab_add);

        friendsData = new ArrayList<>();
        Friends f0 = new Friends("10116567","Frangky  Michael", "IF-13", "089680517559", "frangkimichael97@gmail.com", "frangkimichael");
        Friends f1 = new Friends("100516238","Awan Gustiawan", "IS-06", "08922716231", "awan@gmail.com", "awangustiawan");
        Friends f2 = new Friends("10116561","Egi Widianto", "IF-13", "08218261628", "egiwidianto@gmail.com", "egiwidianto");
        friendsData.add(f0);
        friendsData.add(f1);
        friendsData.add(f2);
        friendAdapter = new FriendAdapter(friendsData, getContext(), new FriendAdapter.ClickHandler() {
            @Override
            public void onItemClick(int position) {
                showOption(position, friendsData.get(position));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(-1, null);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listFriend.setLayoutManager(linearLayoutManager);
        listFriend.setAdapter(friendAdapter);
        return view;
    }
    private void showOption(final int position, final Friends friends){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] action = {"Edit","Delete"};
        builder.setTitle("Share This !");
        builder.setItems(action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setData(position, friends);
                }
                else {
                    deleteData(position);
                }
            }
        });
        builder.create().show();

    }
    private void setData(final int i, Friends friends){
        final Dialog d = new Dialog(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit, null, false);

        final EditText eNim, eName, eClass, ePhone, eEmail, eSosmed;
        Button btnSave;

        eNim = v.findViewById(R.id.edit_nim);
        eName = v.findViewById(R.id.edit_name);
        eClass = v.findViewById(R.id.edit_class);
        ePhone = v.findViewById(R.id.edit_phone);
        eEmail = v.findViewById(R.id.edit_email);
        eSosmed = v.findViewById(R.id.edit_sosmed);
        btnSave = v.findViewById(R.id.save_edit);

        if (i != -1) {
            eNim.setText(friends.getFnim());
            eName.setText(friends.getFname());
            eClass.setText(friends.getFclass());
            ePhone.setText(friends.getFphone());
            eEmail.setText(friends.getFemail());
            eSosmed.setText(friends.getFsosmed());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eNim.getText().toString().isEmpty() &&
                        eName.getText().toString().isEmpty() &&
                        eClass.getText().toString().isEmpty() &&
                        ePhone.getText().toString().isEmpty() &&
                        eEmail.getText().toString().isEmpty() &&
                        eSosmed.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Fill The Field", Toast.LENGTH_SHORT).show();
                }else {
                    Friends newFriend = new Friends(
                            eNim.getText().toString(),
                            eName.getText().toString(),
                            eClass.getText().toString(),
                            ePhone.getText().toString(),
                            eEmail.getText().toString(),
                            eSosmed.getText().toString()
                    );
                    if (i != -1) {
                        friendsData.set(i, newFriend);
                    }else {
                        friendsData.add(newFriend);
                    }
                    listFriend.setAdapter(friendAdapter);
                    ((FriendAdapter) friendAdapter).notifyDataSetChanged();
                    d.dismiss();
                }
            }
        });
        d.setContentView(v);
        d.show();
    }
    private void deleteData(int position){
        friendsData.remove(position);
        friendAdapter.notifyDataSetChanged();
    }
}