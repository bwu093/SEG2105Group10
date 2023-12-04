package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.app.DatePickerDialog;
import android.content.DialogInterface;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;

import java.util.Calendar;

import androidx.annotation.RequiresApi;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;
import java.util.HashMap;
public class bookedAppoint extends AppCompatActivity implements View.OnClickListener{

    private String date, time = "", shift;
    private TextView selectDate;
    private Toolbar toolbar;
    private Button confirm;
    private  int flagChecked=0;

    private LinearLayout hospitalShift;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Appointment");
    private DatabaseReference patientDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.book_appointment);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.patient_bookAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Book Appointment");

        hospitalShift = (LinearLayout) findViewById(R.id.hospital_shift);

        confirm = (Button) findViewById(R.id.confirm_appointment);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(flagChecked!=0)
                {
                    databaseReference.child(getIntent().getStringExtra("DoctorUserId")).child(date).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int i = 1;
                            for(i=1;i<=30;i++)
                            {
                                if(dataSnapshot.hasChild(String.valueOf(i)))
                                {
                                    if(dataSnapshot.child(String.valueOf(i)).child("PatientID").getValue().toString().equals(auth.getCurrentUser().getUid()))
                                    {
                                        Toast.makeText(bookedAppoint.this, "There is already and appointment ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }
                            if(i>30)
                            {
                                setTime(flagChecked);
                                databaseReference.child(getIntent().getStringExtra("DoctorUserId")).child(date).child(String.valueOf(flagChecked)).child("PatientID").setValue(auth.getCurrentUser().getUid().toString());
                                patientDatabase.child("Doctor_Details").child(getIntent().getStringExtra("DoctorUserId")).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String doctorName = dataSnapshot.child("Name").getValue().toString();

                                        HashMap<String,String> details = new HashMap<>();
                                        details.put("Doctor_ID",getIntent().getStringExtra("DoctorUserId"));
                                        details.put("Date",date);
                                        details.put("Time",time);

                                        patientDatabase.child("Booked_Appointments").child(auth.getCurrentUser().getUid()).push().setValue(details);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                                startActivity(new Intent(bookedAppoint.this, patientViewAppointment.class));

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
                else{
                    Toast.makeText(bookedAppoint.this, "Please Select Time Slot", Toast.LENGTH_SHORT).show();
                }
            }
        });

        c1 = (CardView) findViewById(R.id.time1);
        c2 = (CardView) findViewById(R.id.time2);
        c3 = (CardView) findViewById(R.id.time3);
        c4 = (CardView) findViewById(R.id.time4);
        c5 = (CardView) findViewById(R.id.time5);
        c6 = (CardView) findViewById(R.id.time6);
        c7 = (CardView) findViewById(R.id.time7);
        c8 = (CardView) findViewById(R.id.time8);
        c9 = (CardView) findViewById(R.id.time9);
        c10 = (CardView) findViewById(R.id.time10);
        c11 = (CardView) findViewById(R.id.time11);
        c12 = (CardView) findViewById(R.id.time12);
        c13 = (CardView) findViewById(R.id.time13);
        c14 = (CardView) findViewById(R.id.time14);
        c15 = (CardView) findViewById(R.id.time15);
        c16 = (CardView) findViewById(R.id.time16);
        c17 = (CardView) findViewById(R.id.time17);
        c18 = (CardView) findViewById(R.id.time18);
        c19 = (CardView) findViewById(R.id.time19);
        c20 = (CardView) findViewById(R.id.time20);
        c21 = (CardView) findViewById(R.id.time21);
        c22 = (CardView) findViewById(R.id.time22);
        c23 = (CardView) findViewById(R.id.time23);
        c24 = (CardView) findViewById(R.id.time24);
        c25 = (CardView) findViewById(R.id.time25);
        c26 = (CardView) findViewById(R.id.time26);
        c27 = (CardView) findViewById(R.id.time27);
        c28 = (CardView) findViewById(R.id.time28);
        c29 = (CardView) findViewById(R.id.time29);
        c30 = (CardView) findViewById(R.id.time30);


        selectDate = (TextView) findViewById(R.id.bookAppointment_selectDate);

        date = getIntent().getStringExtra("Date").toString();
        selectDate.setText(date);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(bookedAppoint.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = dayOfMonth +"-"+ (month+1) +"-"+ year;
                        // Toast.makeText(Patient_BookAppointmentActivity.this, date , Toast.LENGTH_SHORT).show();
                        selectDate.setText(date);
                        onStart();


                    }
                },day,month,year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });

    }

    private void setTime(int i) {

        switch (i) {
            case 1:
                time = "08:00 AM";
                break;
            case 2:
                time = "08:20 AM";
                break;
            case 3:
                time = "08:40 AM";
                break;
            case 4:
                time = "09:00 AM";
                break;
            case 5:
                time = "09:20 AM";
                break;
            case 6:
                time = "09:40 AM";
                break;
            case 7:
                time = "10:00 AM";
                break;
            case 8:
                time = "10:20 AM";
                break;
            case 9:
                time = "10:40 AM";
                break;
            case 10:
                time = "11:00 AM";
                break;
            case 11:
                time = "11:20 AM";
                break;
            case 12:
                time = "11:40 AM";
                break;
            case 13:
                time = "02:00 PM";
                break;
            case 14:
                time = "02:20 PM";
                break;
            case 15:
                time = "02:40 PM";
                break;
            case 16:
                time = "03:00 PM";
                break;
            case 17:
                time = "03:20 PM";
                break;
            case 18:
                time = "03:40 PM";
                break;
            case 19:
                time = "04:00 PM";
                break;
            case 20:
                time = "04:20 PM";
                break;
            case 21:
                time = "04:40 PM";
                break;
            case 22:
                time = "05:00 PM";
                break;
            case 23:
                time = "05:20 PM";
                break;
            case 24:
                time = "05:40 PM";
                break;
            case 25:
                time = "06:00 PM";
                break;
            case 26:
                time = "06:20 PM";
                break;
            case 27:
                time = "06:40 PM";
                break;
            case 28:
                time = "09:00 PM";
                break;
            case 29:
                time = "09:20 PM";
                break;
            case 30:
                time = "09:40 PM";
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.time1:
                isBooked(1);
                break;
            case R.id.time2:
                isBooked(2);
                break;
            case R.id.time3:
                isBooked(3);
                break;
            case R.id.time4:
                isBooked(4);
                break;
            case R.id.time5:
                isBooked(5);
                break;
            case R.id.time6:
                isBooked(6);
                break;
            case R.id.time7:
                isBooked(7);
                break;
            case R.id.time8:
                isBooked(8);
                break;
            case R.id.time9:
                isBooked(9);
                break;
            case R.id.time10:
                isBooked(10);
                break;
            case R.id.time11:
                isBooked(11);
                break;
            case R.id.time12:
                isBooked(12);
                break;
            case R.id.time13:
                isBooked(13);
                break;
            case R.id.time14:
                isBooked(14);
                break;
            case R.id.time15:
                isBooked(15);
                break;
            case R.id.time16:
                isBooked(16);
                break;
            case R.id.time17:
                isBooked(17);
                break;
            case R.id.time18:
                isBooked(18);
                break;
            case R.id.time19:
                isBooked(19);
                break;
            case R.id.time20:
                isBooked(20);
                break;
            case R.id.time21:
                isBooked(21);
                break;
            case R.id.time22:
                isBooked(22);
                break;
            case R.id.time23:
                isBooked(23);
                break;
            case R.id.time24:
                isBooked(24);
                break;
            case R.id.time25:
                isBooked(25);
                break;
            case R.id.time26:
                isBooked(26);
                break;
            case R.id.time27:
                isBooked(27);
                break;
            case R.id.time28:
                isBooked(28);
                break;
            case R.id.time29:
                isBooked(29);
                break;
            case R.id.time30:
                isBooked(30);
                break;

            default:
                break;
        }

    }
    private void isBooked(int i) {


        if(flagChecked!=0) {
            setDefaultColor(flagChecked);
            flagChecked = i;
            setGreen(i);
        }
        else {
            flagChecked=i;
            setGreen(i);
        }


    }
}
