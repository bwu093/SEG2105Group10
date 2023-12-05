package com.example.segproject;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class patientViewDoctor extends AppCompatActivity{
    private TextView name, specialization, contactNo;
    private String shift;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_doctor);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.patient_doctorProfile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Doctor Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.patient_doctorProfile_name);
        specialization = (TextView) findViewById(R.id.patient_doctorProfile_specialization);
        contactNo = (TextView) findViewById(R.id.patient_doctorProfile_contact);

        Button mBookAppointmentBtn = (Button) findViewById(R.id.book_appointment_button);
        mBookAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(patientViewDoctor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String userId = getIntent().getStringExtra("UserId");
                        String date = dayOfMonth + "-" + (month + 1) + "-" + year;

                        Intent intent = new Intent(patientViewDoctor.this, bookedAppoint.class);
                        intent.putExtra("Date", date);
                        intent.putExtra("DoctorUserId", userId);
                        intent.putExtra("Shift", shift);
                        startActivity(intent);
                    }
                }, day, month, year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String doctorName = getIntent().getStringExtra("Name");
        String docSpecialization = getIntent().getStringExtra("Specialization");
        String contact = getIntent().getStringExtra("Contact");

        name.setText(doctorName);  // Use the correct variable name here
        specialization.setText(docSpecialization);
        contactNo.setText(contact);
    }
}
