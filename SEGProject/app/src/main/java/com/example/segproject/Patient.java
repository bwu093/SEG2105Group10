package com.example.segproject;

import java.util.ArrayList;

//this class represents the Patient which extends user
public class Patient extends User{

    //instance variables
    protected String healthCardNumber;

    //constructor
    public Patient(String userId, String username, String password, String firstName, String lastName, String phoneNumber, String address, String healthCardNumber) {
        super(userId, username, password, firstName, lastName, phoneNumber, address, "Patient");
        this.healthCardNumber = healthCardNumber;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }
    // Setter for healthCardNumber
    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    //methods

}
