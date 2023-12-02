package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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


public class doctorAppoint extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private String userID, date = "", time, name, type;
    private TextView selectDate, selectedDate;
    private int count = 0;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_appoint_layout);

        toolbar = (Toolbar) findViewById(R.id.show_doctor_appointment);
    }

}
