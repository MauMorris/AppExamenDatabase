package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityCapturaDatosBinding;

import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.vm.CapturaDatosImpl;

public class CapturaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = CapturaDatosActivity.class.getSimpleName();

    private Context mContext;
    private CapturaDatosImpl mPresenter;

    private ActivityCapturaDatosBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mContext = CapturaDatosActivity.this;
        mPresenter = new CapturaDatosImpl(mContext, new DatabaseHelper(mContext));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_captura_datos);

        mDataBinding.siguientePantallaFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        boolean resultado = mPresenter.validaDatos(
                mDataBinding.nombreEditText, mDataBinding.nombreTextInputLayout,
                mDataBinding.apellidoEditText, mDataBinding.apellidoTextInputLayout,
                mDataBinding.direccionEditText, mDataBinding.direccionTextInputLayout,
                mDataBinding.telefonoEditText, mDataBinding.telefonoTextInputLayout,
                mDataBinding.mailEditText, mDataBinding.mailTextInputLayout,
                mDataBinding.fechaNacimientoEditText, mDataBinding.fechaNacimientoTextInputLayout,
                mDataBinding.estadoCivilEditText, mDataBinding.estadoCivilTextInputLayout,
                mDataBinding.usuarioEditText, mDataBinding.usuarioTextInputLayout,
                mDataBinding.contraseniaEditText, mDataBinding.contraseniaTextInputLayout);

        if(resultado) {
            String nombre = mDataBinding.nombreEditText.getText().toString();
            String apellidos = mDataBinding.apellidoEditText.getText().toString();
            String direccion = mDataBinding.direccionEditText.getText().toString();
            String telefono = mDataBinding.telefonoEditText.getText().toString();
            String mail = mDataBinding.mailEditText.getText().toString();
            String fecha_nacimiento = mDataBinding.fechaNacimientoEditText.getText().toString();
            String edo_civil = mDataBinding.estadoCivilEditText.getText().toString();
            String usuario = mDataBinding.usuarioEditText.getText().toString();
            String contrasenia = mDataBinding.contraseniaEditText.getText().toString();

            mPresenter.insert(
                    nombre, apellidos, direccion,
                    telefono, mail, fecha_nacimiento,
                    edo_civil, usuario, contrasenia);

            Log.d(LOG_TAG, " db: regresa a la activity en la pila y elimina " + LOG_TAG);
            finish();
        }else{
            getSnackbar(view, "Agrega los datos correctamente");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, " db: presiona back: regresa a la activity en la pila y elimina " + LOG_TAG);
    }

    public void getSnackbar(View view, String mensaje) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}