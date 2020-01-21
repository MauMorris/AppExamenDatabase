package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.databinding.ActivityLeeCapturaBinding;
import com.example.idscomercial.examen1.repository.datareturnutils.RetornoDatosConsultaDB;
import com.example.idscomercial.examen1.ui.modalutils.ModalDialogs;
import com.example.idscomercial.examen1.vm.LeeCapturaImpl;

public class LeeCapturaActivity extends AppCompatActivity implements View.OnClickListener {
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = LeeCapturaActivity.class.getSimpleName();

    private ActivityLeeCapturaBinding mDataBinding;
    private LeeCapturaImpl mPresenter;
    private Context mContext = LeeCapturaActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_captura);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);
        mPresenter = new LeeCapturaImpl(LeeCapturaActivity.this, new DatabaseHelper(LeeCapturaActivity.this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lee_captura);

        setListeners(mDataBinding);
    }

    private void setListeners(ActivityLeeCapturaBinding mDataBinding) {
        mDataBinding.leeDataButton.setOnClickListener(this);
        mDataBinding.capturaDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.lee_data_button:
                Log.d(LOG_TAG, " db: clic en leer datos de la db local");

                int estatus = mPresenter.getDataFromDB();

                if(estatus == RetornoDatosConsultaDB.NO_EXISTE_DB)
                    Toast.makeText(mContext, "No existe la base de datos", Toast.LENGTH_LONG).show();
                else if (estatus == RetornoDatosConsultaDB.NO_HAY_DATA)
                    ModalDialogs.showMessage(mContext,":(", "No hay datos que mostrar");
                else if (estatus == RetornoDatosConsultaDB.OPERACION_REALIZADA)
                    setNewActivity(LeeDatosActivity.class);
                break;

            case R.id.captura_data_button:
                Log.d(LOG_TAG, " db: clic en captura datos en la db local");

                setNewActivity(CapturaDatosActivity.class);
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

    public void setNewActivity(Class<?> cls) {
        Intent next = new Intent(mContext, cls);

        mContext.startActivity(next);
        Log.d(LOG_TAG, " db: inicia activity " + cls.getSimpleName());
    }
}