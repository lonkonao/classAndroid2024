package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onclick (View view){
        EditText txtNombre = findViewById(R.id.txtNombre);
        String nombre =txtNombre.getText().toString().trim();

        if (nombre.isBlank()){

            txtNombre.setError("Campo Vacio");
        }else{
            Toast.makeText(this, "Hola "+nombre, Toast.LENGTH_SHORT).show();
        }

    }

    public void toMinToMay (View v) {
        EditText minuscula = findViewById(R.id.txtMin);
        TextView resultado = findViewById(R.id.lbl);

        String texto = minuscula.getText().toString().trim();

        if (texto.isEmpty()){
            minuscula.setError("Ingrese un texto");
        }else {
            resultado.setText(texto.toUpperCase());
        }


    }


}