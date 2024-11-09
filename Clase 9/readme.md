# README - Proyectos Firebase Múltiples y TestSwipe en Android

Este documento describe dos proyectos en Android:
1. **Proyecto Múltiples Proyectos Firebase**: Configura una aplicación Android para conectarse a dos proyectos diferentes en Firebase. Uno de los proyectos maneja la autenticación y la base de datos, mientras que el otro es exclusivamente para almacenamiento en Firebase Storage.
2. **Proyecto TestSwipe**: Implementa una funcionalidad de "deslizar para aceptar/rechazar colores". Este proyecto utiliza gestos de swipe para permitir que los usuarios acepten o rechacen colores con animaciones y muestra el resultado final.

A continuación, se detalla cómo configurar y trabajar en ambos proyectos.

## Proyecto Múltiples Proyectos Firebase en Android

Este proyecto muestra cómo configurar una aplicación Android para conectarse a dos proyectos diferentes en Firebase: uno para manejar autenticación y base de datos, y otro para almacenamiento.

### Requisitos Previos

1. **Android Studio** instalado.
2. **Cuenta de Firebase** con dos proyectos configurados:
   - **Proyecto Principal** (proyectoA) para autenticación y base de datos.
   - **Proyecto de Almacenamiento** (proyectoStorage) para Firebase Storage.

### Pasos de Configuración

#### 1. Crear y Configurar los Proyectos en Firebase Console

1. Crea ambos proyectos en [Firebase Console](https://console.firebase.google.com/).
2. Añade tu aplicación Android a cada proyecto y descarga el archivo `google-services.json` correspondiente para obtener los datos de configuración.
3. **Importante**: no reemplaces `google-services.json` en tu proyecto. Utilizaremos los datos manualmente para inicializar el segundo proyecto.

#### 2. Configurar el Proyecto Principal (proyectoA)

1. Coloca el archivo `google-services.json` de **proyectoA** en la carpeta `app/` de tu proyecto Android.
2. En el archivo `build.gradle` a nivel de módulo (`app`), añade la siguiente línea para habilitar los servicios de Google:

   ```groovy
   apply plugin: 'com.google.gms.google-services'

3. En build.gradle a nivel de proyecto, asegúrate de incluir la dependencia de Google Services:
    ```groovy
    classpath 'com.google.gms:google-services:4.3.10'
    ```
Esto configura el proyecto proyectoA como el principal para autenticación y base de datos en tu aplicación.

#### 3. Configurar el Segundo Proyecto (proyectoStorage) en `MyApp.java`

Para conectar el segundo proyecto de Firebase (proyectoStorage) exclusivamente para almacenamiento, necesitamos inicializarlo manualmente en una clase de aplicación personalizada. 

1. En la carpeta `app/src/main/java/com/example/loginperfilimg/`, crea una clase llamada `MyApp.java`:

   ```java
   package com.example.loginperfilimg;

   import android.app.Application;
   import com.google.firebase.FirebaseApp;
   import com.google.firebase.FirebaseOptions;

   public class MyApp extends Application {

       @Override
       public void onCreate() {
           super.onCreate();

           // Inicializar el proyecto principal (proyectoA) automáticamente
           FirebaseApp.initializeApp(this);

           // Configurar y registrar el segundo proyecto (proyectoStorage) manualmente
            FirebaseOptions options = new FirebaseOptions.Builder()
             .setProjectId("your_project_id")  // Reemplaza "your_project_id" con el project_id específico de proyectoStorage
             .setApplicationId("your_application_id") // Reemplaza "your_application_id" con el mobilesdk_app_id de proyectoStorage
             .setApiKey("your_api_key") // Reemplaza "your_api_key" con el current_key de proyectoStorage
             .setStorageBucket("your_storage_bucket.appspot.com") // Reemplaza "your_storage_bucket.appspot.com" con el storage_bucket de proyectoStorage
             .build();
           FirebaseApp.initializeApp(this, options, "proyectoStorage");
       }
   }
2. Asegúrate de reemplazar los valores (project_id, application_id, api_key y storage_bucket) con los datos específicos de proyectoStorage que obtuviste del archivo google-services.json.

3. Modificar `AndroidManifest.xml`

Para que la aplicación inicialice ambos proyectos al arrancar, agrega la clase `MyApp` en el archivo `AndroidManifest.xml`:

1. Abre `AndroidManifest.xml`, ubicado en `app/src/main/`.
2. Agrega la clase `MyApp` como la clase de aplicación principal en el elemento `<application>`:

   ```xml
   <application
       android:name=".MyApp"
       android:allowBackup="true"
       android:icon="@mipmap/ic_launcher"
       android:label="@string/app_name"
       android:roundIcon="@mipmap/ic_launcher_round"
       android:supportsRtl="true"
       android:theme="@style/AppTheme">
       <!-- Otras configuraciones, actividades, servicios, etc. -->
   </application>
3. Configurar `RegisterActivity.java` y `FirebaseUtils.java`

Para que la aplicación utilice **proyectoStorage** exclusivamente para almacenamiento, es necesario ajustar el código en `RegisterActivity.java` y `FirebaseUtils.java`.

##### `RegisterActivity.java`

En `RegisterActivity`, cambia la referencia de `FirebaseStorage` para que use el segundo proyecto:

1. En el método `onCreate`, inicializa `FirebaseStorage` con `proyectoStorage`:

   ```java
   import com.google.firebase.FirebaseApp;
   import com.google.firebase.storage.FirebaseStorage;
   import com.google.firebase.storage.StorageReference;

   // Dentro del método onCreate en RegisterActivity.java
   FirebaseApp storageApp = FirebaseApp.getInstance("proyectoStorage");
   StorageReference storageReference = FirebaseStorage.getInstance(storageApp).getReference("profile_images");// Aqui debes cambiar el nombre por el grupo

 Nota: Asegúrate de reemplazar "profile_images" con el nombre del directorio de almacenamiento que desees.   

 ##### `FirebaseUtils.java`

En `FirebaseUtils.java`, ajusta el método `getProfileImagesStorageReference()` para que utilice **proyectoStorage**:

2. Cambia la referencia de `FirebaseStorage` para especificar el segundo proyecto:

   ```java
   public static StorageReference getProfileImagesStorageReference() {
       FirebaseApp storageApp = FirebaseApp.getInstance("proyectoStorage");
       return FirebaseStorage.getInstance(storageApp).getReference("profile_images");
   }
Este cambio asegura que todas las referencias a getProfileImagesStorageReference() utilicen proyectoStorage.

## Conclusión

Con estos pasos, tu aplicación Android ahora está configurada para usar dos proyectos de Firebase de manera independiente:

1. **Proyecto Principal** (**proyectoA**): Se utiliza para manejar la autenticación y la base de datos en Firebase.
2. **Proyecto de Almacenamiento** (**proyectoStorage**): Exclusivamente para almacenamiento de archivos en Firebase Storage.

Gracias a esta configuración, puedes mantener una separación clara entre las funciones de autenticación, base de datos y almacenamiento. Esto es particularmente útil para proyectos que requieren modularidad o que deben cumplir con diferentes necesidades de almacenamiento y autenticación.

## Proyecto TestSwipe: Implementación de Deslizar para Aceptar/Rechazar Colores

El proyecto **TestSwipe** permite a los usuarios deslizar a la izquierda o a la derecha para aceptar o rechazar colores. Esta funcionalidad utiliza un `GestureDetector` para detectar los gestos de swipe y mostrar los colores aceptados o rechazados con una animación.

### Estructura del Proyecto

1. **Interfaz de Usuario (activity_main.xml)**: Contiene un `ImageView` que muestra el color actual y un `TextView` para mostrar los resultados finales.
   
   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:padding="16dp">

       <ImageView
           android:id="@+id/imageView"
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           android:contentDescription="Color deslizable"
           android:scaleType="centerCrop" />

       <TextView
           android:id="@+id/messageTextView"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_centerInParent="true"
           android:textSize="18sp"
           android:textColor="#000000"
           android:text=""/>
   </RelativeLayout>
   
2. Lógica de Swipe (MainActivity.java): La actividad principal gestiona los gestos de swipe y cambia el color en respuesta a estos gestos. Los colores aceptados se almacenan en una lista y se muestran al final.

   1. Gestión de Gestos y Animaciones (MainActivity.java): La actividad principal contiene un GestureDetector para capturar los gestos y 
      animaciones para los movimientos de swipe.
      1. Inicialización de Colores y Detectores de Gestos:
         ```java
            gestureDetector = new GestureDetector(this, new SwipeGestureListener());
            imageView = findViewById(R.id.imageView);
            messageTextView = findViewById(R.id.messageTextView);
            
            // Configura el primer color de fondo
            imageView.setBackgroundColor(colors[currentIndex]);
            imageView.setOnTouchListener((v, event) -> {
                gestureDetector.onTouchEvent(event);
                return true;
            });
         
      2. Método para Aceptar Color: Aplica una animación de salida a la izquierda al aceptar el color actual y luego muestra el siguiente.
         ```java
         private void acceptColor() {
          ObjectAnimator animatorOut = ObjectAnimator.ofFloat(imageView, "translationX", -imageView.getWidth());
          animatorOut.setDuration(300);
          animatorOut.addListener(new android.animation.AnimatorListenerAdapter() {
              @Override
              public void onAnimationEnd(android.animation.Animator animation) {
                  acceptedColors.add(colors[currentIndex]);
                  moveToNextColor();
                  imageView.setTranslationX(imageView.getWidth());
                  ObjectAnimator animatorIn = ObjectAnimator.ofFloat(imageView, "translationX", 0);
                  animatorIn.setDuration(300);
                  animatorIn.start();
              }
          });
          animatorOut.start();
         }
      3.Mostrar Resultados Finales: Al llegar al último color, muestra los colores aceptados en el `TextView`.
         ```java
         private void showResults() {
          imageView.setVisibility(ImageView.GONE);
          StringBuilder result = new StringBuilder("No hay más ofertas.\nColores aceptados:\n");
          for (int color : acceptedColors) {
              result.append(String.format("#%06X", (0xFFFFFF & color))).append("\n");
          }
          messageTextView.setText(result.toString());
      }
## Explicación del Código

1. Gestión de Gestos: La clase `SwipeGestureListener` detecta la dirección del swipe.
2. Si el usuario desliza a la derecha, se rechaza el color; si desliza a la izquierda, se acepta.
3. Animaciones de Deslizar: Los métodos `acceptColor()` y `rejectColor()` aplican animaciones de deslizamiento para indicar la acción de aceptación o rechazo del color actual.
4. Mostrar Resultados: Al llegar al último color, `showResults()` oculta el `ImageView` y muestra los colores aceptados en el `TextView`.
Con esta estructura, el proyecto TestSwipe permite gestionar gestos de swipe de manera intuitiva y visual.


