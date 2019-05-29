package com.example.firebaseexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class VerReservasActivity extends AppCompatActivity {

    RecyclerView reservasRecycler;
    List<Reserva> reservasList;
    FirebaseDatabase db;
    ReservaAdapter reservaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reservas);

        reservasList = new ArrayList<>();
        db = FirebaseDatabase.getInstance();

        reservasRecycler = findViewById(R.id.reservasRecycler);

        // Mostrar Recycler
        reservasRecycler.setLayoutManager(new LinearLayoutManager(this));
        reservaAdapter = new ReservaAdapter();
        reservasRecycler.setAdapter(reservaAdapter);

        // Cargo la lista con la info de Firebase
        // Eso leera cada vez que hay un subnodo "reserva"       //Importante
        db.getReference().child("Reserva").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Este Metodo cada vez que encuentra un nodo saltara este metodo y
                // retornara toodo lo que este en ese nodo
                // Datasnapshot compacta la info y eres tu quien debe saber lo que va venir
                // Se ha de mapear

                // Guardo en la arraylist el datasnapshot de tipo reserva
                reservasList.add(dataSnapshot.getValue(Reserva.class));
                Log.i("RESERVAS", dataSnapshot.getValue(Reserva.class).toString());

                // Aviso al Recycler que hay un nuevo elemento que hay que mostrar
                reservaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    // El Adapter se encarga de enlazar el ViewHolder con el listado de datos, los prepara para pintarlos
    public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaHolder>{

        public ReservaAdapter() {

        }

        @Override
        // Dibuja en pantalla los viewholder (elementos de la lista)
        public ReservaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new ReservaHolder(layoutInflater, viewGroup);
        }

        @Override
        // Pones los datos en cada viewholder
        public void onBindViewHolder(@NonNull ReservaHolder  reservaHolder, int i) {

            Reserva res = reservasList.get(i);
            reservaHolder.fechaReserva.setText(res.getFecha());
            reservaHolder.comensalesReserva.setText(res.getPersonas());
        }

        // Metodo que devuelve la cantidad de objectos de la array.
        // Y sabremos cuantas veces recorrera el bucle que pinta los datos
        @Override
        public int getItemCount() {
            return reservasList.size();
        }


        /* MusicaHolder es el bloque donde pinto la info de cada objeto de cojo de la array */
        class ReservaHolder extends RecyclerView.ViewHolder{
            TextView fechaReserva, comensalesReserva;

            public ReservaHolder(LayoutInflater layoutInflater, ViewGroup parent) { // Busca el itemview
                super(layoutInflater.inflate(R.layout.reservaholder, parent,false));

                fechaReserva = itemView.findViewById(R.id.fechaView);
                comensalesReserva = itemView.findViewById(R.id.comensalesView);

            }
        }

    }
}
