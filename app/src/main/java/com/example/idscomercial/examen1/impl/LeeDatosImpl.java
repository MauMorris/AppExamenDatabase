package com.example.idscomercial.examen1.impl;

import android.content.Context;

import com.example.idscomercial.examen1.constructor.LeeDatosView;
import com.example.idscomercial.examen1.data.CrudDatabaseImpl;
import com.example.idscomercial.examen1.data.DatabaseHelper;
import com.example.idscomercial.examen1.datatoshow.DataRow;

import java.util.ArrayList;
import java.util.List;

public class LeeDatosImpl implements LeeDatosView {

    private CrudDatabaseImpl mCrud;

    public LeeDatosImpl(Context mContext, DatabaseHelper mDatabase) {
        mCrud = new CrudDatabaseImpl(mContext, mDatabase);
    }

    @Override
    public List<DataRow> read() {
        List <DataRow> data = mCrud.readData();
        if(data == null || data.isEmpty() || data.size() == 0){
            data = new ArrayList<>();
            data.add(new DataRow());
        }

        return data;
    }

    @Override
    public void destroy() {
        mCrud = null;
    }
}