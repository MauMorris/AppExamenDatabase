package com.example.idscomercial.examen1.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityEnrollmentValidacionBinding;
import com.example.idscomercial.examen1.vm.EnrollmentValidacionViewModel;
import com.example.idscomercial.examen1.vm.validateutils.ValidateDataUtils;

public class EnrollmentValidacionActivity extends AppCompatActivity {

    public static final String NUMERO_CELULAR_EXTRA = "numero_celular";

    private Context context =  EnrollmentValidacionActivity.this;
    private Activity activity = EnrollmentValidacionActivity.this;

    private ActivityEnrollmentValidacionBinding mBinding;
    private EnrollmentValidacionViewModel mViewModel;
    private Handler mShowHandler;

    private String numeroTelefonico;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_validacion);

        mBinding = DataBindingUtil.setContentView(activity, R.layout.activity_enrollment_validacion);
        mViewModel = new ViewModelProvider(this).get(EnrollmentValidacionViewModel.class);

        mShowHandler = new Handler();

        setViews(mBinding);
        setListeners(mBinding);
    }

    private void setViews(ActivityEnrollmentValidacionBinding mBinding) {
        mBinding.enrollmentValidacionPb.setVisibility(View.INVISIBLE);
    }

    private void setListeners(ActivityEnrollmentValidacionBinding mBinding) {
        mBinding.nextEnrollmentValidacionFab.setOnClickListener(view -> {

            String numeroFromET = mBinding.numeroTelefonicoEt.getText().toString();
            int validacion = mViewModel.validaNumero(numeroFromET);

            switch (validacion) {
                case ValidateDataUtils.NO_DATA:
                    mBinding.numeroTelefonicoTil.setError(null);
                    mBinding.numeroTelefonicoTil.setError(getString(R.string.no_informacion_error_text));

                    break;
                case ValidateDataUtils.WRONG_DATA:
                    mBinding.numeroTelefonicoTil.setError(null);
                    mBinding.numeroTelefonicoTil.setError(getString(R.string.formato_incorrecto_error_text));

                    break;
                case ValidateDataUtils.DATA_OK:
                    numeroTelefonico = mViewModel.returnFormattedNumber(numeroFromET);

                    mBinding.enrollmentValidacionPb.setVisibility(View.VISIBLE);

                    mShowHandler.removeCallbacks(mShowRunnable);
                    mShowHandler.postDelayed(mShowRunnable, getResources().getInteger(R.integer.service_request_time));

                    break;
            }
        });

        mBinding.numeroTelefonicoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mBinding.numeroTelefonicoTil.getError() != null){
                    mBinding.numeroTelefonicoTil.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int inputLength = mBinding.numeroTelefonicoEt.getText().toString().length();
                String spaceChar = " ";

                if (count < inputLength && (inputLength == 2 || inputLength == 7)){
                    mBinding.numeroTelefonicoEt.setText(mBinding.numeroTelefonicoEt.getText() + spaceChar);
                    mBinding.numeroTelefonicoEt.setSelection(mBinding.numeroTelefonicoEt.getText().toString().length());

                } else if (count > inputLength && (inputLength == 3 || inputLength == 8)){
                    mBinding.numeroTelefonicoEt.setText(mBinding.numeroTelefonicoEt.getText().toString()
                            .substring(0, mBinding.numeroTelefonicoEt.getText().toString().length()-2));

                    mBinding.numeroTelefonicoEt.setSelection(mBinding.numeroTelefonicoEt.getText().toString().length());
                }else if (count > inputLength && (inputLength == 2 || inputLength == 7)){
                    mBinding.numeroTelefonicoEt.setText(mBinding.numeroTelefonicoEt.getText().toString()
                            .substring(0, mBinding.numeroTelefonicoEt.getText().toString().length()-1));

                    mBinding.numeroTelefonicoEt.setSelection(mBinding.numeroTelefonicoEt.getText().toString().length());
                }

                count = mBinding.numeroTelefonicoEt.getText().toString().length();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mShowHandler.removeCallbacks(mShowRunnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mShowHandler.removeCallbacks(mShowRunnable);
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.enrollmentValidacionPb.setVisibility(View.INVISIBLE);
            mBinding.numeroTelefonicoEt.getText().clear();

            Intent intent = new Intent(context, EnrollmentCodigoAutorizacionActivity.class);
            intent.putExtra(NUMERO_CELULAR_EXTRA, numeroTelefonico);

            startActivity(intent);
        }
    };
}