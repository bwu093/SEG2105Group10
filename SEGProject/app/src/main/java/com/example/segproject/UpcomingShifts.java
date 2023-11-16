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

public class UpcomingShifts extends AppCompatActivity {
    TextView textView5;
    private Button buttonForAdding;
    private Button buttonForDeleting;

    private Button buttonForBackForUpcomingShifts;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_shifts);

        textView5 = findViewById(R.id.textView5);
        buttonForAdding= findViewById(R.id.buttonForAdding);
        buttonForDeleting = findViewById(R.id.buttonForDeleting);
        buttonForBackForUpcomingShifts = findViewById(R.id.buttonForBackForUpcomingShifts);


        buttonForAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdding();
            }
        });

        buttonForDeleting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleting();}
        });

        buttonForBackForUpcomingShifts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openBackForUpcomingShifts();}
    });

}

    public void openBackForUpcomingShifts() {
        Intent intentBackForUpcomingShifts = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentBackForUpcomingShifts= new Intent(this, DoctorFunctionality.class);
        }
        startActivity(intentBackForUpcomingShifts);
    }

}

