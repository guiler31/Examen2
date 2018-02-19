package com.example.guille.examen2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by guille on 19/2/18.
 */

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    public DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }


    //BOTONES HECHOS CON METODOS LINKEADOS

    public void botonClicked(View v) {
         if (v.getId() == R.id.btnSql) {
            Log.v("MainActivity", "sql");

             FirebaseDatabase.getInstance().getReference().child("Contact").addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     GenericTypeIndicator<ArrayList<Contact>> indicator= new GenericTypeIndicator<ArrayList<Contact>>(){};

                     ArrayList<Contact> value = dataSnapshot.getValue(indicator);

                     Log.v("ggg", value.get(0).get_email());
                     Log.v("ggg", value.get(0).get_pass());
                     Contact contact = new Contact(1,value.get(0).get_pass(),value.get(0).get_email());
                     databaseHandler.addContact(contact);
                 }
                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                 }
             });




        } else if (v.getId() == R.id.btnDint) {
            Log.v("MainActivity", "dint");
            Intent intent4 = new Intent(getApplicationContext(), nav_drawer.class);
            startActivity(intent4);
        }
    }
}
