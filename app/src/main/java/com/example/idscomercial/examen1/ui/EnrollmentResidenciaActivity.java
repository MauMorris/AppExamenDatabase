package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idscomercial.examen1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EnrollmentResidenciaActivity extends AppCompatActivity {
    private Context context = EnrollmentResidenciaActivity.this;

    private String celular;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_residencia);


        FloatingActionButton fab = findViewById(R.id.next_enrollment_usuario_contrasena_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnrollmentUsuarioContrasenaActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        celular = intent.getStringExtra(EnrollmentCodigoAutorizacionActivity.CELULAR_EXTRA);
        nombre = intent.getStringExtra(EnrollmentPerfilActivity.NOMBRE_EXTRA);
        primerApellido = intent.getStringExtra(EnrollmentPerfilActivity.PRIMER_APELLIDO_EXTRA);
        segundoApellido = intent.getStringExtra(EnrollmentPerfilActivity.SEGUNDO_APELLIDO_EXTRA);
        fechaNacimiento = intent.getStringExtra(EnrollmentPerfilActivity.FECHA_NACIMIENTO_EXTRA);

        Toast.makeText(context,
                celular + nombre + primerApellido + segundoApellido + fechaNacimiento,
                Toast.LENGTH_LONG)
                .show();
    }
}