package com.example.segproject;

import java.util.ArrayList;

//this class represents the doctor which extends user
public class Doctor extends User{

    //instance variables
    protected int employeeNumber;

    protected ArrayList<String> specialties;

    //constructor
    public Doctor(int userId, String username, String password, String firstName, String lastName, String phoneNumber, String address, int employeeNumber, ArrayList<String> specialties){
        super(userId, username, password, firstName, lastName, phoneNumber, address, "Doctor");
        this.employeeNumber = employeeNumber;
        this.specialties = specialties;
    }


    //methods



}