package com.example.idscomercial.examen1.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.idscomercial.examen1.R;
import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.datatoshow.DataAdapter;
import com.example.idscomercial.examen1.impl.LeeDatosImpl;

public class LeeDatosActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = LeeDatosActivity.class.getSimpleName();

    private LeeDatosImpl mPresenter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lee_datos);
        Log.d(LOG_TAG, " db: crea activity " + LOG_TAG);

        mPresenter = new LeeDatosImpl(LeeDatosActivity.this, new DatabaseHelper(LeeDatosActivity.this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.rv_resultado);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemViewCacheSize(15);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new DataAdapter(mPresenter.read());
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
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