# **App de Gestión de Contraseñas Segura**

Este proyecto es una aplicación básica para la gestión de contraseñas, diseñada para que los usuarios puedan guardar, visualizar, editar y eliminar sus contraseñas de forma segura. Además de usar Firebase, incluye una capa de seguridad adicional mediante cifrado personalizado.

---

## **Funcionalidades**

### 1. **Registro e Inicio de Sesión con Firebase**
   - Permite a los usuarios registrarse y autenticarse mediante Firebase Authentication.

### 2. **CRUD de Contraseñas**
   - **Crear:** Los usuarios pueden guardar contraseñas con los siguientes campos:
     - Nombre del sitio o aplicación
     - Nombre de usuario
     - Contraseña
     - Notas adicionales (opcional)
   - **Leer:** Para visualizar las contraseñas almacenadas, es obligatorio autenticarse mediante huella digital o patrón.
   - **Actualizar:** Edición de contraseñas y campos relacionados.
   - **Eliminar:** Opción para eliminar contraseñas que ya no sean necesarias.

### 3. **Cifrado Personalizado de Contraseñas**
   - Las contraseñas no se almacenan en texto plano, sino que se cifran utilizando **AES (Advanced Encryption Standard)**.
   - Cada contraseña utiliza una clave única generada a partir del ID del usuario y un valor de sal (salt).
   - El cifrado y descifrado se realiza de forma local antes de interactuar con Firebase Firestore.

### 4. **Autenticación Biométrica para Visualización**
   - Se utiliza **BiometricPrompt** o **KeyguardManager** para garantizar que solo el usuario autenticado pueda ver sus contraseñas.

---

## **Configuración del Proyecto**

1. **Configurar Firebase**
   - Crea un proyecto en [Firebase Console](https://console.firebase.google.com/).
   - Activa Firebase Authentication y Firestore.
   - Descarga y agrega el archivo `google-services.json` a la carpeta `app/` de tu proyecto Android.

2. **Cifrado de Contraseñas ESTUDIAR**
   - Implementa un método de cifrado AES para proteger las contraseñas.
   - La clave de cifrado debe generarse a partir del UID del usuario y un valor único (salt).
   - Antes de almacenar una contraseña en Firebase Firestore, asegúrate de cifrarla.
   - Cuando se recupera una contraseña, descífrala localmente.

3. **Autenticación Biométrica**
   - Configura el uso de **BiometricPrompt** o **KeyguardManager** para proteger la pantalla de visualización de contraseñas.

4. **Instalar Dependencias**
   - Agrega las siguientes dependencias al archivo `build.gradle`:
     ```gradle
     implementation 'com.google.firebase:firebase-auth:latest_version'
     implementation 'com.google.firebase:firebase-firestore:latest_version'
     implementation 'androidx.biometric:biometric:latest_version'
     ```

---

## **Pauta de Evaluación**

| **Criterio**                 | **Descripción**                                                       | **Puntos** |
|-------------------------------|-----------------------------------------------------------------------|------------|
| Registro e Inicio de Sesión   | Implementación correcta de registro e inicio de sesión con Firebase Authentication. | 20         |
| CRUD de Contraseñas           | Implementación de las funciones de agregar, ver, editar y eliminar contraseñas.     | 30         |
| Cifrado de Contraseñas        | Uso de cifrado personalizado con AES para proteger las contraseñas.             | 20         |
| Autenticación para Visualización | Uso de BiometricPrompt o KeyguardManager para garantizar la seguridad.            | 20         |
| Organización del Código       | Código bien estructurado y comentado.                                   | 10         |
| Interfaz de Usuario           | Diseño con Material Design.                                           | 10         |
| Documentación del Proyecto    | README.md completo con instrucciones claras.                         | 10         |

**Total: 100 puntos**

---

## **Instrucciones de Entrega**

- La entrega debe realizarse a más tardar el jueves a las 13:00.
- Sube el proyecto a un repositorio de GitHub.
- En la issue del proyecto **android2024**, incluye el enlace al repositorio y las instrucciones para ejecutar el programa (incluyendo la configuración de Firebase).

---

## **Cifrado Personalizado de Contraseñas**

El cifrado personalizado es una técnica utilizada para proteger las contraseñas antes de almacenarlas en Firestore. Este proyecto utiliza el estándar **AES (Advanced Encryption Standard)**, que es un cifrado simétrico ampliamente reconocido por su seguridad.

1. **Clave de Cifrado Dinámica:**
   - La clave de cifrado se genera a partir del **UID del usuario** y un valor único llamado **salt**. Esto asegura que cada usuario tenga una clave única.

2. **Proceso de Cifrado:**
   - Antes de almacenar una contraseña, se cifra utilizando la clave generada.
   - Este proceso convierte la contraseña en un texto ilegible que solo puede ser descifrado con la clave correcta.

3. **Proceso de Descifrado:**
   - Cuando el usuario desea ver una contraseña, la app utiliza la clave generada dinámicamente para descifrarla.

4. **Ventajas:**
   - Las contraseñas nunca se almacenan en texto plano, lo que protege los datos incluso si la base de datos es comprometida.
   - La clave de cifrado es única para cada usuario, lo que previene ataques masivos de descifrado.
  
## Ejemplos

`Cifrado`

   ```java
   import javax.crypto.Cipher
   import javax.crypto.KeyGenerator
   import javax.crypto.SecretKey
   import javax.crypto.spec.SecretKeySpec
   import android.util.Base64
   
   fun encrypt(data: String, secretKey: String): String {
       val key = SecretKeySpec(secretKey.toByteArray(), "AES")
       val cipher = Cipher.getInstance("AES")
       cipher.init(Cipher.ENCRYPT_MODE, key)
       val encryptedBytes = cipher.doFinal(data.toByteArray())
       return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
   }
   ```

`Descifrado`
   ```java
      fun decrypt(encryptedData: String, secretKey: String): String {
          val key = SecretKeySpec(secretKey.toByteArray(), "AES")
          val cipher = Cipher.getInstance("AES")
          cipher.init(Cipher.DECRYPT_MODE, key)
          val decodedBytes = Base64.decode(encryptedData, Base64.DEFAULT)
          val decryptedBytes = cipher.doFinal(decodedBytes)
          return String(decryptedBytes)
      }
   ```
`Generar Clave Única`
   ```java
      fun generateKey(userUID: String, salt: String): String {
          // Derivar clave usando un algoritmo simple
          return (userUID + salt).take(16) // Tomar los primeros 16 caracteres para AES
      }
```

