package com.example.segproject;

public class Administrator extends User{

    //instance variables

    //constructor
    public Administrator(String userId, String username, String password, String firstName, String lastName, String phoneNumber, String address) {
        super(userId, username, password, firstName, lastName, phoneNumber, address, "Administrator");
    }

    //methods
    //returns true if approving user and returns false if rejecting user
    public boolean approveOrReject(User user){
        return true;
        //insert logic
    }

    //view registration requests

}
