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







