package com.example.segproject;

import java.util.ArrayList;

//this class represents a user
public class User{

    //instance variables
    protected String userID;
    protected String username; //a user's username is their email
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String address;
    protected String role; //Patient, Doctor or Administrator



    //constructor
    public User(String userId, String username, String password, String firstName, String lastName, String phoneNumber, String address, String role){
        this.userID = userId;
        this.username = username;
        this.password = password;;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }


    public void login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            System.out.println("Welcome! You are logged in as " + role);
        }
        else {
            System.out.println("wrong username or password");
        }
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public void logoff(){
        //insert logic
    }

}

