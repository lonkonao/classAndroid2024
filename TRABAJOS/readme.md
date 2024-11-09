
# App de Gestión de Contraseñas Segura

## Descripción
Este proyecto consiste en crear una aplicación básica para la gestión de contraseñas. La app permite a los usuarios registrarse, añadir, ver, editar y eliminar contraseñas. Cada vez que un usuario quiera ver una contraseña, deberá autenticarse mediante huella digital o patrón de desbloqueo para garantizar la seguridad.

## Funcionalidades

1. **Registro e Inicio de Sesión con Firebase**:
   - Permitir a los usuarios registrarse e iniciar sesión de manera segura mediante Firebase Authentication.

2. **CRUD de Contraseñas**:
   - **Crear**: Permitir al usuario agregar nuevas contraseñas incluyendo:
     - Nombre del sitio o aplicación
     - Nombre de usuario
     - Contraseña
     - Notas adicionales (opcional)
   - **Leer (Ver)**: Para ver una contraseña guardada, el usuario debe autenticarse mediante huella digital o patrón.
   - **Actualizar**: Los usuarios pueden editar una entrada de contraseña.
   - **Eliminar**: Eliminar entradas de contraseñas innecesarias.

3. **Autenticación para Ver Contraseñas**:
   - Investigar y utilizar `BiometricPrompt` o `KeyguardManager` para la autenticación. La autenticación es necesaria cada vez que el usuario intenta ver una contraseña.

## Tecnologías Utilizadas

- **Firebase Authentication**: Para el registro e inicio de sesión de los usuarios.
- **Firebase Firestore**: Para almacenar las contraseñas de cada usuario.
- **BiometricPrompt o KeyguardManager**: Para la autenticación de seguridad adicional.

## Implementar la Autenticación Biométrica o de Patrón:

   - Investigar el uso de `BiometricPrompt` y `KeyguardManager`.
   - Aplicar esta autenticación en la pantalla de visualización de contraseñas.

## Pauta de Evaluación

| Criterio                                | Descripción                                                                                  | Puntos |
|-----------------------------------------|----------------------------------------------------------------------------------------------|--------|
| **Registro e Inicio de Sesión**         | Implementación correcta de registro e inicio de sesión con Firebase Authentication           | 20     |
| **CRUD de Contraseñas**                 | Implementación de las funciones de agregar, ver, editar y eliminar contraseñas               | 30     |
| **Autenticación para Visualización**    | Uso de `BiometricPrompt` o `KeyguardManager` para ver las contraseñas                        | 20     |
| **Organización del Código**             | Código bien estructurado y comentado                                                         | 10     |
| **Interfaz de Usuario**                 | Diseño con Material Design                                                                   | 10     |
| **Documentación del Proyecto**          | README.md que incluya una breve explicación del proyecto y usarlo                            | 10     |

**Total**: 100 puntos

## Instrucciones de Entrega

- La entrega debe realizarse a más tardar el **jueves a mediodía 13:00 MAX.**.
- Este ejercicio puede reemplazar una nota anterior y será parte del promedio de la **nota 5**.
- La **nota 4** corresponde a la presentación del proyecto.
- El proyecto debe subirse a GitHub. Luego, el enlace debe ser agregado en la issue del proyecto **android2024**, con una descripción de cómo acceder para ejecutar el programa, incluyendo la configuración de Firebase.
