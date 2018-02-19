package com.example.guille.milib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapDetailFragment extends Fragment {

    public TextView txtNom, txtMarca, txtFabricado;


    public MapDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_map_detail, container,false);
        txtNom=v.findViewById(R.id.txtNom1);
        txtMarca=v.findViewById(R.id.txtMarca1);
        txtFabricado=v.findViewById(R.id.txtFabricado1);
        return  v;
    }

}
