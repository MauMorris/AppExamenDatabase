package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityEnrollmentPerfilBinding;
import com.example.idscomercial.examen1.vm.EnrollmentPerfilViewModel;
import com.example.idscomercial.examen1.vm.validateutils.ValidateDataUtils;
import com.google.android.material.textfield.TextInputLayout;

public class EnrollmentPerfilActivity extends AppCompatActivity {

    public static final String PERFIL_EXTRA = "perfil_nombre";
    Context context = EnrollmentPerfilActivity.this;

    private ActivityEnrollmentPerfilBinding mBinding;
    private EnrollmentPerfilViewModel mViewModel;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_perfil);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_enrollment_perfil);
        mViewModel = new ViewModelProvider(this).get(EnrollmentPerfilViewModel.class);

        setListeners(mBinding);
    }

    private void setListeners(ActivityEnrollmentPerfilBinding mBinding) {
        mBinding.nextEnrollmentResidenciaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreFromET = mBinding.nombreCompletoEt.getText().toString();
                String primerApellidoFromET = mBinding.primerApellidoEt.getText().toString();
                String segundoApellidoFromET = mBinding.segundoApellidoEt.getText().toString();
                String fechaFromET = mBinding.fechaNacimientoEt.getText().toString();

                int validaNombre = mViewModel.validaTexto(nombreFromET);
                int validaPrimerApellido = mViewModel.validaTexto(primerApellidoFromET);
                int validaSegundoApellido = mViewModel.validaTexto(segundoApellidoFromET);
                int validaFecha = mViewModel.validaFecha(fechaFromET);

                boolean validaNombreFlag = false;
                boolean validaPrimerApellidoFlag = false;
                boolean validaSegundoApellidoFlag = false;
                boolean validaFechaFlag = false;

                int[] validaData = {
                        validaNombre,
                        validaPrimerApellido,
                        validaSegundoApellido,
                        validaFecha};

                boolean[] validaFlag = {
                        validaNombreFlag,
                        validaPrimerApellidoFlag,
                        validaSegundoApellidoFlag,
                        validaFechaFlag};

                TextInputLayout[] validaTil = {
                        mBinding.nombreCompletoTil,
                        mBinding.primerApellidoTil,
                        mBinding.segundoApellidoTil,
                        mBinding.fechaNacimientoTil};

                for (int i = 0; i < validaData.length; i++) {
                    switch (validaData[i]) {
                        case ValidateDataUtils.NO_DATA:
                            validaFlag[i] = false;

                            validaTil[i].setError(null);
                            validaTil[i].setError(getString(R.string.no_informacion_error_text));

                            break;
                        case ValidateDataUtils.WRONG_DATA:
                            validaFlag[i] = false;

                            validaTil[i].setError(null);
                            validaTil[i].setError(getString(R.string.wrong_text));

                            break;
                        case ValidateDataUtils.WRONG_DATE:
                            validaFlag[i] = false;

                            validaTil[i].setError(null);
                            validaTil[i].setError(getString(R.string.wrong_date));

                            break;
                        case ValidateDataUtils.DATA_OK:
                            validaFlag[i] = true;
                            validaTil[i].setError(null);

                            break;
                    }
                }

                if(validaFlag[0] & validaFlag[1] & validaFlag[2] & validaFlag[3]){
                    Intent intent = new Intent(context, EnrollmentResidenciaActivity.class);
                    startActivity(intent);
                }
            }
        });

        mBinding.nombreCompletoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mBinding.nombreCompletoTil.getError() != null){
                    mBinding.nombreCompletoTil.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mBinding.primerApellidoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mBinding.primerApellidoTil.getError() != null){
                    mBinding.primerApellidoTil.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mBinding.segundoApellidoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mBinding.segundoApellidoTil.getError() != null){
                    mBinding.segundoApellidoTil.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mBinding.fechaNacimientoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mBinding.fechaNacimientoTil.getError() != null){
                    mBinding.fechaNacimientoTil.setError(null);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                int inputLength = mBinding.fechaNacimientoEt.getText().toString().length();
                String spaceChar = "/";

                if (count < inputLength && (inputLength == 2 || inputLength == 5)){
                    mBinding.fechaNacimientoEt.setText(mBinding.fechaNacimientoEt.getText() + spaceChar);
                    mBinding.fechaNacimientoEt.setSelection(mBinding.fechaNacimientoEt.getText().toString().length());

                } else if (count > inputLength && (inputLength == 3 || inputLength == 6)){
                    mBinding.fechaNacimientoEt.setText(mBinding.fechaNacimientoEt.getText().toString()
                            .substring(0, mBinding.fechaNacimientoEt.getText().toString().length()-2));

                    mBinding.fechaNacimientoEt.setSelection(mBinding.fechaNacimientoEt.getText().toString().length());
                }else if (count > inputLength && (inputLength == 2 || inputLength == 5)){
                    mBinding.fechaNacimientoEt.setText(mBinding.fechaNacimientoEt.getText().toString()
                            .substring(0, mBinding.fechaNacimientoEt.getText().toString().length()-1));

                    mBinding.fechaNacimientoEt.setSelection(mBinding.fechaNacimientoEt.getText().toString().length());
                }

                count = mBinding.fechaNacimientoEt.getText().toString().length();
            }
        });

    }
}