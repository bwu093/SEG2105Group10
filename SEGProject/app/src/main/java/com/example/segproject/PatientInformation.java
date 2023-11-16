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

public class PatientInformation extends AppCompatActivity {
    TextView textView6;
    private Button buttonForBackForiNFO;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_information);

        textView6 = findViewById(R.id.textView6);
        buttonForBackForiNFO = findViewById(R.id.buttonForBackForiNFO);


        buttonForBackForiNFOt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackForInfo();
            }
        });

    }

    public void openBackForInfo() {
        Intent intentBackForInfo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentBackForInfo= new Intent(this, UpcomingAppointments.class);
        }
        startActivity(intentBackForInfo);
    }

}