package com.example.segproject;
//Bill Wu made the basics of this page, waiting for others to fill in the rest

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;


@RequiresApi(api = Build.VERSION_CODES.P)
public class LoginPage extends AppCompatActivity {

    TextView email, titlePage;
    EditText username, password;
    private Button loginButton, backButton;
    //this part made by Bill Wu 300170086
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.username);//nothing for these two yet as code needs to be written
        password = findViewById(R.id.password);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcome();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                } else {
                    checkUsers();
                }
            }
        });

    }

    //this part is work in progress really first if statement makes it so that it will prompt the user to input something
    //sees if the user is real or not
    public Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Username cannot be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    //returns to homepage
    public void openWelcome(){
        Intent intentLogin = new Intent(this, WelcomePage.class);
        startActivity(intentLogin);
    }

    //This part checks if they are a doctor or patient, add in code below, might need two classes for the occupations
    public void checkUsers(){

    }

}