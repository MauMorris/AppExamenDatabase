package com.example.idscomercial.examen1.repository;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.idscomercial.examen1.datasource.DataRow;
import com.example.idscomercial.examen1.datasource.DatabaseContract;
import com.example.idscomercial.examen1.datasource.DatabaseHelper;
import com.example.idscomercial.examen1.datasource.DatabaseSyncIntentService;
import com.example.idscomercial.examen1.datasource.QueryFromInternet;
import com.example.idscomercial.examen1.datasource.QueryTask;

import com.example.idscomercial.examen1.vm.ReturnDataFromWeb;
import com.example.idscomercial.examen1.vm.datareturnutils.DatosConsultaHolder;
import com.example.idscomercial.examen1.vm.CrudDatabaseRepositoryCallback;
import com.example.idscomercial.examen1.vm.ReturnDataFromTask;

import java.util.ArrayList;
import java.util.List;

public class CrudDatabaseRepository implements CrudDatabaseRepositoryCallback {

    private static final String LOG_TAG = CrudDatabaseRepository.class.getSimpleName();

    private static CrudDatabaseRepository sInstance;
    private Context mContext;
    private DatabaseHelper mDatabase;

    private CrudDatabaseRepository(Context context) {
        mContext = context;
        mDatabase = new DatabaseHelper(mContext);
    }

    public static CrudDatabaseRepository getInstance(Context mContext) {
        if (sInstance == null) {
            sInstance = new CrudDatabaseRepository(mContext);
        }
        return sInstance;
    }

    @Override
    public void readData(ReturnDataFromTask returnToViewModel) {
        Cursor[] res = new Cursor[1];
        final List<DataRow>[] bufferList = new List[]{new ArrayList<>()};
        DatosConsultaHolder buffer = new DatosConsultaHolder();

        QueryTask queryTask = new QueryTask(mContext, new TaskInterface() {
            @Override
            public void sucessResultPostExecute(String result, Cursor cursor) {
                res[0] = cursor;
                bufferList[0] = null;

                buffer.setEstatusConsulta(DatosConsultaHolder.ACCESO_CORRECTO);
                buffer.setListOfData(bufferList[0]);
                buffer.setCursorData(res[0]);

                if (res[0].getCount() == 0) {
                    bufferList[0] = null;
                    res[0] = null;

                    buffer.setEstatusConsulta(DatosConsultaHolder.NO_HAY_DATA);
                    buffer.setListOfData(bufferList[0]);
                    buffer.setCursorData(res[0]);

                    Log.d(LOG_TAG, " db: no hay campos en la db");
                } else {
                    bufferList[0] = new ArrayList<>();
                    DataRow row;

                    while (res[0].moveToNext()) {
                        row = new DataRow();

                        row.setIdRow(DatabaseContract.COL_ID + " : " + res[0].getString(0));
                        row.setNombreRow(DatabaseContract.COL_NOMBRE + " : " + res[0].getString(1));
                        row.setApellidosRow(DatabaseContract.COL_APELLIDOS + " : " + res[0].getString(2));
                        row.setDireccionRow(DatabaseContract.COL_DIRECCION + " : " + res[0].getString(3));
                        row.setTelefonoRow(DatabaseContract.COL_TELEFONO + " : " + res[0].getString(4));
                        row.setMailRow(DatabaseContract.COL_MAIL + " : " + res[0].getString(5));
                        row.setFechaNacimientoRow(DatabaseContract.COL_NACIMIENTO + " : " + res[0].getString(6));
                        row.setEdoCivilRow(DatabaseContract.COL_EDO_CIVIL + " : " + res[0].getString(7));
                        row.setUsuarioRow(DatabaseContract.COL_USUARIO + " : " + res[0].getString(8));
                        row.setContraseñaRow(DatabaseContract.COL_CONTRASEÑA + " : " + res[0].getString(9));

                        bufferList[0].add(row);
                    }
                    res[0].close();

                    buffer.setEstatusConsulta(DatosConsultaHolder.OPERACION_REALIZADA);
                    buffer.setListOfData(bufferList[0]);
                    buffer.setCursorData(res[0]);

                    Log.d(LOG_TAG, " db: lectura finalizada ");
                }
                returnToViewModel.returnData(buffer);
            }

            @Override
            public void errorResultPostExecute(String error, Cursor cursor) {
                Log.d(LOG_TAG, " db: no hay tablas en la db");

                res[0] = null;
                bufferList[0] = null;

                buffer.setEstatusConsulta(DatosConsultaHolder.NO_EXISTE_DB);
                buffer.setListOfData(bufferList[0]);
                buffer.setCursorData(res[0]);

                returnToViewModel.returnData(buffer);
            }
        }, QueryTask.READ_ALL);
        
        queryTask.execute("");
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

    @Override
    public void readAllData(Context context, ReturnDataFromTask returnDataFromTask) {
        Intent intentToSyncDataFromDB = new Intent(context, DatabaseSyncIntentService.class);
        context.startService(intentToSyncDataFromDB);
    }

    @Override
    public void getDataFromWeb(String name, String salary, String age, ReturnDataFromWeb returnDataFromWeb) {
        QueryFromInternet mTask = new QueryFromInternet(mContext, new WebTaskInterface() {
            @Override
            public void sucessResultPostExecute(String result, String cursor) {
                returnDataFromWeb.returnWebData(cursor);
            }

            @Override
            public void errorResultPostExecute(String error, String cursor) {
                returnDataFromWeb.returnWebData(cursor);
            }
        }, name, salary, age);
        mTask.execute("");
    }
}