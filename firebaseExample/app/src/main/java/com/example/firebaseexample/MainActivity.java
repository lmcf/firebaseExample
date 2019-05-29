package com.example.firebaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Declaramos variables tipo editText que necesitamos
    EditText fecha, personas, nombre, telefono, comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos todos los edit text con su campo en el layout
        fecha = findViewById(R.id.fechaEditText);
        personas = findViewById(R.id.comensalesEditText);
        nombre = findViewById(R.id.nombreEditText);
        telefono = findViewById(R.id.telefonoEditText);
        comentarios = findViewById(R.id.comentariosEditText);

        // Generar objeto de tipo reserva y subirlo a Firebase
    }
}
