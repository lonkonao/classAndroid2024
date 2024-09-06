package com.example.registro;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListadoUsuariosActivity extends AppCompatActivity {

    private TableLayout tableLayoutUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado);

        tableLayoutUsuarios = findViewById(R.id.tableLayoutUsuarios);

        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) getIntent().getSerializableExtra("listaUsuarios");

        // Verificar si la lista de usuarios no es nula
        if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
            for (Usuario usuario : listaUsuarios) {
                agregarFilaATabla(usuario);
            }
        }
    }

    private void agregarFilaATabla(Usuario usuario) {
        TableRow tableRow = new TableRow(this);

        TextView tvNombre = new TextView(this);
        tvNombre.setText(usuario.getNombre());
        tableRow.addView(tvNombre);



        TextView tvSexo = new TextView(this);
        tvSexo.setText(usuario.getSexo());
        tableRow.addView(tvSexo);

        TextView tvFechaNacimiento = new TextView(this);
        tvFechaNacimiento.setText(usuario.getFechaNacimiento());
        tableRow.addView(tvFechaNacimiento);


        TextView tvEstado = new TextView(this);
        tvEstado.setText(usuario.isEstado() ? "Activo" : "Inactivo");
        tableRow.addView(tvEstado);

        // Agregar la fila al TableLayout
        tableLayoutUsuarios.addView(tableRow);
    }
    public void volver(View view) {
        // Finaliza la actividad actual y vuelve a la anterior
        finish();
    }
}
