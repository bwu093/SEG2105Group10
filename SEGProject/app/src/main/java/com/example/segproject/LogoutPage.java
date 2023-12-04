package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LogoutPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_layout);

        //YES button
        Button yesButton = findViewById(R.id.approve_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcomePage();
            }
        });

        //NO button
        Button noButton = findViewById(R.id.backButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAfterLoginPage();
            }
        });
    }

    //Go to the WelcomePage after clicking "YES"
    private void openWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
        finish();
    }

    //Go to the AfterLoginPage after clicking "NO"
    private void openAfterLoginPage() {
        Intent intent = new Intent(this, AfterLoginPage.class);
        startActivity(intent);
        finish();
    }
}
