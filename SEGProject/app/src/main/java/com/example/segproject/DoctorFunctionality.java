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

public class DoctorFunctionality extends AppCompatActivity {
    TextView doctorFunctionality;
    private Button buttonForUpcomingAppointments, buttonForPastAppointments, buttonForUpcomingShifts;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_functionality);

        doctorFunctionality = findViewById(R.id.doctorFunctionality);
        buttonForUpcomingAppointments = findViewById(R.id.buttonForUpcomingAppointments);
        buttonForPastAppointments = findViewById(R.id.buttonForPastAppointments);
        buttonForUpcomingShifts = findViewById(R.id.buttonForUpcomingShifts);


        buttonForUpcomingAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpcomingAppointments();
            }
        });

        buttonForPastAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) { openPastAppointments();}
    });
        buttonForUpcomingShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openUpcomingShifts();}
        });


    }

    public void openUpcomingAppointments() {
        Intent intentUpcomingAppointments = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentUpcomingAppointments = new Intent(this, UpcomingAppointments.class);
        }
        startActivity(intentUpcomingAppointments);
    }

    public void openPastAppointments() {
        Intent intentPastAppointments = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentPastAppointments = new Intent(this, PastAppointments.class);
        }
        startActivity(intentPastAppointments);
    }

    public void openUpcomingShifts() {
        Intent intentUpcomingShifts = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentUpcomingShifts= new Intent(this, UpcomingShifts.class);
        }
        startActivity(intentUpcomingShifts);
    }


}

