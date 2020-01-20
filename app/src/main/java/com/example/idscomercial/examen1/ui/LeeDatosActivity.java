package com.example.idscomercial.examen1.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.databinding.ActivityLeeDatosBinding;
import com.example.idscomercial.examen1.ui.dapterutils.DataAdapter;
import com.example.idscomercial.examen1.vm.LeeDatosImpl;

public class LeeDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = LeeDatosActivity.class.getSimpleName();

    private LeeDatosImpl mPresenter;
    private ActivityLeeDatosBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mPresenter = new LeeDatosImpl(LeeDatosActivity.this, new DatabaseHelper(LeeDatosActivity.this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_lee_datos);

        setViews(mDataBinding);

        mDataBinding.goBackFab.setOnClickListener(this);
    }

    private void setViews(ActivityLeeDatosBinding mDataBinding) {
        mDataBinding.resultadoRecyclerView.setHasFixedSize(true);

        mDataBinding.resultadoRecyclerView.setItemViewCacheSize(15);
        mDataBinding.resultadoRecyclerView.setDrawingCacheEnabled(true);
        mDataBinding.resultadoRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDataBinding.resultadoRecyclerView.setLayoutManager(mLayoutManager);

        DataAdapter mAdapter = new DataAdapter(mPresenter.getDataFromDB().getmDataRow());
        mDataBinding.resultadoRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, " db: regresa a la activity en la pila y elimina " + LOG_TAG );
        finish();
        mPresenter.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, " db: presiona back: regresa a la activity en la pila y elimina " + LOG_TAG);
        mPresenter.destroy();
    }
}