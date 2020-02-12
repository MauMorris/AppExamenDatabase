package com.example.idscomercial.examen1.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.databinding.ActivityLeeDatosBinding;
import com.example.idscomercial.examen1.ui.adapterutils.DataAdapter;
import com.example.idscomercial.examen1.vm.LeeDatosViewModel;

public class LeeDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = LeeDatosActivity.class.getSimpleName();

    private LeeDatosViewModel mViewModel;
    private ActivityLeeDatosBinding mDataBinding;
    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mViewModel = new LeeDatosViewModel(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lee_datos);

        setViews(mDataBinding);
        mDataBinding.goBackFab.setOnClickListener(this);
        subscribeUI(mViewModel);

        mViewModel.getDataFromDB(this);
    }

    private void subscribeUI(LeeDatosViewModel mViewModel) {
        mViewModel.getLiveData().observe(this, datosConsultaHolder -> mAdapter.setData(datosConsultaHolder));
        mViewModel.getWebLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LeeDatosActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setViews(ActivityLeeDatosBinding mDataBinding) {
        mDataBinding.resultadoRecyclerView.setHasFixedSize(true);

        mDataBinding.resultadoRecyclerView.setItemViewCacheSize(15);
        mDataBinding.resultadoRecyclerView.setDrawingCacheEnabled(true);
        mDataBinding.resultadoRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDataBinding.resultadoRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DataAdapter();
        mDataBinding.resultadoRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        mViewModel.getDataFromInternet("test", "123", "23");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}