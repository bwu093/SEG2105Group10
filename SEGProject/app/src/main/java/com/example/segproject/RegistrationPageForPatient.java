package com.example.segproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationPageForPatient extends AppCompatActivity {
    EditText firstName, lastName, username, password, phoneNumber, address, healthCardNumber;
    private Button registerButtonForPatient, backButton;
    TextView viewSuccessInfo;
    LinearLayout layoutError;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page_for_patient);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

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
                String validateFirstName = firstName.getText().toString().trim();
                if (validateFirstName.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing name");
                    return;
                }
                String validateLastName = lastName.getText().toString().trim();
                if (validateLastName.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing name");
                    return;

                }

                String validateUsername = username.getText().toString().trim().toLowerCase();
                if(validateUsername.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing email");
                    return;
                }

                String hashedEmail = hashEmail(validateUsername);

                reference.child("users").child(hashedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            layoutError.setVisibility(View.VISIBLE);
                            viewSuccessInfo.setText("Email already taken");
                        } else{
                            layoutError.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        layoutError.setVisibility(View.VISIBLE);
                        viewSuccessInfo.setText("Error checking email");
                    }
                });

                String validatePassword = password.getText().toString().trim();
                if(validatePassword.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing password");
                    return;
                }

                String validatePhoneNumber = phoneNumber.getText().toString().trim();
                if(validatePhoneNumber.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing phone number");
                    return;
                }

                String validateAddress = address.getText().toString().trim();
                if(validateAddress.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing address");
                    return;
                }

                String validateHealthCardNumber = healthCardNumber.getText().toString().trim();
                if(validateHealthCardNumber.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing health card info");
                    return;
                }

                if (validateHealthCardNumber.contains(".") || validateHealthCardNumber.contains("#") || validateHealthCardNumber.contains("$") || validateHealthCardNumber.contains("[") ||  validateHealthCardNumber.contains("]")) {
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Invalid health card info");
                    return;
                }

                String databasePath = "users/" + hashEmail(validateUsername);

                DatabaseReference userRef = database.getReference(databasePath);

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            layoutError.setVisibility(View.VISIBLE);
                            viewSuccessInfo.setText("Email already taken");
                        } else {
                            layoutError.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        layoutError.setVisibility(View.VISIBLE);
                        viewSuccessInfo.setText("Error checking email");
                    }
                });

                User newPatient = new Patient(validateHealthCardNumber, validateUsername, validatePassword, validateFirstName, validateLastName, validatePhoneNumber, validateAddress, validateHealthCardNumber);
                String healthCardValue = healthCardNumber.getText().toString();
                DatabaseReference patientRef = database.getReference("patients").child(String.valueOf(healthCardValue));
                patientRef.setValue(newPatient);
            }
        });

    }


    private String hashEmail(String email) {
        return email.replace(".", "_").replace("@", "_");
    }

    // Directs to one of the layout pages including the Welcome page, the Rejected page, and the Pending page as dictated by the admin.

    public void adminExaminesTheRegistration(String employeeNumber, String registerCondition){
        DatabaseReference doctorRef = database.getReference("doctors").child(String.valueOf(healthCardNumber));
        if("ApprovalByAdmin".equals(registerCondition)) {
            doctorRef.setValue("ApprovedByAdmin");
            openWelcome();}
        else if ("RejectionByAdmin".equals(registerCondition)) {
            doctorRef.setValue("RejectedByAdmin");
            openRejectedPage();}
        else if("PendingForAdmin".equals(registerCondition)) {
            doctorRef.setValue("Pending");
            openPendingPage();}
        }

    // RegistrationPage switches into loginPage
    public void registerPatient() {
        Intent intent = new Intent(this, LoginPage.class );
        startActivity(intent);
    }
    // returns to homepage from RegistrationPage
    public void openWelcome() {
        Intent intentLogin = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentLogin = new Intent(this, WelcomePage.class);
        }
        startActivity(intentLogin);
    }

    public void openPendingPage() {
        setContentView(R.layout.registration_pending_by_administrator);
    }

    public void openRejectedPage(){
        setContentView(R.layout.registration_rejected_by_administrator);
    }
}

