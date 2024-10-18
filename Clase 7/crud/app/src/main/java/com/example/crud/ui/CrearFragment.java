package com.example.crud.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crud.R;
import com.example.crud.models.Contacto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CrearFragment extends Fragment {

    private EditText etNombre, etEdad;
    private Button btnGuardar;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        // Referencias a los elementos de la vista
        etNombre = view.findViewById(R.id.et_nombre);
        etEdad = view.findViewById(R.id.et_edad);
        btnGuardar = view.findViewById(R.id.btn_guardar);

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference("contacto");

        // Configurar el botón para guardar el contacto
        btnGuardar.setOnClickListener(v -> guardarContacto());

        return view;
    }

    private void guardarContacto() {
        String nombre = etNombre.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(edadStr)) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int edad = Integer.parseInt(edadStr);
        String id = databaseReference.push().getKey(); // Generar un ID único para el contacto

        if (id != null) {
            // Verificar si ya existe un contacto con el mismo nombre
            databaseReference.orderByChild("nombre").equalTo(nombre)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Si ya existe un contacto con el mismo nombre, mostrar un mensaje
                                Toast.makeText(getContext(), "El contacto ya existe. Ingrese otro nombre.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Si no existe, guardar el contacto
                                Contacto contacto = new Contacto(id, nombre, edad);
                                databaseReference.child(id).setValue(contacto)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(getContext(), "Contacto guardado correctamente", Toast.LENGTH_SHORT).show();
                                            limpiarFormulario();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(getContext(), "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
                                        });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "Error al verificar el contacto", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etEdad.setText("");
        etNombre.requestFocus(); // Poner el foco en el campo de nombre para un nuevo ingreso
    }
}
