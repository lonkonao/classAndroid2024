package com.example.crud.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crud.models.Contacto;
import com.example.crud.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private ListView listViewContactos;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaContactos;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar, container, false);

        // Inicializar la vista y la lista
        listViewContactos = view.findViewById(R.id.list_view_contactos);
        listaContactos = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaContactos);
        listViewContactos.setAdapter(adapter);

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("contacto");

        // Obtener los contactos desde Firebase
        obtenerContactos();

        return view;
    }

    private void obtenerContactos() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaContactos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Contacto contacto = dataSnapshot.getValue(Contacto.class);
                    if (contacto != null) {
                        // Agregar el contacto a la lista
                        listaContactos.add(contacto.getNombre() + " - Edad: " + contacto.getEdad());
                        Log.d("ListarFragment", "Contacto encontrado: " + contacto.getNombre());
                    }
                }
                adapter.notifyDataSetChanged();
                Log.d("ListarFragment", "Número de contactos: " + listaContactos.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al cargar los contactos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ListarFragment", "Error al cargar los contactos", error.toException());
            }
        });
    }
}
