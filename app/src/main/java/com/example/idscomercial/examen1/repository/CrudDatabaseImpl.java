package com.example.idscomercial.examen1.repository;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.idscomercial.examen1.datasource.DatabaseContract;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;

import com.example.idscomercial.examen1.repository.datareturnutils.RetornoDatosConsultaDB;

import com.example.idscomercial.examen1.ui.dapterutils.DataRow;

import java.util.ArrayList;
import java.util.List;

public class CrudDatabaseImpl implements CrudDatabaseView {

    private static final String LOG_TAG = CrudDatabaseImpl.class.getSimpleName();

    private Context mContext;
    private DatabaseHelper mDatabase;

    public CrudDatabaseImpl(Context context, DatabaseHelper myDb) {
        mContext = context;
        mDatabase = myDb;
    }

    @Override
    public RetornoDatosConsultaDB readData() {
        Cursor res;
        RetornoDatosConsultaDB buffer = new RetornoDatosConsultaDB();
        List<DataRow> bufferList;

        DataRow row;

        try {
            res = mDatabase.getAllData();
            bufferList = null;

            buffer.setEstatusConsulta(RetornoDatosConsultaDB.ACCESO_CORRECTO);
            buffer.setListOfData(bufferList);
            buffer.setCursorData(res);

        } catch (Exception e) {
            Log.d(LOG_TAG, " db: no hay tablas en la db");

            res = null;
            bufferList = null;

            buffer.setEstatusConsulta(RetornoDatosConsultaDB.NO_EXISTE_DB);
            buffer.setListOfData(bufferList);
            buffer.setCursorData(res);
        }

        if (res != null) {
            if (res.getCount() == 0) {
                bufferList = null;
                res = null;

                buffer.setEstatusConsulta(RetornoDatosConsultaDB.NO_HAY_DATA);
                buffer.setListOfData(bufferList);
                buffer.setCursorData(res);

                Log.d(LOG_TAG, " db: no hay campos en la db");
            } else {
                bufferList = new ArrayList<>();

                while (res.moveToNext()) {
                    row = new DataRow();

                    row.setIdRow(DatabaseContract.COL_ID + " : " + res.getString(0));
                    row.setNombreRow(DatabaseContract.COL_NOMBRE + " : " + res.getString(1));
                    row.setApellidosRow(DatabaseContract.COL_APELLIDOS + " : " + res.getString(2));
                    row.setDireccionRow(DatabaseContract.COL_DIRECCION + " : " + res.getString(3));
                    row.setTelefonoRow(DatabaseContract.COL_TELEFONO + " : " + res.getString(4));
                    row.setMailRow(DatabaseContract.COL_MAIL + " : " + res.getString(5));
                    row.setFechaNacimientoRow(DatabaseContract.COL_NACIMIENTO + " : " + res.getString(6));
                    row.setEdoCivilRow(DatabaseContract.COL_EDO_CIVIL + " : " + res.getString(7));
                    row.setUsuarioRow(DatabaseContract.COL_USUARIO + " : " + res.getString(8));
                    row.setContraseñaRow(DatabaseContract.COL_CONTRASEÑA + " : " + res.getString(9));

                    bufferList.add(row);
                }
                res.close();

                buffer.setEstatusConsulta(RetornoDatosConsultaDB.OPERACION_REALIZADA);
                buffer.setListOfData(bufferList);
                buffer.setCursorData(res);

                Log.d(LOG_TAG, " db: lectura finalizada ");
            }
        }
        return buffer;
    }

    @Override
    public void updateData(String id, String nombre, String apellidos,
                           String direccion, String telefono, String mail, String fecha,
                           String edo_civil, String usuario, String contraseña) {

        boolean isUpdate = mDatabase.updateData(id, nombre, apellidos, direccion, telefono, mail, fecha, edo_civil, usuario, contraseña);
        if (isUpdate)
            Toast.makeText(mContext, "Data upDated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Data no updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void insertData(String nombre, String apellidos,
                           String direccion, String telefono, String mail, String fecha,
                           String edo_civil, String usuario, String contraseña) {

        boolean isInserted = mDatabase.insertData(nombre, apellidos, direccion, telefono, mail, fecha, edo_civil, usuario, contraseña);
        if (isInserted)
            Toast.makeText(mContext, "Data inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Data no inserted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteData(String id) {
        Integer delete = mDatabase.deleteData(id);
    }
}