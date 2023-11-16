import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.segproject.R;
import com.example.segproject.WelcomePage;

public class UpcomingAppointments extends AppCompatActivity {
    TextView upcom;
    private Button buttonForApproval, buttonForRejection, buttonForPatientInformation, buttonForBack;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_appointments);

        upcom = findViewById(R.id.upcom);
        buttonForApproval = findViewById(R.id.buttonForApproval);
        buttonForRejection = findViewById(R.id.buttonForRejection);
        buttonForPatientInformation = findViewById(R.id.buttonForPatientInformation);
        buttonForBack = findViewById(R.id.buttonForBack);


        buttonForApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openApproval();
            }
        });

        buttonForRejection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRejection();}
        });
        buttonForPatientInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientInformation();
            }
        });

        buttonForBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();}
        });

    }

    public void openBack() {
        Intent intentBack = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentBack= new Intent(this, DoctorFunctionality.class);
        }
        startActivity(intentBack);
    }

    public void openPatientInformation() {
        Intent intentPatientInformation = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentPatientInformation= new Intent(this, PatientInformation.class);
        }
        startActivity(intentPatientInformation;
    }

    }

