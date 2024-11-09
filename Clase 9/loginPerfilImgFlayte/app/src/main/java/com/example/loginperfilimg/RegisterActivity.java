package com.example.loginperfilimg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginperfilimg.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText nameInput, emailInput, passwordInput;
    private RadioGroup roleGroup;
    private Button registerButton, selectImageButton;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference userDatabase;
    private StorageReference storageReference;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        roleGroup = findViewById(R.id.roleGroup);
        registerButton = findViewById(R.id.registerButton);
        selectImageButton = findViewById(R.id.selectImageButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference("users");

        // Configuración de storageReference para proyectoStorage
        FirebaseApp storageApp = FirebaseApp.getInstance("proyectoStorage");
        storageReference = FirebaseStorage.getInstance(storageApp).getReference("tilines");

        selectImageButton.setOnClickListener(view -> openFileChooser());
        registerButton.setOnClickListener(view -> registerUser());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Toast.makeText(this, "Imagen seleccionada", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        int selectedRoleId = roleGroup.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || selectedRoleId == -1 || imageUri == null) {
            Toast.makeText(RegisterActivity.this, "Por favor ingresa todos los campos y selecciona una imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        String role = ((RadioButton) findViewById(selectedRoleId)).getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    uploadImageToStorage(user.getUid(), name, email, role);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Error de registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImageToStorage(String userId, String name, String email, String role) {
        progressBar.setVisibility(View.VISIBLE);
        StorageReference fileReference = storageReference.child(userId + "/" + UUID.randomUUID().toString());
        fileReference.putFile(imageUri).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    saveUserToDatabase(userId, name, email, role, imageUrl);
                });
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegisterActivity.this, "Error al subir la imagen: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserToDatabase(String userId, String name, String email, String role, String imageUrl) {
        User user = new User(userId, name, email, role, imageUrl);
        userDatabase.child(userId).setValue(user).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Error al guardar datos: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
