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

public class PastAppointments extends AppCompatActivity {
    TextView textView2;
    private Button buttonForBackForPast;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_appointments);

        textView2 = findViewById(R.id.textView2);
        buttonForBackForPast = findViewById(R.id.buttonForBackForPast);


        buttonForBackForPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBackForPast();
            }
        });

}

    public void openBackForPast() {
        Intent intentBackForPast = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            intentBackForPast= new Intent(this, DoctorFunctionality.class);
        }
        startActivity(intentBackForPast);
    }

}

