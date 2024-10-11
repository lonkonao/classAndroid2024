package com.example.ejercicio;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listactivity);

        ListView listView = findViewById(R.id.listView);
        Button btnVolver = findViewById(R.id.btnVolver);

        ArrayList<String> listaTextos = getIntent().getStringArrayListExtra("listaTextos");

        if (listaTextos != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTextos);
            listView.setAdapter(adapter);
        }

        btnVolver.setOnClickListener(v -> finish());

    };
}
