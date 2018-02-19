package com.example.guille.examen2;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by guille on 18/12/17.
 */
@IgnoreExtraProperties
public class DataHolder {
    public static DataHolder instances = new DataHolder();

    public FirebaseAdmin firebaseAdmin;

    public DataHolder(){
        this.firebaseAdmin = new FirebaseAdmin();
    }
}
