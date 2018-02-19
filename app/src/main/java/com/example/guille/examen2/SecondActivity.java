package com.example.guille.examen2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by guille on 19/2/18.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //BOTONES HECHOS CON METODOS LINKEADOS

    public void botonClicked(View v) {
         if (v.getId() == R.id.btnSql) {
            Log.v("MainActivity", "sql");
        } else if (v.getId() == R.id.btnMap) {
            Log.v("MainActivity", "map");
        } else if (v.getId() == R.id.btnDint) {
            Log.v("MainActivity", "dint");
            Intent intent4 = new Intent(getApplicationContext(), nav_drawer.class);
            startActivity(intent4);
        }
    }
}
