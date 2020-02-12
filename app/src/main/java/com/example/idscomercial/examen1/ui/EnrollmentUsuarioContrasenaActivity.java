package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idscomercial.examen1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EnrollmentUsuarioContrasenaActivity extends AppCompatActivity {
    Context context = EnrollmentUsuarioContrasenaActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_usuario_contrasena);

        FloatingActionButton fab = findViewById(R.id.enrollment_terminacion_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnrollmentTerminacionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
