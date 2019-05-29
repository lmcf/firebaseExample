package com.example.firebaseexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class NewReservaActivity extends AppCompatActivity implements itemReservaFragment.ItemReservaListener{
    // Declaramos variables que necesitamos
    EditText fecha, personas, nombre, telefono, comentarios;
    Button enviar;

    // Como conectarme a firebase
    FirebaseDatabase db;

    // Del ejemplo del metodo de fragment
    int pulsaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reserva);

        pulsaciones = 0;
        // Para dibuujar los fragments
        FragmentManager fm = getSupportFragmentManager();
        // EL newInstance para poderle pasar los argumentos
        Fragment fragment = new itemReservaFragment().newInstance("1-1-1","3","Luis","ninguno");
        // El contenedor del fragment
        // Hay que acabar siempre con el commit
        fm.beginTransaction().replace(R.id.fragment_container,fragment).commit();


        // Esto apuntara a mi proyecto de Firebase
        db = FirebaseDatabase.getInstance();

        // Referenciamos todos los edit text con su campo en el layout
        fecha = findViewById(R.id.fechaEditText);
        personas = findViewById(R.id.comensalesEditText);
        nombre = findViewById(R.id.nombreEditText);
        telefono = findViewById(R.id.telefonoEditText);
        comentarios = findViewById(R.id.comentariosEditText);
        enviar = findViewById(R.id.enviarButton);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Generar objeto de tipo reserva y subirlo a Firebase
                Reserva nuevaReserva;
                nuevaReserva = new Reserva(
                        fecha.getText().toString(),
                        personas.getText().toString(),
                        nombre.getText().toString(),
                        telefono.getText().toString(),
                        comentarios.getText().toString());

                /*
                   Esto sirve para guardar en el firebase
                .child() es para guardarlo en la subcarpeta de la raiz
                .push() para que al leer no de ningun problema
                */
                db.getReference().child("Reserva").push().setValue(nuevaReserva);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.VerListaReservas:
                Intent intent= new Intent (this, VerReservasActivity.class);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    // Ejemplo del metodo que tendo en itemreservafragmente.
    @Override
    public int contador_pulsaciones() {
        pulsaciones ++;
        return pulsaciones;
    }
}
