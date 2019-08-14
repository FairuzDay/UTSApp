package com.unikom.ujiantengahsemester;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.unikom.ujiantengahsemester.fragments.ContactFragment;
import com.unikom.ujiantengahsemester.fragments.FriendFragment;
import com.unikom.ujiantengahsemester.fragments.HomeFragment;
import com.unikom.ujiantengahsemester.fragments.SignoutFragment;

/**tanggal : 21-05-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(listenerBottom);

        SharedPreferences sharedPref = (SharedPreferences) this.getSharedPreferences( "com.unikom.ujiantengahsemester", Context.MODE_PRIVATE);
        final String spUsername = sharedPref.getString("username", "none");
        final String spPassword = sharedPref.getString("password", "none");
        if (spPassword.equals("none") && spUsername.equals("none")){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listenerBottom = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment f = new HomeFragment();
            loadFragment(f);
            switch (item.getItemId()){
                case R.id.home_menu:
                    f = new HomeFragment();
                    loadFragment(f);
                    return true;
                case R.id.contact_menu:
                    f = new ContactFragment();
                    loadFragment(f);
                    return true;
                case R.id.friend_menu:
                    f = new FriendFragment();
                    loadFragment(f);
                    return true;
                case R.id.signout_menu:
                    f = new SignoutFragment();
                    loadFragment(f);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment f){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
