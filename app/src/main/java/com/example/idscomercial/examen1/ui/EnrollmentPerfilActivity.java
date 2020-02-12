package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idscomercial.examen1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EnrollmentPerfilActivity extends AppCompatActivity {

    Context context = EnrollmentPerfilActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_perfil);

        FloatingActionButton fab = findViewById(R.id.next_enrollment_residencia_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnrollmentResidenciaActivity.class);
                startActivity(intent);
            }
        });
    }
}