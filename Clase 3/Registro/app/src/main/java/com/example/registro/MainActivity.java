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

import androidx.appcompat.app.AppCompatActivity;

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
        btnVerListado = findViewById(R.id.btn_ver_listado);

        // Configurar el botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });

        // Configurar el botón Ver Listado
        btnVerListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListado();
            }
        });
    }

    private void guardarUsuario() {
        // Obtener los datos del formulario
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

        // Validar que todos los campos requeridos estén llenos
        if (nombre.isEmpty() || apellidos.isEmpty() || sexo.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, complete todos los campos requeridos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un objeto Usuario y añadirlo a la lista
        Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, sexo, intereses, estado);
        listaUsuarios.add(usuario);

        // Mostrar un mensaje de éxito
        Toast.makeText(MainActivity.this, "Usuario guardado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos del formulario
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etApellidos.setText("");
        rgSexo.clearCheck();
        cbDeportes.setChecked(false);
        cbMusica.setChecked(false);
        cbLectura.setChecked(false);
        swEstado.setChecked(false);
    }

    private void abrirListado() {
        // Crear un Intent para abrir la actividad del listado
        Intent intent = new Intent(MainActivity.this, ListadoUsuariosActivity.class);
        // Pasar la lista de usuarios a la nueva actividad
        intent.putExtra("listaUsuarios", (ArrayList<Usuario>) listaUsuarios);
        startActivity(intent);
    }
}
