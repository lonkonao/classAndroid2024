package com.example.testswipe;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private ImageView imageView;
    private TextView messageTextView;
    private int[] colors = {
            Color.parseColor("#FF5733"), // Color 1
            Color.parseColor("#33FF57"), // Color 2
            Color.parseColor("#3357FF"), // Color 3
            Color.parseColor("#FF33A6"), // Color 4
            Color.parseColor("#FFA833")  // Color 5
    };
    private int currentIndex = 0;
    private List<Integer> acceptedColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, new SwipeGestureListener());
        imageView = findViewById(R.id.imageView);
        messageTextView = findViewById(R.id.messageTextView);

        // Establece el primer color de fondo
        imageView.setBackgroundColor(colors[currentIndex]);

        // Configura el listener de touch para detectar los gestos de swipe
        imageView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });
    }

    // Clase interna para manejar los gestos de swipe
    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(e2.getY() - e1.getY())) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        rejectColor();
                    } else {
                        acceptColor();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    // Método para aceptar el color actual con animación de deslizamiento
    private void acceptColor() {
        // Animación de salida hacia la izquierda
        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(imageView, "translationX", -imageView.getWidth());
        animatorOut.setDuration(300); // Duración de la animación

        // Evento cuando termina la animación de salida
        animatorOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                // Añade el color aceptado a la lista
                acceptedColors.add(colors[currentIndex]);
                Log.d("Swipe", "Color aceptado");
                Toast.makeText(MainActivity.this, "Color aceptado", Toast.LENGTH_SHORT).show();

                // Cambia al siguiente color o muestra los resultados
                moveToNextColor();

                // Regresa la vista al centro antes de animar el regreso
                imageView.setTranslationX(imageView.getWidth());

                // Animación de regreso a la posición central con el nuevo color
                ObjectAnimator animatorIn = ObjectAnimator.ofFloat(imageView, "translationX", 0);
                animatorIn.setDuration(300);
                animatorIn.start();
            }
        });
        animatorOut.start();
    }

    // Método para rechazar el color actual con animación de deslizamiento
    private void rejectColor() {
        // Animación de salida hacia la derecha
        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(imageView, "translationX", imageView.getWidth());
        animatorOut.setDuration(300); // Duración de la animación

        // Evento cuando termina la animación de salida
        animatorOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                Log.d("Swipe", "Color rechazado");
                Toast.makeText(MainActivity.this, "Color rechazado", Toast.LENGTH_SHORT).show();

                // Cambia al siguiente color o muestra los resultados
                moveToNextColor();

                // Regresa la vista al centro antes de animar el regreso
                imageView.setTranslationX(-imageView.getWidth());

                // Animación de regreso a la posición central con el nuevo color
                ObjectAnimator animatorIn = ObjectAnimator.ofFloat(imageView, "translationX", 0);
                animatorIn.setDuration(300);
                animatorIn.start();
            }
        });
        animatorOut.start();
    }

    // Método para avanzar al siguiente color o mostrar resultados si no hay más colores
    private void moveToNextColor() {
        if (currentIndex < colors.length - 1) {
            currentIndex++;
            imageView.setBackgroundColor(colors[currentIndex]);
        } else {
            showResults();
        }
    }

    // Método para mostrar los resultados finales en el TextView
    private void showResults() {
        imageView.setVisibility(ImageView.GONE); // Oculta el ImageView

        // Construye el mensaje de resultados
        StringBuilder result = new StringBuilder("No hay más ofertas.\nColores aceptados:\n");
        for (int color : acceptedColors) {
            result.append(String.format("#%06X", (0xFFFFFF & color))).append("\n");
        }

        // Muestra el mensaje en el TextView
        messageTextView.setText(result.toString());
        Log.d("Resultados", result.toString());
    }
}
