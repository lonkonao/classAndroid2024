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
