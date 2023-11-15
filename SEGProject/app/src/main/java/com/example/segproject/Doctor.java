package com.example.segproject;

import java.util.ArrayList;
import java.util.List;

//this class represents the doctor which extends user
public class Doctor extends User {

    //instance variables
    protected String employeeNumber;

    protected String specialties;

    protected List<AppointmentForDoctor> pastAppointments;

    protected List<AppointmentForDoctor> upcomingAppointments;

    protected List<ShiftsForDoctor> upcomingShiftsForDoctor;

    //constructor
    public Doctor(String userId, String username, String password, String firstName, String lastName, String phoneNumber, String address, String employeeNumber, String specialties) {
        super(userId, username, password, firstName, lastName, phoneNumber, address, "Doctor");
        this.employeeNumber = employeeNumber;
        this.specialties = specialties;
        this.pastAppointments = new ArrayList<>();
        this.upcomingAppointments = new ArrayList<>();
        this.upcomingShiftsForDoctor = new ArrayList<>();


    }
    // Getters and Setters

    public String getEmployeeNumber() {

        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {

        this.employeeNumber = employeeNumber;
    }

    public String getSpecialties() {

        return specialties;
    }

    public void setSpecialties(String specialties) {

        this.specialties = specialties;
    }

    public List<AppointmentForDoctor> getPastAppointments() {

        return pastAppointments;
    }

    public void setPastAppointments(List<AppointmentForDoctor> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }

    public List<AppointmentForDoctor> getUpcomingAppointments() {

        return upcomingAppointments;
    }

    public void setUpcomingAppointments(List<AppointmentForDoctor> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public List<ShiftsForDoctor> getUpcomingShiftsForDoctor() {

        return upcomingShiftsForDoctor;
    }

    public void setUpcomingShiftsForDoctor(List<ShiftsForDoctor> upcomingShiftsForDoctor) {
        this.upcomingShiftsForDoctor = upcomingShiftsForDoctor;
    }

    public static class AppointmentForDoctor {

        private Patient patient;
        private String conditionOfAppointment;
        private String dateOfAppointment;

        public AppointmentForDoctor(Patient patient, String conditionOfAppointment, String dateOfAppointment ) {
            this.patient = patient;
            this.conditionOfAppointment = conditionOfAppointment;
            this.dateOfAppointment = dateOfAppointment;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public String getConditionOfAppointment() {
            return conditionOfAppointment;
        }

        public void setConditionOfAppointment(String conditionOfAppointment) {
            this.conditionOfAppointment = conditionOfAppointment;
        }

        public String getDateOfAppointment() {
            return dateOfAppointment;
        }

        public void setDateOfAppointment(String dateOfAppointment) {
            this.dateOfAppointment = dateOfAppointment;
        }

    }

    public static class ShiftsForDoctor {
        private String dateOfShift;
        private String startTimeOfShift;
        private String endTimeOfShift;

        public ShiftsForDoctor(String dateOfShift, String startTimeOfShift, String endTimeOfShift){
            this.dateOfShift = dateOfShift;
            this.startTimeOfShift = startTimeOfShift;
            this.endTimeOfShift = endTimeOfShift;
        }

        public String getDateOfShift() {
            return dateOfShift;
        }

        public void setDateOfShift(String dateOfShift) {
            this.dateOfShift = dateOfShift;
        }

        public String getStartTimeOfShift() {
            return startTimeOfShift;
        }

        public void setStartTimeOfShift(String startTimeOfShift) {
            this.startTimeOfShift = startTimeOfShift;
        }

        public String getEndTimeOfShift() {
            return endTimeOfShift;
        }

        public void setEndTimeOfShift(String endTimeOfShift) {
            this.endTimeOfShift = endTimeOfShift;
        }
    }



    // Prints the information of patient including first name, last name, username, password, phone number, address, and health card number
    public void informationOfGivenPatient(AppointmentForDoctor appoint) {
        if (appoint == null) {
            System.out.println("There is no given informaton on the appointment for the patient");
        }
        Patient pat = appoint.getPatient();
        if (pat != null) {
            System.out.println("Information about patient:" +
                    "\n" + "First Name:" + pat.getFirstName() +
                    "\n" + "Last Name:" + pat.getLastName() +
                    "\n" + "Username" + pat.getUsername() +
                    "\n" + "Password:" + pat.getPassword() +
                    "\n" + "Phone Number:" + pat.getPhoneNumber() +
                    "\n" + "Address" + pat.getAddress() +
                    "\n" + "Health Card Number:" + pat.getHealthCardNumber());
        } else if (pat == null) {
            System.out.println("There is no given information for the patient");
        }
    }
}








