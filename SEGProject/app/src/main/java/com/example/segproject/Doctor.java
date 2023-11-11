package com.example.segproject;

import java.util.ArrayList;
import java.util.List;

//this class represents the doctor which extends user
public class Doctor extends User {

    //instance variables
    protected String employeeNumber;

    protected String specialties;

    protected List<App> pastAppointments;

    protected List<App> upcomingAppointments;

    protected List<Sft> upcomingShiftsForDoctor;

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

    public List<App> getPastAppointments() {
        return pastAppointments;
    }

    public void setPastAppointments(List<App> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }

    public List<App> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setUpcomingAppointments(List<App> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public List<Sft> getUpcomingShiftsForDoctor() {
        return upcomingShiftsForDoctor;
    }

    public void setUpcomingShiftsForDoctor(List<Sft> upcomingShiftsForDoctor) {
        this.upcomingShiftsForDoctor = upcomingShiftsForDoctor;
    }

    // Prints the information of patient including first name, last name, username, password, phone number, address, and health card number
    public void informationOfGivenPatient(Appoint appoint) {
        if (appoint == null) {
            System.out.println("There is no given informaton on the appointment for the patient");
        }
        Pat pat = appoint.getPatient();
        if (pat != null) {
            System.out.println("Information about patient:" +
                    "\n" + "First Name:" + pat.getFirstName() +
                    "\n" + "Last Name:" + pat.getLastName() +
                    "\n" + "Username" + pat.getUsername() +
                    "\n" + "Password:" + pat.getPasword() +
                    "\n" + "Phone Number:" + pat.getPhoneNumber() +
                    "\n" + "Address" + pat.getAddress() +
                    "\n" + "Health Card Number:" + pat.getHealthCardNumber());
        } else if (pat == null) {
            System.out.println("There is no given information for the patient");
        }
    }
}








