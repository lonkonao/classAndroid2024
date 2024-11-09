package com.example.loginperfilimg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import com.example.loginperfilimg.activities.ApplicationActivity;
import com.example.loginperfilimg.activities.JobPostActivity;
import com.example.loginperfilimg.adapters.JobAdapter;
import com.example.loginperfilimg.models.Job;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameText, emailText, welcomeText;
    private ImageView profileImage;
    private Button actionButton,logoutButton;
    private ProgressBar progressBar;
    private RecyclerView jobRecyclerView;
    private JobAdapter jobAdapter;
    private List<Job> jobList;
    private String userRole;
    private DatabaseReference userDatabase, jobsDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Vinculando vistas con sus IDs
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        welcomeText = findViewById(R.id.welcomeText);
        profileImage = findViewById(R.id.profileImage);
        actionButton = findViewById(R.id.actionButton);
        jobRecyclerView = findViewById(R.id.jobRecyclerView);

        auth = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference("users");
        jobsDatabase = FirebaseDatabase.getInstance().getReference("jobs");

        jobList = new ArrayList<>();
        jobAdapter = new JobAdapter(this, jobList, userRole);
        jobRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobRecyclerView.setAdapter(jobAdapter);

        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            auth.signOut();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Cierra la ProfileActivity para evitar volver con el botón de retroceso
        });

        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = auth.getCurrentUser().getUid();
        userDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                String email = snapshot.child("email").getValue(String.class);
                userRole = snapshot.child("role").getValue(String.class);
                String imageUrl = snapshot.child("profileImageUrl").getValue(String.class);

                nameText.setText(name);
                emailText.setText(email);
                welcomeText.setText("Bienvenido, " + name + " (" + userRole + ")");

                if (imageUrl != null) {
                    Picasso.get().load(imageUrl).into(profileImage);
                }

                // Configurar el adaptador de trabajos una vez que el rol esté disponible
                jobAdapter = new JobAdapter(ProfileActivity.this, jobList, userRole);
                jobRecyclerView.setAdapter(jobAdapter);

                if ("Empresa".equals(userRole)) {
                    actionButton.setText("Crear Empleo");
                    actionButton.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, JobPostActivity.class)));
                    jobRecyclerView.setVisibility(View.VISIBLE);
                    listenForJobUpdates();
                } else {
                    actionButton.setText("Ver Postulaciones");
                    actionButton.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, ApplicationActivity.class)));
                    jobRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error al cargar perfil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listenForJobUpdates() {
        String companyEmail = auth.getCurrentUser().getEmail();
        jobsDatabase.orderByChild("companyEmail").equalTo(companyEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        jobList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Job job = snapshot.getValue(Job.class);
                            jobList.add(job);
                        }
                        jobAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ProfileActivity.this, "Error al cargar empleos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
