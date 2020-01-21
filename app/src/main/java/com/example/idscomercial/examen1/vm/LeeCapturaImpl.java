package com.example.idscomercial.examen1.vm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.idscomercial.examen1.repository.CrudDatabaseImpl;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.repository.datareturnutils.RetornoDatosConsultaDB;
import com.example.idscomercial.examen1.ui.dapterutils.DataRow;

import java.util.List;

public class LeeCapturaImpl {
    private static final String LOG_TAG = LeeCapturaImpl.class.getSimpleName();

    private Context mContext;
    private CrudDatabaseImpl mCrud;

    public LeeCapturaImpl(Context context, DatabaseHelper database) {
        mContext = context;
        mCrud = new CrudDatabaseImpl(this.mContext, database);
    }

    public int getDataFromDB() {
        RetornoDatosConsultaDB data;
        data = mCrud.readData();

        return data.getEstatusConsulta();
    }
}