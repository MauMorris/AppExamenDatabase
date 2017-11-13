package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.impl.LeeCapturaImpl;

public class LeeCapturaActivity extends AppCompatActivity implements View.OnClickListener {
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = LeeCapturaActivity.class.getSimpleName();

    private AppCompatButton lee;
    private AppCompatButton captura;
    //presenter nos ayuda a que la lectura de código sea mejor
    private LeeCapturaImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_captura);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mPresenter = new LeeCapturaImpl(LeeCapturaActivity.this, new DatabaseHelper(LeeCapturaActivity.this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lee = findViewById(R.id.btn_lee);
        captura = findViewById(R.id.btn_captura);

        lee.setOnClickListener(this);
        captura.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lee:
                Log.d(LOG_TAG, " db: clic en leer datos de la db local");
                if(mPresenter.read())
                    mPresenter.newActivity(LeeDatosActivity.class);
                break;

            case R.id.btn_captura:
                Log.d(LOG_TAG, " db: clic en captura datos en la db local");
                mPresenter.newActivity(CapturaDatosActivity.class);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, " db: presiona back: elimina " + LOG_TAG);
    }
}