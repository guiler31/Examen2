package com.example.guille.milib;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public EditText txtfEmail;
    public EditText txtfPas;
    public Button btnLog;
    public Button btnReg;
    public LoginFragmentEvents events;
    public LoginFragmentListener listener;
    public CallbackManager callbackManager;
    public LoginButton loginButton;



    public void setListener(LoginFragmentListener listener) {

        this.listener = listener;
    }

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        // Build a GoogleSignInClient with the options specified by gso.

        callbackManager = CallbackManager.Factory.create();
        events = new LoginFragmentEvents(this);
        loginButton = v.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.v("LogFB", "bienF");

            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LogFB", "canF");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LogFB", "malF");

            }
        });
        txtfEmail = v.findViewById(R.id.edTEmailLog);
        txtfPas = v.findViewById(R.id.edTPassLog);

        btnLog = v.findViewById(R.id.btnLogin);
        btnReg = v.findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(events);
        btnLog.setOnClickListener(events);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }



}
class LoginFragmentEvents implements View.OnClickListener{

    public LoginFragment login;

    public LoginFragmentEvents(LoginFragment login){

        this.login = login;

    }


    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister) {
            this.login.listener.OnclickedRegisterButton();

        } else if (view.getId() == R.id.btnLogin) {
            this.login.listener.OnclickedLoginButton(login.txtfEmail.getText().toString(),login.txtfPas.getText().toString());
        }
    }

}
