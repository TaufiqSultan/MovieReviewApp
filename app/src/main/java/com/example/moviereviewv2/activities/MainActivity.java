package com.example.moviereviewv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviereviewv2.R;

public class MainActivity extends AppCompatActivity {
    MovieReviewDatabaseHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DB = new MovieReviewDatabaseHelper(this);
    }
    //Login Button
    public void onClickLogin (View view) {
        EditText username = (EditText) findViewById(R.id.userName);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.userPassword);
        String passwordValue = String.valueOf(password.getText());

        String notification = "";

        if (usernameValue.isEmpty()) {
            notification = "Vul a.u.b. eerst een email en wachtwoord in.";
        } else {
            Boolean checkUserPass = DB.checkCredentials(usernameValue);
            if (checkUserPass) {
                notification = "Welcome " + usernameValue;
                loadHomescreenActivity();
            } else {
                notification = "Email of password niet correct!";
            }
        }
        Toast.makeText(this, notification, Toast.LENGTH_SHORT).show();
    }
    //Redirect to homescreen after login
    public void loadHomescreenActivity() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    //Continue to homescreen button without account
    public void onClickContinue(View view){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
    //Create Account Button
    public void onClickAccount(View view){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

}
