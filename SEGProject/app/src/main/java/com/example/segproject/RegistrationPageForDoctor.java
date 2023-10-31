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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;


public class RegistrationPageForDoctor extends AppCompatActivity {
    EditText firstName, lastName, username, password, phoneNumber, address, employeeNumber, specialties;
    TextView viewSuccessInfo;
    LinearLayout layoutError;
    private Button registerButtonForDoctor, backButton, returnDoctor;
    FirebaseDatabase database;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page_for_doctor);



        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        employeeNumber = findViewById(R.id.employeeNumber);
        specialties = findViewById(R.id.specialties);
        registerButtonForDoctor = findViewById(R.id.registerButtonForDoctor);
        backButton = findViewById(R.id.backButton);

        viewSuccessInfo = findViewById(R.id.successInfo);
        layoutError = findViewById(R.id.error_display);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcome();
            }
        });

        returnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDoctor();
            }
        });

        registerButtonForDoctor.setOnClickListener(new View.OnClickListener() {
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

                reference.child(validateUsername).addListenerForSingleValueEvent(new ValueEventListener() {
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

                String validateEmployeeNumber = employeeNumber.getText().toString().trim();
                if(validateEmployeeNumber.isEmpty()){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing employee number");
                    return;
                }
                reference.child(validateEmployeeNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            layoutError.setVisibility(View.VISIBLE);
                            viewSuccessInfo.setText("Number already taken");
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

                String validateSpecialties = specialties.getText().toString();
                String[] specialtiesArray = validateSpecialties.split(",");
                if(specialtiesArray.length == 0){
                    layoutError.setVisibility(View.VISIBLE);
                    viewSuccessInfo.setText("Missing employee number");
                    return;
                }

                User newDoctor = new Doctor(validateEmployeeNumber, validateUsername, validatePassword, validateFirstName, validateLastName, validatePhoneNumber, validateAddress, validateEmployeeNumber, validateSpecialties);
                DatabaseReference doctorRef = database.getReference("doctors").child(String.valueOf(employeeNumber));
                doctorRef.setValue(newDoctor);


        }
    });

}

// Directs to one of the layout pages such as the Welcome page, the Rejected page, and the Pending page as dictated by admin.
    public void adminExaminesTheRegistration(String employeeNumber,  String registerCondition) {
        DatabaseReference doctorRef = database.getReference("doctors").child(String.valueOf(employeeNumber));
        if ("ApprovalByAdmin".equals(registerCondition)) {
            doctorRef.setValue("ApprovedByAdmin");
            openWelcome();}
        else if ("RejectionByAdmin".equals(registerCondition)) {
            doctorRef.setValue("RejectedByAdmin");
            openRejectedPage();}
        else if ("PendingForAdmin".equals(registerCondition)) {
            doctorRef.setValue("Pending");
            openPendingPage();}
    }



    // RegistrationPage switches into loginPage
    public void registerDoctor() {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intent = new Intent(this, LoginPage.class );
        }
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

    // Goes into Registration Pending layout
    public void openPendingPage() {
        setContentView(R.layout.registration_pending_by_administrator);
    }

    // Goes into Registration Rejected layout

    public void openRejectedPage() {
        setContentView(R.layout.registration_rejected_by_administrator);
    }

}


