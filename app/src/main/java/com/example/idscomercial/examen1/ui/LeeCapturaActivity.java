package com.example.idscomercial.examen1.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityLeeCapturaBinding;
import com.example.idscomercial.examen1.vm.datareturnutils.DatosConsultaHolder;
import com.example.idscomercial.examen1.ui.modalutils.ModalDialogs;
import com.example.idscomercial.examen1.vm.LeeCapturaViewModel;

import java.util.Objects;

public class LeeCapturaActivity extends AppCompatActivity implements View.OnClickListener {
    //Cada LOG_TAG que aparezca en el codigo es para el taggeo de los flujos que recorre el usuario
    private static final String LOG_TAG = LeeCapturaActivity.class.getSimpleName();

    private ActivityLeeCapturaBinding mDataBinding;
    private LeeCapturaViewModel mViewModel;
    private Context mContext = LeeCapturaActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_captura);

        mViewModel = new LeeCapturaViewModel(LeeCapturaActivity.this);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lee_captura);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0f);

        setListeners(mDataBinding);
        subscribeUI(mViewModel);
    }

    private void subscribeUI(LeeCapturaViewModel mViewModel) {
        mViewModel.getmLiveData().observe(this, retornoDatosConsultaDB -> {
            int estatus = retornoDatosConsultaDB.getEstatusConsulta();

            if(estatus == DatosConsultaHolder.NO_EXISTE_DB)
                Toast.makeText(mContext, "No existe la base de datos", Toast.LENGTH_LONG).show();
            else if (estatus == DatosConsultaHolder.NO_HAY_DATA)
                ModalDialogs.showMessage(mContext,":(", "No hay datos que mostrar");
            else if (estatus == DatosConsultaHolder.OPERACION_REALIZADA)
                setNewActivity(LeeDatosActivity.class);
        });
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

                mViewModel.getDataFromDB();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setNewActivity(Class<?> cls) {
        Intent next = new Intent(mContext, cls);

        mContext.startActivity(next);
        Log.d(LOG_TAG, " db: inicia activity " + cls.getSimpleName());
    }
}