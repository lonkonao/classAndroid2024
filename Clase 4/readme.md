# Resumen de la Clase de Android: Navegación y Transferencia de Datos

## Objetivo de la Clase

Aprender a navegar entre vistas y a transferir datos entre ellas en Android Studio.

## Temas Cubiertos

1. **Creación y Uso de Intents**
2. **Manejo de Listas y Adaptadores**
3. **Uso de `EditText` y `Button`**

## Fragmentos de Código Clave

### Iniciando `ListActivity` desde `MainActivity`

```java
// MainActivity.java
Intent intent = new Intent(MainActivity.this, ListActivity.class);
intent.putStringArrayListExtra("listaTextos", listaTextos);
startActivity(intent);
```

### Mostrando Datos en `ListActivity`

```java
// ListActivity.java
ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTextos);
listView.setAdapter(adapter);
```

## Conclusiones

- **Intents** son esenciales para la navegación entre actividades.
- **ArrayAdapter** se utiliza para conectar un `ArrayList` de datos con un `ListView`.

# Tarea: Calculadora con Historial

### Descripción

En esta tarea, vamos a continuar desarrollando la **Calculadora Básica** que vimos en las clases 2 y 3. El objetivo principal es modificar el **diseño** de la calculadora, agregar un **historial** de resultados, y reforzar los conceptos que vimos previamente.

### Requisitos

1. **Eliminación de decimales innecesarios:**
   - El resultado debe eliminar el decimal si no es necesario. Por ejemplo, al sumar 2 + 2, el resultado debe ser 4 en lugar de 4.0.
2. **Operaciones continuas:**

   - La calculadora debe permitir realizar operaciones con múltiples valores de forma continua. Ejemplo: `2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 + 2 = 20`.

3. **Nuevo diseño:**

   - Debes modificar el diseño de la calculadora para que no sea igual al actual.
   - (AQUÍ INSERTARÉ IMAGEN DEL DISEÑO ACTUAL).
   - Puedes cambiar tanto el diseño como la lógica interna de la calculadora, pero si decides cambiar la lógica, asegúrate de mantener las funcionalidades vistas en clases anteriores.

4. **Historial de resultados:**
   - La calculadora debe contar con un botón que permita ver un historial de resultados.
   - Cada vez que se obtenga un resultado, este debe aparecer en un **nuevo Activity** que mostrará todos los resultados previos.

### Entrega

- La tarea debe ser entregada a más tardar el **miércoles 11 de septiembre a las 23:59**.
- El **jueves 12 de septiembre** se publicará un video con la solución.
- Esta tarea forma parte de la **segunda evaluación** de la unidad.
- Los **primeros 5 estudiantes** que entreguen la tarea tendrán un bono de **0,5 décimas** para la evaluación del **viernes 13 de septiembre**, siempre y cuando el trabajo se pueda ejecutar correctamente y cumpla con todos los requisitos solicitados.

### Instrucciones de envío

1. Subir el proyecto a **GitHub**.
2. Crear una **Issue** en el repositorio del curso.
3. En la Issue, incluir tu nombre completo y el enlace a tu repositorio de GitHub con el proyecto.

### Consideraciones adicionales

- Los trabajos serán evaluados y tendrán retroalimentación.
- Recuerda que no solo es necesario ser uno de los primeros en enviar la tarea, sino también que esta funcione y cumpla con lo solicitado para optar por las décimas adicionales.
