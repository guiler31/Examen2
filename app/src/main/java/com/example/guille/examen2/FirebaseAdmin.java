package com.example.guille.examen2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by guille on 18/12/17.
 */

public class FirebaseAdmin {

    public FirebaseAuth mAuth;
    private FirebaseAdminListener listener;
    public FirebaseUser user;
    public FirebaseDatabase database;
    DatabaseReference myRefRaiz;

    public FirebaseAdmin(){
        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRefRaiz=database.getReference();
    }

    public void setListener(FirebaseAdminListener listener){
        this.listener = listener;
    }

    public void RegisterEmailPass(String email, String pass, Activity activity){

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.registerOK(true);
                        }else{
                            listener.registerOK(false);
                        }

                        // ...
                    }
                });

    }

    public void LoginEmailPass(String email, String pass, Activity activity){

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            listener.loginOK(true);
                        }else{
                            listener.loginOK(false);
                        }

                        // ...
                    }
                });

    }

    //metodo para descargar ramas
    public void descargarYObservarRama(final String rama){
        DatabaseReference refRama=myRefRaiz.child(rama);
        refRama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                listener.ramaDescargada(rama, dataSnapshot);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                listener.ramaDescargada(rama, null);

                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}


