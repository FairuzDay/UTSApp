package com.unikom.ujiantengahsemester;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.unikom.ujiantengahsemester.fragments.ContactFragment;
import com.unikom.ujiantengahsemester.fragments.FriendFragment;
import com.unikom.ujiantengahsemester.fragments.HomeFragment;
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
