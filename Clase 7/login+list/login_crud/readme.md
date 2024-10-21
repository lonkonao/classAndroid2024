
# Proyecto Android con Login y Listado de Usuarios usando Firebase

Este proyecto es una aplicación básica de Android que permite a los usuarios registrarse y autenticarse utilizando Firebase Authentication. Los datos adicionales del usuario (nombre, edad, y correo) se almacenan en Firebase Realtime Database, y se listan en una vista con un `RecyclerView`.

## Requisitos Previos

- Android Studio instalado.
- Cuenta de Firebase.
- Conocimiento básico de Android y Java.

## Configuración del Proyecto

### 1. Crear un Nuevo Proyecto en Android Studio

1. Abre Android Studio y selecciona **New Project**.
2. Elige **Empty Activity**.
3. Nombra tu proyecto (ejemplo: `LoginFirebase`).
4. Asegúrate de seleccionar **Java** como lenguaje y haz clic en **Finish**.

### 2. Configurar Firebase

1. Accede a [Firebase Console](https://console.firebase.google.com/).
2. Crea un nuevo proyecto en Firebase.
3. Agrega tu aplicación Android al proyecto:
   - Proporciona el `package name` de tu app.
   - Descarga el archivo `google-services.json` y colócalo en la carpeta `app` de tu proyecto.
4. En la consola de Firebase, habilita el método de **Correo/Contraseña** en la sección **Authentication**.
5. Configura una **Realtime Database** en modo de prueba para desarrollo.

### 3. Agregar Dependencias de Firebase

Agrega las siguientes dependencias en el archivo `build.gradle` de la app:

```gradle
implementation 'com.google.firebase:firebase-auth:22.1.1'
implementation 'com.google.firebase:firebase-database:20.2.2'
```

Sincroniza el proyecto para aplicar los cambios.

### 4. Diseñar la Interfaz de Usuario

Modifica el archivo `activity_main.xml` para incluir campos de registro y botones:

```xml
<EditText android:id="@+id/editTextEmail" ... />
<EditText android:id="@+id/editTextPassword" ... />
<EditText android:id="@+id/editTextNombre" ... />
<EditText android:id="@+id/editTextEdad" ... />
<Button android:id="@+id/buttonRegister" ... />
<Button android:id="@+id/buttonLogin" ... />
<Button android:id="@+id/buttonVerUsuarios" ... />
```

### 5. Configurar el Registro y Autenticación

En `MainActivity.java`, agrega el código para registrar un usuario y guardar datos adicionales en Firebase Realtime Database:

```java
private void crearUsuario(String email, String password, String nombre, String edad) {
    mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                guardarDatosAdicionales(user, nombre, edad);
            }
        });
}

private void guardarDatosAdicionales(FirebaseUser user, String nombre, String edad) {
    String userId = user.getUid();
    String email = user.getEmail();
    Map<String, Object> datosUsuario = new HashMap<>();
    datosUsuario.put("nombre", nombre);
    datosUsuario.put("edad", edad);
    datosUsuario.put("correo", email);

    mDatabase.child("usuarios").child(userId).setValue(datosUsuario);
}
```

### 6. Listar Usuarios en un `RecyclerView`

1. Crea una nueva actividad `ListaUsuariosActivity.java` y un `RecyclerView` en su layout para mostrar la lista de usuarios.
2. Define la clase `Usuario` con `nombre`, `edad` y `correo`.
3. Crea un adaptador `UsuarioAdapter` para el `RecyclerView` y configura la vista de cada usuario.

### 7. Navegar a la Lista de Usuarios

Agrega un `Intent` en `MainActivity.java` para abrir `ListaUsuariosActivity` al hacer clic en el botón de "Ver Usuarios":

```java
buttonVerUsuarios.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this, ListaUsuariosActivity.class);
    startActivity(intent);
});
```

### 8. Probar la Aplicación

1. Registra un usuario desde la app.
2. Verifica que los datos se guarden en Firebase Realtime Database.
3. Navega a la vista de usuarios y asegúrate de que se muestran correctamente en la lista.

## Notas

- Recuerda actualizar las reglas de seguridad de Firebase antes de lanzar la aplicación a producción.
- Esta guía es para propósitos de desarrollo. No olvides aplicar las mejores prácticas de seguridad antes de publicar tu app.
