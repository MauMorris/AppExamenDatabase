package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityEnrollmentCodigoAutorizacionBinding;
import com.example.idscomercial.examen1.device.notifications.NotificationReceiver;
import com.example.idscomercial.examen1.vm.EnrollmentCodigoAutorizacionViewModel;

public class EnrollmentCodigoAutorizacion extends AppCompatActivity {

    private Context context = EnrollmentCodigoAutorizacion.this;
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

        mViewModel.requestValidCode(context);
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
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.VISIBLE);

            mShowHandler.removeCallbacks(mNextRunnable);
            mShowHandler.removeCallbacks(mShowRunnable);
            mShowHandler.postDelayed(mNextRunnable, getResources().getInteger(R.integer.service_request_time));

        });

        mBinding.preguntaCelularRecepcionCodigoAutorizacionTv.setOnClickListener(view -> onBackPressed());

        mBinding.enviarNuevamenteCodigoAutorizacionTv.setOnClickListener(view -> {
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.VISIBLE);

            mShowHandler.removeCallbacks(mShowRunnable);
            mShowHandler.postDelayed(mShowRunnable, getResources().getInteger(R.integer.service_request_time));
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
        mShowHandler.removeCallbacks(mNextRunnable);

        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mShowHandler.removeCallbacks(mShowRunnable);
        mShowHandler.removeCallbacks(mNextRunnable);
    }

    private final Runnable mShowRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.INVISIBLE);
            mViewModel.requestValidCode(context);
        }
    };

    private final Runnable mNextRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.enrollmentCodigoAutorizacionPb.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(context, EnrollmentPerfilActivity.class);
            startActivity(intent);
        }
    };
}