package com.example.guille.examen2;


import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.stream.Stream;

@IgnoreExtraProperties
public class Contact{
    //private variables
    int _id;
    String _pass;
    String _email;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String pass, String _email){
        this._id = id;
        this._pass = pass;
        this._email = _email;
    }

    // constructor
    public Contact(String pass, String _email){
        this._pass = pass;
        this._email = _email;
    }
    // getting ID
    public int get_id(){
        return this._id;
    }

    // setting id
    public void set_id(int id){
        this._id = id;
    }

    // getting name
    public String get_pass(){
        return this._pass;
    }

    // setting name
    public void set_pass(String pass){
        this._pass = pass;
    }

    // getting phone number
    public String get_email(){
        return this._email;
    }

    // setting phone number
    public void set_email(String email){
        this._email = email;
    }


}