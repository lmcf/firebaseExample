package com.example.firebaseexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link itemReservaFragment.ItemReservaListener} interface
 * to handle interaction events.
 * Use the {@link itemReservaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemReservaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // Los argumentos se han de declarar aqui
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

    TextView fecha, comensales, nombre, comentarios;

    private ItemReservaListener mListener;

    public itemReservaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemReservaFragment.
     */
    // TODO: Rename and change types and number of parameters
    // Se han de poner lo argumentos que llegaran del tipo que sea en el constructor
    public static itemReservaFragment newInstance(String param1, String param2,String param3, String param4) {
        itemReservaFragment fragment = new itemReservaFragment();
        Bundle args = new Bundle();
        // E añadirlos aqui
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        // Esto empaqueta los argumentos
        fragment.setArguments(args);
        return fragment;
    }

    // Cuando alguien llama a un fragment entrara aqui primero
    // Se utiliza para coger información que viene adjuntada
    // Cuando se inicializa un fragment puede venir con variables para usarlas mas tarde
    // Vienen empaquetadas en el Bundle
    // Se coje por argumentos es info
    // Aqui estara la infor de fecha,comensales,nombre y comentarios
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    // He de empezar por aqui en un Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Borro el return y lo que retorna pogo View view y al final del metodo hago un return view;
        View view =  inflater.inflate(R.layout.fragment_item_reserva, container, false);

        // FindViewByID no funciona si no lo referencias con view. primero
        fecha = view.findViewById(R.id.fechaTexto);
        comensales = view.findViewById(R.id.comensalesTexto);
        nombre = view.findViewById(R.id.nombreTexto);
        comentarios = view.findViewById(R.id.comentariosTexto);

        // Si tuviera un boton un set on click listener y como siempre (no es el caso)

        // Metemos los datos en cada textview
        fecha.setText(mParam1);
        comensales.setText(mParam2);
        nombre.setText(mParam3);
        comentarios.setText(mParam4);

        // Ejemplo de como se usa interface
        comentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pulsadas;
                // Aqui utilizaremos el metodo contador pulsaciones que hemos implementado en el controller
                pulsadas = mListener.contador_pulsaciones();
                Log.i("CONTADOR", String.valueOf(pulsadas));
            }
        });


        return view;
    }

    // Los dos siguientes metodos no hay que tocarlos
    // Uno se encarga de que mListener tenga algo
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemReservaListener) {
            mListener = (ItemReservaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    // Este oro libera el mListener (interface)
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    // Cambio el metodo por itemreservaListener
    // Clase interface
    // Muy importante este metodo para que le fragment funciones con el controller
    public interface ItemReservaListener{
        // Aqui se definen las interfaces
        // TODO: Update argument type and name
        // Aqui declaro las acciones que quiero que el controller haga por mi
        int contador_pulsaciones();
    }
}
