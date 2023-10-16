package com.example.segproject;

import java.util.ArrayList;

//this class represents the Patient which extends user
public class Patient extends User{
    private EditText firstNameText, lastNameText, emailText, passwordText, phoneNumberText, addressText, healthCardText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_patient_registration);

        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        phoneNumberText = findViewById(R.id.phoneNumberText);
        addressText = findViewById(R.id.addressText);
        healthCardText = findViewById(R.id.healthCardText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View) {
            String firstName = firstNameText.getText().toString();
            String lastName = lastNameText.getText().toString();
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String phoneNumber = phoneNumberText.getText().toString();
            String address = addressText.getText().toString();
            String healthCard = healthCardText.getText().toString();
        }

        //store all patient info into database

    }

}
