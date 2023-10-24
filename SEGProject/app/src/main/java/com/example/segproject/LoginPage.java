package com.example.segproject;
//Bill Wu made the basics of this page, waiting for others to fill in the rest

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

import org.w3c.dom.Text;


@RequiresApi(api = Build.VERSION_CODES.P)
public class LoginPage extends AppCompatActivity {

    TextView email, titlePage;
    EditText username, password;
    private Button loginButton, backButton;
    //this part made by Bill Wu 300170086

    //This is only temp change the users, passwords and roles with the actual info later
    private final String[] users = {"doctorOne", "patientOne"};
    private final String[] passwords = {"doctorOnePass", "patientOnePass"};
    private final String[] roles = {"doctor", "patient"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        username = findViewById(R.id.email);//nothing for these two yet as code needs to be written
        password = findViewById(R.id.password);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcome();
            }
        });

        loginButton = findViewById(R.id.approve_button);
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
        String val = username.getText().toString().trim();
        if (val.isEmpty()) {
            username.setError("Username cannot be empty");
            return false;
        }
        return true;

    }

    public Boolean validatePassword() {
        String val = password.getText().toString().trim();
        if (val.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        }

        return true;

    }

    //returns to homepage
    public void openWelcome() {
        Intent intentLogin = new Intent(this, WelcomePage.class);
        startActivity(intentLogin);
    }

    //This part checks if they are a doctor or patient, add in code below, might need two classes for the occupations
    public void checkUsers() {
        String userUsername = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserData = reference.orderByChild("username").equalTo(userUsername);

        checkUserData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    String databasePass = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (databasePass.equals(userPassword) ){
                        String databaseEmail = snapshot.child(userUsername).child("email").getValue(String.class);
                        String databaseUsername = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(LoginPage.this, User.class);
                        intent.putExtra("email", databaseUsername);
                        intent.putExtra("password", databasePass);
                        startActivity(intent);
                    } else {
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                    } else{
                    username.setError("Username does not exist");
                    username.requestFocus();
                }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        for (int i = 0; i < users.length; i++) {
            if (userUsername.equals(users[i]) && userPassword.equals(passwords[i])) {
                // Authentication successful. You can set user roles and navigate accordingly.
                String role = roles[i];
                if ("doctor".equals(role)) {
                    //User is a doctor
                    Intent intent = new Intent(this, Doctor.class);
                    startActivity(intent);
                } else if ("patient".equals(role)) {
                    //User is a patient
                    Intent intent = new Intent(this, Patient.class);
                    startActivity(intent);
                }
                return;
            }
        }

        Toast.makeText(this, "Authentication failed. Check credentials.", Toast.LENGTH_SHORT).show();
        */

    }
}