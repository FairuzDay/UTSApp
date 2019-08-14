package com.unikom.ujiantengahsemester.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unikom.ujiantengahsemester.R;
import com.unikom.ujiantengahsemester.adapters.FriendAdapter;
import com.unikom.ujiantengahsemester.models.Friends;
import com.unikom.ujiantengahsemester.viewModel.FriendViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
/**tanggal : 11-08-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */
public class FriendFragment extends Fragment {

    private RecyclerView listFriend;
    private FriendAdapter friendAdapter;
    private FloatingActionButton fab ;
    private FriendViewModel mFriendViewModel;

    public FriendFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        listFriend = view.findViewById(R.id.list_view);
        fab = view.findViewById(R.id.fab_add);

        mFriendViewModel = ViewModelProviders.of(this).get(FriendViewModel.class);
        mFriendViewModel.getAllFriends().observe(this, new Observer<List<Friends>>() {
            @Override
            public void onChanged(final List<Friends> friends) {
                friendAdapter = new FriendAdapter(friends, getContext(), new FriendAdapter.ClickHandler() {
                    @Override
                    public void onItemClick(int position) {
                        showOption(position, friends.get(position));
                    }
                });
                listFriend.setAdapter(friendAdapter);
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
        builder.setTitle("Do Something!");
        builder.setItems(action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setData(position, friends);
                }
                else {
                    deleteData(friends);
                }
            }
        });
        builder.create().show();

    }
    private void setData(final int i, Friends friends){
        final Dialog d = new Dialog(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit, null, false);

        final EditText eNim, eName, eClass, ePhone, eEmail, eSosmed;
        TextView textEdit;
        Button btnSave;

        eNim = v.findViewById(R.id.edit_nim);
        eName = v.findViewById(R.id.edit_name);
        eClass = v.findViewById(R.id.edit_class);
        ePhone = v.findViewById(R.id.edit_phone);
        eEmail = v.findViewById(R.id.edit_email);
        eSosmed = v.findViewById(R.id.edit_sosmed);
        btnSave = v.findViewById(R.id.save_edit);
        textEdit = v.findViewById(R.id.editDialog);

        if (i != -1) {
            textEdit.setText("Edit Data");
            eNim.setText(friends.getFnim());
            eNim.setFocusable(false);
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
                        mFriendViewModel.updateFriend(newFriend);
                    }else {
                        mFriendViewModel.insertFriend(newFriend);
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
    private void deleteData(Friends friends){
        mFriendViewModel.removeFriend(friends);
        friendAdapter.notifyDataSetChanged();
    }
}