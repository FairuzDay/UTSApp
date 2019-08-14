package com.unikom.ujiantengahsemester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unikom.ujiantengahsemester.models.User;
import com.unikom.ujiantengahsemester.viewModel.UserViewModel;

import java.util.List;
/**tanggal : 11-08-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */


public class LoginActivity extends AppCompatActivity {

    private EditText mInputUsername;
    private EditText mInputPassword;
    private Button mBtnSignin;
    private Button mBtnSignup;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mInputUsername = findViewById(R.id.login_username);
        mInputPassword = findViewById(R.id.login_password);
        mBtnSignin = findViewById(R.id.btn_login);
        mBtnSignup = findViewById(R.id.btn_signup);

        userViewModel = ViewModelProviders.of(LoginActivity.this).get(UserViewModel.class);
        mBtnSignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSignUp();
                    }
                });

        mBtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userViewModel.getAllUser().observe(LoginActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        boolean getUser = true;
                        for (int i = 0; i < users.size(); i++) {
                            Log.d("Boom List", users.get(i).getPassword()+" onChanged: "+users.get(i).getUsername());
                            if (users.get(i).getUsername().equals(mInputUsername.getText().toString())
                                    && users.get(i).getPassword().equals(mInputPassword.getText().toString())){
                                setSharedPreferences(mInputUsername.getText().toString(), mInputPassword.getText().toString());
                                getUser = true;
                                break;
                            }else{
                                getUser = false;
                            }
                        }
                        if (!getUser){
                            Toast.makeText(LoginActivity.this, "There is no User like "+mInputUsername.getText().toString(), Toast.LENGTH_SHORT).show();
                            mInputPassword.setText("");
                            mInputUsername.setText("");
                        }
                    }
                });

            }
        });

        SharedPreferences sharedPref = (SharedPreferences) this.getSharedPreferences( "com.unikom.ujiantengahsemester", Context.MODE_PRIVATE);
        final String spUsername = sharedPref.getString("username", "none");
        final String spPassword = sharedPref.getString("password", "none");
        Log.d("BoomCheck", "onCreate: "+spUsername);
        if (!spPassword.equals("none") && !spUsername.equals("none")){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    private void showSignUp(){
        final Dialog d = new Dialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_signup, null, false);

        final EditText username, password, repassword;
        Button btnSignup;

        username = v.findViewById(R.id.singup_username);
        password = v.findViewById(R.id.singup_password);
        repassword = v.findViewById(R.id.singup_repassword);
        btnSignup = v.findViewById(R.id.btn_signup_popup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(repassword.getText().toString())){
                    String nim = String.valueOf((Math.random() * 999999) + 1);
                    Log.d("Boom nim", "onClick: "+nim);
                    User u = new User(nim,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            username.getText().toString(),
                            password.getText().toString());
                    userViewModel.insertUser(u);
                    Toast.makeText(LoginActivity.this, "User "+username.getText().toString()+" Cretated. Now You Can Login", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                }else {
                    Toast.makeText(LoginActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    repassword.setText("");
                }
            }
        });
        d.setContentView(v);
        d.show();
    }

    private void setSharedPreferences(String uname, String pass){
        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("com.unikom.ujiantengahsemester", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", uname);
        editor.putString("password", pass);
        editor.apply();
        editor.commit();
        Toast.makeText(this, "Welcome "+uname, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
