package com.example.idscomercial.examen1.vm;

import android.content.Context;

import com.example.idscomercial.examen1.repository.CrudDatabaseImpl;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.repository.datareturnutils.RetornoDatosConsultaDB;
import com.example.idscomercial.examen1.ui.dapterutils.DataRow;

import java.util.ArrayList;
import java.util.List;

public class LeeDatosImpl {

    private CrudDatabaseImpl mCrud;

    public LeeDatosImpl(Context mContext, DatabaseHelper mDatabase) {
        mCrud = new CrudDatabaseImpl(mContext, mDatabase);
    }

    public RetornoDatosConsultaDB getDataFromDB() {
        RetornoDatosConsultaDB data = mCrud.readData();
        int estatus = data.getEstatusConsulta();

        if (estatus == RetornoDatosConsultaDB.OPERACION_REALIZADA) {
            return data;
        } else {
            return null;
        }
    }

    public void destroy() {
        mCrud = null;
    }
}