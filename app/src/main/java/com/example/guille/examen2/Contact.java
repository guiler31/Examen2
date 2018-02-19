package com.example.guille.examen2;


public class Contact {
    //private variables
    int _id;
    String _name;
    String _email;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String name, String _email){
        this._id = id;
        this._name = name;
        this._email = _email;
    }

    // constructor
    public Contact(String name, String _email){
        this._name = name;
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
    public String get_name(){
        return this._name;
    }

    // setting name
    public void set_name(String name){
        this._name = name;
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