package com.example.ejercicio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private ArrayList<String> listaTextos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnVer = findViewById(R.id.btnVer);

        btnGuardar.setOnClickListener(v -> {
            String texto = inputText.getText().toString().trim();
            if (!texto.isEmpty()) {
                listaTextos.add(texto);
                inputText.setText("");
                Toast.makeText(this, "Texto guardado", Toast.LENGTH_SHORT).show();
            }
        });

        btnVer.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListActivity.class);
            intent.putStringArrayListExtra("listaTextos", listaTextos);
            startActivity(intent);
        });
        }



    }

