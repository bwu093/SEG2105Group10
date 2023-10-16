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


public class RegistrationPageForDoctor extends AppCompatActivity {
    EditText firstName, lastName, username, password, phoneNumber, address, employeeNumber, specialties;
    private Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page_for_doctor);


        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        employeeNumber = findViewById(R.id.employeeNumber);
        specialties = findViewById(R.id.specialties);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openWelcome();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!validateFirstName() || !validateLastName() || !validateUsername() || !validatePassword() || !validatePhoneNumber() || !validateAddress() || !validateEmployeeNumber() || !validateSpecialties()){
            } else {
                registerDoctor();
            }

        }
    });
}
// Validating Different Characteristics of Doctor
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

public boolean validateEmployeeNumber() {
    String val = employeeNumber.getText().toString();
    if (val.isEmpty()) {
        employeeNumber.setError("Employee number must not be empty");
        return false;
    } else {
        employeeNumber.setError(null);
        return true;
    }
}

public boolean validateSpecialties() {
    String val = specialties.getText().toString();
    if (val.isEmpty()) {
        specialties.setError("Specialties must not be empty");
        return false;
    } else {
        specialties.setError(null);
        return true;
    }
}
// RegistrationPage switches into loginPage
public void registerDoctor() {
    Intent intent = new Intent(this, LoginPage.class );
    startActivity(intent);
}
// returns to homepage from RegistrationPage
public void openWelcome() {
    Intent intentLogin = new Intent(this, WelcomePage.class);
    startActivity(intentLogin);
}
}


