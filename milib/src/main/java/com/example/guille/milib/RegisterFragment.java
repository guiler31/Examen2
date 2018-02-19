package com.example.guille.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public EditText txtfEmail;
    public EditText txtfPas;
    public Button btnLog;
    public Button btnCancel;
    public RegisterFragmentEvents events;
    public RegisterFragmentsListener listener;

    public void setListener(RegisterFragmentsListener registerFragmentsListener){
        this.listener = registerFragmentsListener;
    }


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_regiaster, container, false);

        events = new RegisterFragmentEvents(this);

        txtfEmail = v.findViewById(R.id.edtEmailReg);
        txtfPas = v.findViewById(R.id.edtPassReg);

        btnLog = v.findViewById(R.id.btnAceptar);
        btnCancel = v.findViewById(R.id.btnCancelar);

        btnCancel.setOnClickListener(events);
        btnLog.setOnClickListener(events);
        return v;
    }

}
class RegisterFragmentEvents implements View.OnClickListener{

RegisterFragment registerFragment;

public RegisterFragmentEvents(RegisterFragment registerFragment){
    this.registerFragment = registerFragment;
}


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAceptar) {
            this.registerFragment.listener.OnClickAceptarRegistro(registerFragment.txtfEmail.getText().toString(),registerFragment.txtfPas.getText().toString());

        } else if (view.getId() == R.id.btnCancelar) {
            this.registerFragment.listener.OnclickCancelarRegistro();
        }
    }
}
