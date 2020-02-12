package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityCapturaDatosBinding;

import com.example.idscomercial.examen1.vm.CapturaDatosViewModel;

public class CapturaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = CapturaDatosActivity.class.getSimpleName();

    private Context mContext = CapturaDatosActivity.this;
    private CapturaDatosViewModel mViewModel;
    int count = 0;

    private ActivityCapturaDatosBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mViewModel = new ViewModelProvider(this).get(CapturaDatosViewModel.class);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_captura_datos);

        mDataBinding.siguientePantallaFloatingActionButton.setOnClickListener(this);
        mDataBinding.fechaNacimientoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int inputLength = mDataBinding.fechaNacimientoEditText.getText().toString().length();
                String slashChar = "/";

                if (count < inputLength && (inputLength == 2 || inputLength == 5)){
                    mDataBinding.fechaNacimientoEditText.setText(mDataBinding.fechaNacimientoEditText.getText() + slashChar);
                    mDataBinding.fechaNacimientoEditText.setSelection(mDataBinding.fechaNacimientoEditText.getText().toString().length());

                } else if (count > inputLength && (inputLength == 3 || inputLength == 6)){
                    mDataBinding.fechaNacimientoEditText.setText(mDataBinding.fechaNacimientoEditText.getText().toString()
                            .substring(0, mDataBinding.fechaNacimientoEditText.getText().toString().length()-2));

                    mDataBinding.fechaNacimientoEditText.setSelection(mDataBinding.fechaNacimientoEditText.getText().toString().length());
                }else if (count > inputLength && (inputLength == 2 || inputLength == 5)){
                    mDataBinding.fechaNacimientoEditText.setText(mDataBinding.fechaNacimientoEditText.getText().toString()
                            .substring(0, mDataBinding.fechaNacimientoEditText.getText().toString().length()-1));

                    mDataBinding.fechaNacimientoEditText.setSelection(mDataBinding.fechaNacimientoEditText.getText().toString().length());
                }
                count = mDataBinding.fechaNacimientoEditText.getText().toString().length();
            }
        });
    }

    @Override
    public void onClick(View view) {

        boolean resultado = mViewModel.validaDatos(
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

            mViewModel.insert(
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