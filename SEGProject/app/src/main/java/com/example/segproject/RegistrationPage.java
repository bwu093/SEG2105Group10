package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.RequiresApi;
@RequiresApi(api = Build.VERSION_CODES.P)

public class RegistrationPage extends AppCompatActivity{
    private Button registerButtonForPatient, registerButtonForDoctor;
    // This code enables you to go to registration page for patient or registration page for doctor
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        registerButtonForPatient = findViewById(R.id.registerButtonForPatient);
        registerButtonForPatient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openRegistrationPageForPatient();
            }

        });

        registerButtonForDoctor = findViewById(R.id.registerButtonForDoctor);
        registerButtonForDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
          openRegistrationPageForDoctor();
    }
        });
      
    }
        // goes to registration page for patient
        public void openRegistrationPageForPatient(){
            Intent intentRegistrationForPatient = new Intent(this, RegistrationPageForPatient.class);
            startActivity(intentRegistrationForPatient);
        }
        // goes to registration page for doctor

        public void openRegistrationPageForDoctor(){
            Intent intentRegistrationForDoctor = new Intent(this, RegistrationPageForDoctor.class);
            startActivity(intentRegistrationForDoctor);
        }
}


