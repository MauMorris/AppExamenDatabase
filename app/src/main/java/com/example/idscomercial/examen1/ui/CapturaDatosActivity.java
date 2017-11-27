package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.impl.CapturaDatosImpl;

public class CapturaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = CapturaDatosActivity.class.getSimpleName();

    private TextInputEditText nombre, apellidos, direccion;
    private TextInputEditText telefono, mail, fecha_nacimiento;
    private TextInputEditText edo_civil, usuario, contraseña;

    private TextInputLayout til_nombre, til_apellidos, til_direccion;
    private TextInputLayout til_telefono, til_mail, til_fecha_nacimiento;
    private TextInputLayout til_edo_civil, til_usuario, til_contraseña;

    private FloatingActionButton fab;

    private Context mContext;
    private CapturaDatosImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mContext = CapturaDatosActivity.this;
        mPresenter = new CapturaDatosImpl(mContext, new DatabaseHelper(mContext));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        til_nombre = findViewById(R.id.til_nombre);
        nombre = findViewById(R.id.et_nombre);

        til_apellidos = findViewById(R.id.til_apellido);
        apellidos = findViewById(R.id.et_apellido);

        til_direccion = findViewById(R.id.til_direccion);
        direccion = findViewById(R.id.et_direccion);

        til_telefono = findViewById(R.id.til_telefono);
        telefono = findViewById(R.id.et_telefono);

        til_mail = findViewById(R.id.til_mail);
        mail = findViewById(R.id.et_mail);

        til_fecha_nacimiento = findViewById(R.id.til_fecha_nac);
        fecha_nacimiento = findViewById(R.id.et_fecha_nac);

        til_edo_civil = findViewById(R.id.til_edo_civil);
        edo_civil = findViewById(R.id.et_edo_civil);

        til_usuario = findViewById(R.id.til_usuario);
        usuario = findViewById(R.id.et_usuario);

        til_contraseña = findViewById(R.id.til_contraseña);
        contraseña = findViewById(R.id.et_contraseña);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        boolean resultado = mPresenter.validaDatos(nombre, til_nombre, apellidos, til_apellidos,
                direccion, til_direccion, telefono, til_telefono, mail, til_mail,
                fecha_nacimiento, til_fecha_nacimiento, edo_civil, til_edo_civil, usuario, til_usuario,
                contraseña, til_contraseña);
        if(resultado)
        {
            mPresenter.insert(nombre.getText().toString(), apellidos.getText().toString(),
                    direccion.getText().toString(), telefono.getText().toString(), mail.getText().toString(),
                    fecha_nacimiento.getText().toString(), edo_civil.getText().toString(), usuario.getText().toString(),
                    contraseña.getText().toString());
            Log.d(LOG_TAG, " db: regresa a la activity en la pila y elimina " + LOG_TAG);
            finish();
        }else{
            mPresenter.getSnackbar(view, "Agrega los datos correctamente");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, " db: presiona back: regresa a la activity en la pila y elimina " + LOG_TAG);
    }
}