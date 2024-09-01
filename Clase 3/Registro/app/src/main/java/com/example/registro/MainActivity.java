package com.example.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etApellidos;
    private DatePicker dpFechaNacimiento;
    private RadioGroup rgSexo;
    private CheckBox cbDeportes, cbMusica, cbLectura;
    private Switch swEstado;
    private Button btnGuardar, btnVerListado;

    // Lista para almacenar los usuarios ingresados
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        etNombre = findViewById(R.id.et_nombre);
        etApellidos = findViewById(R.id.et_apellidos);
        dpFechaNacimiento = findViewById(R.id.dp_fecha_nacimiento);
        rgSexo = findViewById(R.id.rg_sexo);
        cbDeportes = findViewById(R.id.cb_deportes);
        cbMusica = findViewById(R.id.cb_musica);
        cbLectura = findViewById(R.id.cb_lectura);
        swEstado = findViewById(R.id.sw_estado);
        btnGuardar = findViewById(R.id.btn_guardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });
    }

    private void guardarUsuario() {

        String nombre = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String fechaNacimiento = dpFechaNacimiento.getDayOfMonth() + "/" +
                (dpFechaNacimiento.getMonth() + 1) + "/" + dpFechaNacimiento.getYear();
        int selectedSexoId = rgSexo.getCheckedRadioButtonId();
        RadioButton rbSexo = findViewById(selectedSexoId);
        String sexo = rbSexo != null ? rbSexo.getText().toString() : "";
        List<String> intereses = new ArrayList<>();
        if (cbDeportes.isChecked()) intereses.add("Deportes");
        if (cbMusica.isChecked()) intereses.add("Música");
        if (cbLectura.isChecked()) intereses.add("Lectura");
        boolean estado = swEstado.isChecked();

    }

}

