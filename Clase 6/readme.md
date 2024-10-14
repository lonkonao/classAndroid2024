

# Proyecto de Login con Firebase en Android

Este proyecto es parte de una clase donde implementamos un sistema de autenticación en Android utilizando Firebase. A continuación, explico los pasos seguidos y los conceptos clave que se cubrieron durante la clase.

## Descripción del Proyecto

Desarrollamos una aplicación básica de Android que permite a los usuarios registrarse, iniciar sesión y cerrar sesión utilizando Firebase Authentication. También se configuró la redirección hacia una pantalla de inicio (`HomeActivity`) cuando el usuario inicia sesión correctamente.

## Configuración de Firebase

1. **Conectar Firebase a la App:**
   - Utilizamos la opción **Tools > Firebase** en Android Studio para conectar el proyecto a Firebase.
   - Agregamos el SDK de Firebase y configuramos `google-services.json` en el proyecto.

2. **Pantallazo:**
   
   <img width="512" alt="image" src="https://github.com/user-attachments/assets/a8666610-40d8-4a66-9e50-68e9bfe7f943">


## Funcionalidades Implementadas

### 1. Registro de Usuario
   - Se implementó la funcionalidad para permitir que los usuarios se registren utilizando su correo electrónico y contraseña.
   - Cuando el registro es exitoso, el usuario es redirigido a la pantalla de inicio.

   - <img width="794" alt="image" src="https://github.com/user-attachments/assets/9b7c218e-96f3-42ef-9410-6f829bb85178">

   - ![image](https://github.com/user-attachments/assets/2b11771e-ac8a-4110-bc9f-3ced3215add7)

   - <img width="251" alt="image" src="https://github.com/user-attachments/assets/a5732038-7f82-4116-bff9-53cc9ac72e34"> 

### 2. Inicio de Sesión
   - Los usuarios pueden iniciar sesión con las credenciales que utilizaron durante el registro.
   - Si las credenciales son correctas, se redirige al usuario a la pantalla de inicio.

   - <img width="259" alt="image" src="https://github.com/user-attachments/assets/e09627ec-1b97-4024-8255-0d1fdfb5d485">
   - <img width="879" alt="image" src="https://github.com/user-attachments/assets/6241a535-c5ff-41fa-92a6-f8c109afc630">
   - ![image](https://github.com/user-attachments/assets/2c606851-28c2-44f7-bc13-e673e87d1487)


   
### 3. Cierre de Sesión
   - Agregamos la funcionalidad para que el usuario pueda cerrar sesión.
   
### 4. Redirección a la Pantalla de Inicio
   - Cuando el usuario inicia sesión correctamente, es redirigido a la actividad `HomeActivity`.


## Código Principal

### `MainActivity.java`
   - Aquí se manejan el registro, inicio y cierre de sesión. También se controla la redirección a `HomeActivity` cuando el login es exitoso.

   ```java
   private void signIn(String email, String password) {
       mAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()) {
                       FirebaseUser user = mAuth.getCurrentUser();
                       updateUI(user);
                       // Redirigir a la pantalla de inicio
                       startActivity(new Intent(MainActivity.this, HomeActivity.class));
                   } else {
                       Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                   }
               }
           });
   }
   ```


## Próximos Pasos
   - En la próxima clase se espera que el sistema de inicio de sesión, registro y cierre de sesión esté completamente operativo. Además, al ingresar con un usuario válido, se debe mostrar su perfil con la información correspondiente, siguiendo los mockups proporcionados por el líder del proyecto. **Se evaluará el front-end, back-end, documentación y pruebas de funcionamiento.**

## Recursos
   - Firebase Authentication: [Documentación Oficial](https://firebase.google.com/docs/auth)
   - Android Studio: [Guía Rápida](https://developer.android.com/studio/intro)
   - Clase 6 [Clase 6.pdf](https://github.com/user-attachments/files/17364290/Clase.6.pdf)


