# Proyecto CRUD Básico en Android con Firebase

Se realiza un CRUD básico en Android Studio utilizando Java y Firebase Realtime Database como base de datos. Los estudiantes implementan funcionalidades para crear, leer, actualizar y eliminar datos. Además, reviso el avance del proyecto Vanner, proporcionando retroalimentación y orientando sobre los próximos pasos a seguir para continuar con el desarrollo del proyecto.

## Descripción del Proyecto

Este proyecto implementa una aplicación móvil en Android que permite gestionar una lista de contactos. Las principales funcionalidades son:

- **Crear**: Permite añadir un nuevo contacto ingresando su nombre y edad.
- **Leer**: Muestra una lista de todos los contactos almacenados en Firebase.
- **Actualizar**: Permite modificar la información de un contacto existente.
- **Eliminar**: Permite borrar un contacto de la base de datos.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación utilizado para el desarrollo de la aplicación.
- **Firebase Realtime Database**: Base de datos en tiempo real para almacenar y sincronizar datos.
- **Android Studio**: Entorno de desarrollo integrado (IDE) para crear la aplicación.

## Configuración del Proyecto

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Abre el proyecto en **Android Studio**.

3. Conecta el proyecto a **Firebase**:
   - En Android Studio, ve a **Tools > Firebase**.
   - Selecciona **Realtime Database** y sigue las instrucciones para conectar tu proyecto.

4. Agrega el archivo `google-services.json` descargado desde la consola de Firebase a la carpeta `app`.

5. Sincroniza el proyecto para aplicar las dependencias de Firebase.

6. Configura las reglas de Firebase Realtime Database para permitir lectura y escritura:
   ```json
   {
     "rules": {
       ".read": true,
       ".write": true
     }
   }
   ```

## Uso de la Aplicación

- Al iniciar la aplicación, puedes crear un nuevo contacto ingresando el nombre y la edad.
- La lista de contactos se actualiza automáticamente con los datos almacenados en Firebase.
- Puedes buscar un contacto existente, editar su información o eliminarlo de la base de datos.

## Capturas de Pantalla

<img width="431" alt="image" src="https://github.com/user-attachments/assets/942d3577-f81c-41d7-8ffe-54cc929513ef">

<img width="828" alt="image" src="https://github.com/user-attachments/assets/111bb0bb-fce6-4555-aea7-638217bb4f0d">

<img width="783" alt="image" src="https://github.com/user-attachments/assets/e026c1a5-29bd-4d92-8707-5e9705911eae">

<img width="423" alt="image" src="https://github.com/user-attachments/assets/056014f5-d1aa-4cec-a3c3-8bee16bef9c6">

<img width="515" alt="image" src="https://github.com/user-attachments/assets/4e7dc743-ba32-4374-b649-0d51eb907b09">

<img width="776" alt="image" src="https://github.com/user-attachments/assets/93087be6-cdab-4aa0-b687-2b7c1e074f89">

# Proxima Evaluación

## Deseables

Objetivo implementar una aplicación móvil Android que incluya las siguientes funcionalidades:

1. **Inicio de sesión (Registro e Inicio de Sesión):**

   - Los usuarios deben poder registrarse y luego iniciar sesión utilizando su correo y contraseña.
   - Validaciones de campos obligatorios para registro e inicio de sesión.

2. **Perfil de Usuario:**

   - Una vez el usuario haya iniciado sesión, debe ver un perfil con sus datos (nombre, correo, etc.).
   - Si el usuario es administrador, debe tener acceso a un listado de todos los usuarios registrados en la aplicación.

3. **Roles del Equipo:**
   Cada equipo debe tener un encargado de:
   - **Front-end**: Implementación de la interfaz de usuario y conexión con el back-end.
   - **Back-end**: Gestión de la lógica de negocio, la base de datos, y control de accesos.
   - **Documentación**: Redacción del manual de usuario y la documentación técnica.
   - **Pruebas de Funcionamiento**: Realización de pruebas de funcionalidad y reporte de errores.
   - **Líder**: Coordinación del equipo y asegurarse de que todo el equipo cumpla con los plazos y estándares de calidad.

## Requisitos Técnicos

- **Lenguaje**: Java
- **Plataforma**: Android Studio
- **Base de datos**: Firebase Realtime Database

## Rúbrica de Evaluación

### 1. Desarrollador Front-end (25 puntos)

| **Criterio**                                     | **No logrado** (0-3 pts)         | **Medianamente logrado** (4-7 pts) | **Logrado** (8-10 pts)                | **Puntos** |
| ------------------------------------------------ | -------------------------------- | ---------------------------------- | ------------------------------------- | ---------- |
| **Diseño de interfaz de usuario**                | Interfaz confusa o inconsistente | Funcional pero con inconsistencias | Interfaz intuitiva y consistente      | /10        |
| **Funcionalidad de registro e inicio de sesión** | Errores críticos en formularios  | Funciona con algunos errores       | Formularios funcionando correctamente | /10        |
| **Integración con back-end**                     | Sin comunicación con servidor    | Comunicación parcial con problemas | Comunicación efectiva con el servidor | /5         |

### 2. Desarrollador Back-end (25 puntos)

| **Criterio**                            | **No logrado** (0-3 pts)             | **Medianamente logrado** (4-7 pts)   | **Logrado** (8-10 pts)            | **Puntos** |
| --------------------------------------- | ------------------------------------ | ------------------------------------ | --------------------------------- | ---------- |
| **Implementación de lógica de negocio** | Lógica incorrecta o inexistente      | Implementada con errores             | Correcta y eficiente              | /10        |
| **Gestión de base de datos**            | Almacenamiento incorrecto o inseguro | Funcional pero con problemas menores | Almacenamiento seguro y eficiente | /10        |
| **Control de accesos y roles**          | Sin control de accesos               | Control de accesos básico            | Roles diferenciados correctamente | /5         |

### 3. Documentación (25 puntos)

| **Criterio**                 | **No logrado** (0-3 pts)                | **Medianamente logrado** (4-7 pts)            | **Logrado** (8-10 pts)    | **Puntos** |
| ---------------------------- | --------------------------------------- | --------------------------------------------- | ------------------------- | ---------- |
| **Manual de usuario**        | Inexistente o incomprensible            | Cubre funciones básicas con falta de claridad | Claro y detallado         | /10        |
| **Documentación técnica**    | Inexistente o insuficiente              | Parcial con falta de detalles                 | Completa y detallada      | /10        |
| **Comentarios en el código** | Sin comentarios o prácticas deficientes | Comentarios limitados                         | Bien documentado y limpio | /5         |

### 4. Pruebas de Funcionamiento (25 puntos)

| **Criterio**                   | **No logrado** (0-3 pts)             | **Medianamente logrado** (4-7 pts)       | **Logrado** (8-10 pts)                | **Puntos** |
| ------------------------------ | ------------------------------------ | ---------------------------------------- | ------------------------------------- | ---------- |
| **Plan de pruebas**            | Inexistente o inapropiado            | Cubre algunos casos pero es incompleto   | Casos de prueba completos             | /10        |
| **Ejecución de pruebas**       | Sin pruebas o sin reporte de errores | Bugs identificados pero mal documentados | Identificación detallada de bugs      | /10        |
| **Validación de correcciones** | Sin verificación                     | Verificación parcial                     | Verificación completa de correcciones | /5         |

### Evaluación del Líder (100 puntos)

- El puntaje del líder será la **suma de los puntajes obtenidos** por cada uno de los roles en su equipo.








