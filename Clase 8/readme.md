
# Proyecto Android Java para Gestión de Empleos con Firebase

## Objetivo del Proyecto

Este proyecto tiene como objetivo guiar a los estudiantes en la creación de una aplicación Android que permita a las **Empresas** publicar empleos y a los **Trabajadores** buscar oportunidades laborales. Implementaremos autenticación, almacenamiento de perfiles con imágenes, y registro en tiempo real de los empleos publicados.

## Tecnologías Utilizadas

1. **Firebase Realtime Database**: Para guardar en tiempo real la información de usuarios y empleos.
2. **Firebase Authentication**: Para gestionar el acceso seguro de los usuarios.
3. **Firebase Storage**: Usado para almacenar imágenes de perfil.
4. **Material Design**: Para crear una interfaz moderna y fácil de usar.
5. **Java en Android Studio**: Herramienta principal para el desarrollo del proyecto.

## Configuración de Firebase

1. **Firebase Realtime Database**:
   - **Habilitación**: En Firebase Console, selecciona el proyecto y activa **Realtime Database** en modo "Testing".
   - **Estructura esperada**: La base de datos tendrá las rutas `users` y `jobs`.

2. **Firebase Storage**:
   - **Habilitación**: Activa **Firebase Storage** desde Firebase Console.
   - **Configuración de Seguridad**: Cambia las reglas de seguridad a modo de prueba si es necesario para desarrollo.
   - **Ubicación de las imágenes**: Las imágenes de perfil se guardarán en la carpeta `profile_images/` y estarán referenciadas por el `userId` único de cada usuario.

**Código de Configuración de Firebase Storage**:

Para almacenar la imagen de perfil del usuario al momento de registro:

```java
FirebaseStorage storage = FirebaseStorage.getInstance();
StorageReference storageRef = storage.getReference();
StorageReference profileImagesRef = storageRef.child("profile_images/" + userId + ".jpg");

profileImagesRef.putFile(imageUri)
    .addOnSuccessListener(taskSnapshot -> {
        profileImagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
            // Guarda la URL de descarga en la base de datos para referenciar la imagen
            user.setImageUrl(uri.toString());
            mDatabase.child("users").child(userId).setValue(user);
        });
    })
    .addOnFailureListener(e -> {
        // Manejo de error
    });
```

---

## Flujo de la Aplicación

1. **Inicio de Sesión o Registro**: 
   - Los usuarios pueden iniciar sesión con su correo y contraseña o registrarse. Si el usuario elige registrarse, el sistema solicita su nombre, rol (Empresa o Trabajador) y una imagen de perfil.

2. **Perfil de Empresa**: 
   - Las empresas pueden gestionar sus empleos, agregando, editando o eliminando publicaciones desde su perfil.
  
3. **Búsqueda de Empleo para Trabajador** *(en desarrollo)*:
   - Los trabajadores podrán ver las ofertas disponibles y postularse, funcionalidad actualmente en desarrollo.

---

## Estructura del Proyecto

```
📦 ProyectoAndroid
 ┣ 📂java
 ┃ ┗ 📂com.example.loginperfilimg
 ┃   ┣ 📜LoginActivity.java              # Autenticación de usuario
 ┃   ┣ 📜ProfileActivity.java            # Perfil de Empresa y gestión de empleos
 ┃   ┣ 📜JobAdapter.java                 # Adaptador para lista de empleos
 ┃   ┗ 📜User.java                       # Modelo de usuario
 ┣ 📂res
 ┃ ┣ 📂layout
 ┃ ┃ ┣ 📜activity_login.xml              # Diseño de pantalla de inicio de sesión
 ┃ ┃ ┣ 📜activity_profile.xml            # Diseño de perfil para Empresa
 ┃ ┃ ┗ 📜item_job.xml                    # Elemento individual de empleo en lista
 ┗ 📜README.md                           # Documentación del proyecto
```

---

## Explicación del Código y Resultados Esperados

### 1. LoginActivity.java - Registro y Login

**Objetivo**: Proporcionar acceso seguro a la aplicación mediante autenticación de usuario.

**Código Principal**:

```java
mAuth.signInWithEmailAndPassword(email, password)
    .addOnCompleteListener(this, task -> {
        if (task.isSuccessful()) {
            // Iniciar sesión y redirigir al perfil
        } else {
            // Mostrar error en caso de fallo
        }
    });
```

**Resultados Esperados**:
   - Los datos de usuario se autentican contra Firebase Authentication.
   - Al registrar un nuevo usuario, se guarda en `users` con imagen almacenada en `Firebase Storage`.
  
**Imagen 1**: *Captura de pantalla del inicio de sesión en la app.*

---

### 2. ProfileActivity.java - Gestión de Empleos para Empresa

**Objetivo**: Permitir a las empresas administrar sus empleos, publicándolos y actualizándolos en tiempo real.

**Código Principal**:

```java
DatabaseReference jobsRef = FirebaseDatabase.getInstance().getReference("jobs");
jobsRef.child(jobId).setValue(newJob)
    .addOnCompleteListener(task -> {
        if (task.isSuccessful()) {
            // Confirmación de creación del empleo
        }
    });
```

**Resultados Esperados**:
   - Cada empleo se almacena en `jobs`, relacionado a la empresa mediante el `companyEmail`.

**Imagen 2**: *Vista del perfil de Empresa, con botón para añadir empleos.*

---

### 3. User.java - Modelo de Usuario

**Objetivo**: Definir el modelo de datos del usuario, incluyendo nombre, email, rol y URL de imagen.

**Código Principal**:

```java
public class User {
    private String name;
    private String email;
    private String role;
    private String imageUrl;

    public User(String name, String email, String role, String imageUrl) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
    }
    // Getters y setters omitidos para brevedad
}
```

**Resultado Esperado**: Cada usuario se almacena en `users`, con su imagen guardada en `Firebase Storage`.

**Imagen 3**: *Estructura en Firebase Realtime Database mostrando usuarios y empleos.*

---

## Estructura de Firebase

**Usuarios (`users`)**: Cada usuario contiene la siguiente información:

```plaintext
users
 └── userId
     ├── name: "Empresa A"
     ├── email: "empresa@example.com"
     ├── role: "Empresa"
     └── imageUrl: "https://firebasestorage.googleapis.com/...
```

**Empleos (`jobs`)**: Cada empleo contiene los detalles específicos de la empresa y la oferta de trabajo.

```plaintext
jobs
 └── jobId
     ├── title: "Desarrollador Android"
     ├── description: "Experiencia en desarrollo móvil..."
     ├── expiryDate: "2024-12-31"
     ├── mode: "Full-time"
     ├── salary: "1000"
     ├── vacancies: "3"
     ├── companyEmail: "empresa@example.com"
     └── status: "Publicado"
```

---

## Flujo General de la Aplicación

1. **Registro/Login**: 
   - Los usuarios se autentican o registran y suben su imagen de perfil.

2. **Perfil de Empresa**: 
   - Las empresas ven y gestionan sus empleos, que aparecen en `ProfileActivity` tras ser creados.

3. **Búsqueda para Trabajadores** *(en proceso)*: 
   - Los trabajadores verán empleos y podrán postularse, esta sección está en desarrollo.
