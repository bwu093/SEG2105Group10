package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AfterLoginPage extends AppCompatActivity {

    private Button logoffButton;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_page);

        logoffButton = findViewById(R.id.logoffButton);

        logoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWelcome();
            }
        });

    }

    public void openWelcome() {
        Intent intentLogin = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentLogin = new Intent(this, WelcomePage.class);
        }
        startActivity(intentLogin);
    }
}
