package com.example.crud.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class BuscarFragment extends Fragment {

    private EditText etBuscarNombre, etNuevoNombre, etNuevaEdad;
    private Button btnBuscar, btnActualizar, btnEliminar;
    private TextView tvResultado;
    private DatabaseReference databaseReference;
    private String contactoId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        etBuscarNombre = view.findViewById(R.id.et_buscar_nombre);
        etNuevoNombre = view.findViewById(R.id.et_nuevo_nombre);
        etNuevaEdad = view.findViewById(R.id.et_nueva_edad);
        btnBuscar = view.findViewById(R.id.btn_buscar);
        btnActualizar = view.findViewById(R.id.btn_actualizar);
        btnEliminar = view.findViewById(R.id.btn_eliminar);
        tvResultado = view.findViewById(R.id.tv_resultado);

        databaseReference = FirebaseDatabase.getInstance().getReference("contacto");

        btnBuscar.setOnClickListener(v -> buscarContacto());
        btnActualizar.setOnClickListener(v -> actualizarContacto());
        btnEliminar.setOnClickListener(v -> eliminarContacto());

        return view;
    }

    private void buscarContacto() {
        String nombre = etBuscarNombre.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getContext(), "Por favor, ingrese un nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.orderByChild("nombre").equalTo(nombre)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Contacto contacto = dataSnapshot.getValue(Contacto.class);
                                if (contacto != null) {
                                    contactoId = contacto.getId();
                                    etNuevoNombre.setText(contacto.getNombre());
                                    etNuevaEdad.setText(String.valueOf(contacto.getEdad()));
                                    tvResultado.setText("Contacto encontrado: " + contacto.getNombre());
                                }
                            }
                        } else {
                            tvResultado.setText("No se encontró el contacto.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Error al buscar el contacto", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void actualizarContacto() {
        String nuevoNombre = etNuevoNombre.getText().toString();
        String nuevaEdadStr = etNuevaEdad.getText().toString();

        if (TextUtils.isEmpty(nuevoNombre) || TextUtils.isEmpty(nuevaEdadStr)) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int nuevaEdad = Integer.parseInt(nuevaEdadStr);

        if (contactoId != null) {
            Contacto contactoActualizado = new Contacto(contactoId, nuevoNombre, nuevaEdad);
            databaseReference.child(contactoId).setValue(contactoActualizado)
                    .addOnSuccessListener(aVoid ->
                            Toast.makeText(getContext(), "Contacto actualizado", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "Error al actualizar el contacto", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(getContext(), "Primero busque un contacto para actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarContacto() {
        if (contactoId != null) {
            databaseReference.child(contactoId).removeValue()
                    .addOnSuccessListener(aVoid ->
                            Toast.makeText(getContext(), "Contacto eliminado", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "Error al eliminar el contacto", Toast.LENGTH_SHORT).show());

            // Limpiar los campos después de eliminar
            etNuevoNombre.setText("");
            etNuevaEdad.setText("");
            etBuscarNombre.setText("");
            tvResultado.setText("Contacto eliminado.");
            contactoId = null;
        } else {
            Toast.makeText(getContext(), "Primero busque un contacto para eliminar", Toast.LENGTH_SHORT).show();
        }
    }
}

