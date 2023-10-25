package com.example.segproject;

import java.util.ArrayList;

//this class represents the doctor which extends user
public class Doctor extends User{

    //instance variables
    protected String employeeNumber;

    protected String specialties;

    //constructor
    public Doctor(String userId, String username, String password, String firstName, String lastName, String phoneNumber, String address, String employeeNumber, String specialties){
        super(userId, username, password, firstName, lastName, phoneNumber, address, "Doctor");
        this.employeeNumber = employeeNumber;
        this.specialties = specialties;
    }


    //methods



}