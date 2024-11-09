package com.example.loginperfilimg;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, registerButton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference("users");

        loginButton.setOnClickListener(view -> loginUser());
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Por favor ingresa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                checkUserRole();
            } else {
                Toast.makeText(LoginActivity.this, "Error de autenticación: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkUserRole() {
        String userId = auth.getCurrentUser().getUid();
        userDatabase.child(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    String role = snapshot.child("role").getValue(String.class);
                    if ("empresa".equals(role)) {
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class).putExtra("role", "empresa"));
                    } else {
                        startActivity(new Intent(LoginActivity.this, ProfileActivity.class).putExtra("role", "trabajador"));
                    }
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
