package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPageForPatient extends AppCompatActivity {
    EditText firstName, lastName, username, password, phoneNumber, address, healthCardNumber;
    private Button registerButtonForPatient, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page_for_patient);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        healthCardNumber = findViewById(R.id.healthCardNumber);
        registerButtonForPatient = findViewById(R.id.registerButtonForPatient);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openWelcome();

            }
        });

        registerButtonForPatient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!validateFirstName() || !validateLastName() || !validateUsername() || !validatePassword() || !validatePhoneNumber() || !validateAddress() || !validateHealthCardNumber()){
                } else {
                    registerPatient();
                }

            }
        });
    }
    // Validating Different Characteristics of Patient
    public boolean validateFirstName() {
        String val = firstName.getText().toString();
        if (val.isEmpty()) {
            firstName.setError("First name must not be empty");
            return false;
        } else {
            firstName.setError(null);
            return true;
        }
    }

    public boolean validateLastName() {
        String val = lastName.getText().toString();
        if (val.isEmpty()) {
            lastName.setError("Last name must not be empty");
            return false;
        } else {
            lastName.setError(null);
            return true;
        }
    }

    public boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Username must not be empty");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Password must not be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public boolean validateAddress() {
        String val = address.getText().toString();
        if (val.isEmpty()) {
            address.setError("Address must not be empty");
            return false;
        } else {
            address.setError(null);
            return true;
        }
    }

    public boolean validatePhoneNumber() {
        String val = phoneNumber.getText().toString();
        if (val.isEmpty()) {
            phoneNumber.setError("Phone number must not be empty");
            return false;
        } else {
            phoneNumber.setError(null);
            return true;
        }
    }

    public boolean validateHealthCardNumber() {
        String val = healthCardNumber.getText().toString();
        if (val.isEmpty()) {
            healthCardNumber.setError("Health card number must not be empty");
            return false;
        } else {
            healthCardNumber.setError(null);
            return true;
        }
    }
    // RegistrationPage switches into loginPage
    public void registerPatient() {
        Intent intent = new Intent(this, LoginPage.class );
        startActivity(intent);
    }
    // returns to homepage from RegistrationPage
    public void openWelcome() {
        Intent intentLogin = new Intent(this, WelcomePage.class);
        startActivity(intentLogin);
    }
}

