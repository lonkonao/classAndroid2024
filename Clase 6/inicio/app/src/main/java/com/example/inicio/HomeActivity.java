package com.example.inicio;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView welcomeMessage;
    private Button logoutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Referencias a los elementos de la interfaz
        welcomeMessage = findViewById(R.id.welcome_message);
        logoutButton = findViewById(R.id.logout_button);

        // Puedes obtener el usuario actual si lo necesitas
        String userEmail = mAuth.getCurrentUser().getEmail();
        welcomeMessage.setText("Bienvenido, " + userEmail + "!");

        // Botón de cierre de sesión
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar sesión y regresar a la pantalla de login
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza HomeActivity para que no quede en el historial
            }
        });
    }
}