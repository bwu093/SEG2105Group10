package com.example.segproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.app.DatePickerDialog;
import android.content.DialogInterface;

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


public class doctorAppoint extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private String userID, date = "", time, name, type;
    private TextView selectDate, selectedDate;
    private int count = 0;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_appoint_layout);

        toolbar = (Toolbar) findViewById(R.id.show_doctor_appointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userID = auth.getCurrentUser().getUid().toString();
        selectDate = (TextView) findViewById(R.id.showAppointment_selecteDate);
        selectedDate = (TextView) findViewById(R.id.showAppointment_selectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(doctorAppoint.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date = i2 + "-" + (i1+1) + "-" + i;
                        selectedDate.setVisibility(View.VISIBLE);
                        selectDate.setText(date);
                        showAppointments();
                    }
                }, day, month, year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.appointments_show);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showAppointments(){
        Query query = databaseReference.child("Appointment").child(userID).child(date);

        FirebaseRecyclerOptions<bookedApointments> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<bookedApointments>()
                .setQuery(query, bookedApointments.class)
                .build();
        FirebaseRecyclerAdapter<bookedApointments, showDoctorAppointment> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<bookedApointments, showDoctorAppointment>(firebaseRecyclerOptions) {
                    @Override
                    protected void onBindViewHolder(@NonNull final showDoctorAppointment holder, final int position, @NonNull final bookedApointments model) {

                        final String patientID = model.getPatientID().toString();
                        final String slot = getRef(position).getKey().toString();
                        final String[] name = new String[1];

                        holder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Toast.makeText(Doctor_ShowAppointmentActivity.this, slot, Toast.LENGTH_SHORT).show();
                                alertDialog(patientID, slot);
                            }
                        });

                        databaseReference.child("User_Type").child(patientID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                type = dataSnapshot.child("Type").getValue().toString();
                                if(type.equals("Patient")){
                                    databaseReference.child("Patient_Details").child(patientID).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            name[0] = dataSnapshot.child("Name").getValue().toString();

                                            changeSlotToTime(slot);
                                            holder.setName(name[0]);
                                            holder.setTime(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }else {
                                    databaseReference.child("Doctor_Details").child(patientID).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            name[0] = dataSnapshot.child("Name").getValue().toString();

                                            changeSlotToTime(slot);
                                            holder.setName(name[0]);
                                            holder.setTime(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public showDoctorAppointment onCreateViewHolder(ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.show_appointment,parent,false);
                        return new showDoctorAppointment(view);
                    }
                };


    }

    private void alertDialog(final String patientID, final String slot) {

        count = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(doctorAppoint.this);
        builder.setIcon(R.drawable.img_3).setTitle("Cancel Appointment");
        builder.setMessage("Are You Sure! Want to Cancel Appointment");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

//                    Toast.makeText(Doctor_ShowAppointmentActivity.this, userID+" = UserID "+date+" = Date "+slot+" = Slot", Toast.LENGTH_SHORT).show();


                Query query = databaseReference.child("Booked_Appointments").child(patientID).orderByChild("Date");
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String myParentNode = dataSnapshot.getKey();

                        for (DataSnapshot child: dataSnapshot.getChildren())
                        {
                            String key = child.getKey().toString();
                            String value = child.getValue().toString();

                            if(value.equals(userID)){
//                                    Toast.makeText(Doctor_ShowAppointmentActivity.this, key+" - "+value, Toast.LENGTH_SHORT).show();
                                count = count + 1;

                            }
                            if(value.equals(date)){
//                                    Toast.makeText(Doctor_ShowAppointmentActivity.this, key+" - "+value, Toast.LENGTH_SHORT).show();
                                count = count + 1;

                            }
                        }
                        if(count == 2){
//                                Toast.makeText(Doctor_ShowAppointmentActivity.this, Integer.toString(count), Toast.LENGTH_SHORT).show();
                            databaseReference.child("Appointment").child(userID).child(date).child(slot).removeValue();
                            databaseReference.child("Booked_Appointments").child(patientID).child(myParentNode).removeValue();

                        }

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void changeSlotToTime(String slot) {

        switch (slot) {
            case "1":
                time = "08:00 AM";
                break;
            case "2":
                time = "08:30 AM";
                break;
            case "3":
                time = "09:00 AM";
                break;
            case "4":
                time = "09:30 AM";
                break;
            case "5":
                time = "10:00 AM";
                break;
            case "6":
                time = "10:30 AM";
                break;
            case "7":
                time = "11:00 AM";
                break;
            case "8":
                time = "11:30 AM";
                break;
            case "9":
                time = "12:00 AM";
                break;
            case "10":
                time = "12:30 AM";
                break;
            case "11":
                time = "01:00 AM";
                break;
            case "12":
                time = "01:30 AM";
                break;
            case "13":
                time = "02:00 PM";
                break;
            case "14":
                time = "02:30 PM";
                break;
            case "15":
                time = "03:00 PM";
                break;
            case "16":
                time = "03:30 PM";
                break;
            case "17":
                time = "04:00 PM";
                break;
            case "18":
                time = "04:30 PM";
                break;
            case "19":
                time = "05:00 PM";
                break;
            case "20":
                time = "05:30 PM";
                break;
            case "21":
                time = "06:00 PM";
                break;
            case "22":
                time = "06:30 PM";
                break;
            case "23":
                time = "07:00 PM";
                break;
            case "24":
                time = "07:30 PM";
                break;
            case "25":
                time = "08:00 PM";
                break;
            case "26":
                time = "08:30 PM";
                break;
            case "27":
                time = "09:00 PM";
                break;
            case "28":
                time = "09:30 PM";
                break;
            case "29":
                time = "10:00 PM";
                break;
            case "30":
                time = "10:30 PM";
                break;
            default:
                break;
        }
    }
    public class showDoctorAppointment extends RecyclerView.ViewHolder{

        View mView;

        public showDoctorAppointment(View itemView) {
            super(itemView);

            mView =itemView;
        }

        public void setTime(String time) {
            TextView textView = (TextView) mView.findViewById(R.id.single_patient_time);
            textView.setText(time);
        }

        public void setName(String name) {
            TextView mName = (TextView) mView.findViewById(R.id.single_patientName);
            mName.setText(name);
        }
    }

}
