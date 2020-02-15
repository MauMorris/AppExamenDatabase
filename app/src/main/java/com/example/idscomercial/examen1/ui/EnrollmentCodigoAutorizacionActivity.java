package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityEnrollmentCodigoAutorizacionBinding;
import com.example.idscomercial.examen1.device.notifications.NotificationReceiver;
import com.example.idscomercial.examen1.vm.EnrollmentCodigoAutorizacionViewModel;

public class EnrollmentCodigoAutorizacionActivity extends AppCompatActivity {

    private Context context = EnrollmentCodigoAutorizacionActivity.this;
    private ActivityEnrollmentCodigoAutorizacionBinding mBinding;
    private Handler mShowHandler;
    private EnrollmentCodigoAutorizacionViewModel mViewModel;
    private NotificationReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_codigo_autorizacion);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_enrollment_codigo_autorizacion);
        mShowHandler = new Handler();
        mViewModel = new ViewModelProvider(this).get(EnrollmentCodigoAutorizacionViewModel.class);

        setViews();
        setClickListeners(mBinding);
        subscribeUI(mViewModel);

        mViewModel.requestValidCode(context);
    }

    private void subscribeUI(EnrollmentCodigoAutorizacionViewModel mViewModel) {
        mViewModel.getWebLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.INVISIBLE);
                mBinding.nextEnrollmentPerfilFab.setEnabled(true);
                mBinding.codigoAutorizacionEt.getText().clear();

                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, EnrollmentPerfilActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.CUSTOM_INTENT");
        receiver = new NotificationReceiver(receiverValue -> {
            mBinding.codigoAutorizacionEt.setText(receiverValue);
            Toast.makeText(context, "notification released", Toast.LENGTH_SHORT).show();
        });

        registerReceiver(receiver, filter);
    }

    private void setClickListeners(ActivityEnrollmentCodigoAutorizacionBinding mBinding) {
        mBinding.nextEnrollmentPerfilFab.setOnClickListener(view -> {

            String codigo = mBinding.codigoAutorizacionEt.getText().toString();
            boolean validacion = mViewModel.validaNumero(codigo);

            if(validacion){
                mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.VISIBLE);
                mBinding.nextEnrollmentPerfilFab.setEnabled(false);

                mShowHandler.removeCallbacks(mShowRunnable);
                mViewModel.getDataFromInternet("test", "salary", "age");

            } else{
                mBinding.codigoAutorizacionTil.setError(getString(R.string.no_informacion_error_text));

            }
        });

        mBinding.preguntaCelularRecepcionCodigoAutorizacionTv.setOnClickListener(view -> onBackPressed());

        mBinding.enviarNuevamenteCodigoAutorizacionTv.setOnClickListener(view -> {
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.VISIBLE);

            mShowHandler.removeCallbacks(mShowRunnable);
            mShowHandler.postDelayed(mShowRunnable, getResources().getInteger(R.integer.service_request_time));
        });

        mBinding.codigoAutorizacionEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mBinding.codigoAutorizacionTil.getError() != null) {
                    mBinding.codigoAutorizacionTil.setError(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void setViews() {
        mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();

        if(intent.hasExtra(EnrollmentValidacionActivity.NUMERO_CELULAR_EXTRA)){
            String data = intent.getStringExtra(EnrollmentValidacionActivity.NUMERO_CELULAR_EXTRA);
            String set = data.substring(0,2) + " " + data.substring(2,6) + " " + data.substring(6);

            mBinding.celularRecepcionCodigoAutorizacionTv.setText(set);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mShowHandler.removeCallbacks(mShowRunnable);

        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mShowHandler.removeCallbacks(mShowRunnable);
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.INVISIBLE);
            mViewModel.requestValidCode(context);
        }
    };
}