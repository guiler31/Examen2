package com.example.guille.examen2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guille.milib.LoginFragment;
import com.example.guille.milib.LoginFragmentListener;
import com.example.guille.milib.RegisterFragment;
import com.example.guille.milib.RegisterFragmentsListener;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity  {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asignacion de xml
        setContentView(R.layout.activity_main);
        //Importacion de fragments con sus respectivos xml
        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frgLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.frgRegister);
        //Creacion de escuchador de eventos
        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);
        //Asignacion de listener para los fragments
        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);


        DataHolder.instances.firebaseAdmin.setListener(mainActivityEvents);
        //Transiciones entre fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(registerFragment);
        transaction.show(loginFragment);
        transaction.commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        loginFragment.callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Metodo para sacar los datos del usuario de facebook mediante firebase
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        DataHolder.instances.firebaseAdmin.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = DataHolder.instances.firebaseAdmin.mAuth.getCurrentUser();
                            Log.v("arr", user.getEmail());
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.v("arr", "fail");

                            //updateUI(null);
                        }
                        // ...

                    }
                });
    }


}
//Creacion de subclase events para gestionar los eventos de los fragments y el listener de firebase
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentsListener, FirebaseAdminListener{

    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void OnclickedLoginButton(String email, String pass) {
        DataHolder.instances.firebaseAdmin.LoginEmailPass(email,pass,mainActivity);
    }

    @Override
    public void OnclickedRegisterButton() {
        FragmentTransaction transaction = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(this.mainActivity.registerFragment);

        transaction.hide(this.mainActivity.loginFragment);
        transaction.commit();
    }

    @Override
    public void OnClickAceptarRegistro(String email, String pass) {
        DataHolder.instances.firebaseAdmin.RegisterEmailPass(email,pass,mainActivity);
    }

    @Override
    public void OnclickCancelarRegistro() {
        FragmentTransaction transaction = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(this.mainActivity.registerFragment);

        transaction.show(this.mainActivity.loginFragment);
        transaction.commit();
    }

    @Override
    public void registerOK(boolean itsOk) {
        if (itsOk){
            //Hacer metodos cuando el registro esta bien
            Intent intent=new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
            Log.v("MAINAKAHFG","BIEN EL Regis");
        }
        else{

        }
    }

    @Override
    public void loginOK(boolean itsOk) {
        if (itsOk){
            //Hacer metodos cuando el login esta bien
            Intent intent=new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
            Log.v("MAINAKAHFG","BIEN EL LOGIN");
        }
    }

    @Override
    public void ramaDescargada(String rama, DataSnapshot dataSnapshot) {

    }
}
