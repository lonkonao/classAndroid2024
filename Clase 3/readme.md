# Proyecto: Registro y Listado de Usuarios en Android

## Descripción del Proyecto

Este proyecto consiste en una aplicación Android básica que permite a los usuarios registrarse mediante un formulario. Los datos ingresados se almacenan y luego se pueden visualizar en un listado. Este proyecto es ideal para aprender conceptos fundamentales de Android como la captura de datos a través de vistas, manejo de eventos con botones, y almacenamiento básico en listas.

## Estructura del Proyecto

### 1. Diseño del Formulario en `MainActivity`

El formulario de registro se compone de varios elementos básicos de la interfaz de usuario de Android:

```xml
<EditText
    android:id="@+id/et_nombre"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Nombre"/>

<EditText
    android:id="@+id/et_apellidos"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Apellidos"/>

<DatePicker
    android:id="@+id/dp_fecha_nacimiento"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    android:calendarViewShown="false"
    android:datePickerMode="spinner"
    android:layout_gravity="center"/>

<RadioGroup
    android:id="@+id/rg_sexo"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <RadioButton
        android:id="@+id/rb_masculino"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Masculino"/>
    <RadioButton
        android:id="@+id/rb_femenino"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Femenino"/>
</RadioGroup>
```

### 2. Lógica de Captura y Almacenamiento en `MainActivity`

La lógica de la actividad principal (`MainActivity`) se encarga de capturar los datos ingresados por el usuario y almacenarlos en una lista para su posterior visualización.

```java
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

    Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, sexo, intereses, estado);
    listaUsuarios.add(usuario);
}
```

## Próximos Pasos

En la próxima clase revisaremos cómo mostrar los usuarios registrados en un listado utilizando `TableLayout` y cómo gestionar la navegación entre actividades en Android.
