package com.example.idscomercial.examen1.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.idscomercial.examen1.constructor.LeeCapturaView;
import com.example.idscomercial.examen1.data.CrudDatabaseImpl;
import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.datatoshow.DataRow;

import java.util.List;

public class LeeCapturaImpl implements LeeCapturaView {
    private static final String LOG_TAG = LeeCapturaImpl.class.getSimpleName();

    private Context mContext;
    private CrudDatabaseImpl mCrud;

    public LeeCapturaImpl(Context context, DatabaseHelper database) {
        mContext = context;
        mCrud = new CrudDatabaseImpl(this.mContext, database);
    }

    @Override
    public void newActivity(Class<?> cls) {
        Intent next = new Intent(mContext, cls);

        mContext.startActivity(next);
        Log.d(LOG_TAG, " db: inicia activity " + cls.getSimpleName());
    }

    @Override
    public boolean read() {
        List<DataRow> data = mCrud.readData();
        boolean lectura = true;

        if (data == null || data.isEmpty() || data.size() == 0)
            lectura = false;

        return lectura;
    }
}