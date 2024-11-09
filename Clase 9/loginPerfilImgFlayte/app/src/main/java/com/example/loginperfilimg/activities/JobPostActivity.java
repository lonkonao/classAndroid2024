package com.example.loginperfilimg.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginperfilimg.R;
import com.example.loginperfilimg.models.Job;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.UUID;

public class JobPostActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput, salaryInput, vacanciesInput, expirationDateInput;
    private RadioGroup modeGroup;
    private Button postJobButton;
    private ProgressBar progressBar;
    private DatabaseReference jobsDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        salaryInput = findViewById(R.id.salaryInput);
        vacanciesInput = findViewById(R.id.vacanciesInput);
        expirationDateInput = findViewById(R.id.expirationDateInput);
        modeGroup = findViewById(R.id.modeGroup);
        postJobButton = findViewById(R.id.postJobButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        jobsDatabase = FirebaseDatabase.getInstance().getReference("jobs");

        expirationDateInput.setOnClickListener(view -> showDatePickerDialog());
        postJobButton.setOnClickListener(view -> postJob());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Formato: dd/MM/yyyy
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    expirationDateInput.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void postJob() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();
        String salary = salaryInput.getText().toString().trim();
        String vacancies = vacanciesInput.getText().toString().trim();
        String expirationDate = expirationDateInput.getText().toString().trim();
        int selectedModeId = modeGroup.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(salary) ||
                TextUtils.isEmpty(vacancies) || TextUtils.isEmpty(expirationDate) || selectedModeId == -1) {
            Toast.makeText(JobPostActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el texto del modo seleccionado
        RadioButton selectedModeButton = findViewById(selectedModeId);
        String mode = selectedModeButton.getText().toString();

        String jobId = UUID.randomUUID().toString();
        String companyEmail = auth.getCurrentUser().getEmail();

        progressBar.setVisibility(View.VISIBLE);

        Job job = new Job(jobId, companyEmail, title, description, expirationDate, Integer.parseInt(vacancies), mode, salary);
        jobsDatabase.child(jobId).setValue(job).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                Toast.makeText(JobPostActivity.this, "Empleo publicado con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Regresar a la pantalla anterior
            } else {
                Toast.makeText(JobPostActivity.this, "Error al publicar el empleo: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
