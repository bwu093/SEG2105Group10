package com.example.segproject;

//this part made by Bill Wu 300170086

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.P)

public class WelcomePage extends AppCompatActivity {

    private Button loginButton, registerButton;
    //@Override
// This code allows you to go to login page or registration page
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }

        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });

    }
    //these two open the respective pages
    public void openLoginPage(){
        Intent intentLogin = new Intent(this, LoginPage.class);
        startActivity(intentLogin);
    }

    public void openRegisterPage(){
        Intent intentRegister = new Intent(this, LoginPage.class);
        startActivity(intentRegister);
    }
}
