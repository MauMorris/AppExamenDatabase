package com.example.idscomercial.examen1.datasource;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.idscomercial.examen1.repository.TaskInterface;
import com.example.idscomercial.examen1.vm.datareturnutils.DatosConsultaHolder;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSyncIntentService extends IntentService {
    public static final String ACTION_FIN = "fin";

    public DatabaseSyncIntentService() {
        super("DatabaseSync");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Cursor[] res = new Cursor[1];
        final List<DataRow>[] bufferList = new List[]{new ArrayList<>()};
        DatosConsultaHolder buffer = new DatosConsultaHolder();

        new QueryTask(getBaseContext(), new TaskInterface() {
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

                }
                Intent bcIntent = new Intent();
                bcIntent.setAction(ACTION_FIN);
//                bcIntent.putExtra("final", buffer);
                sendBroadcast(bcIntent);
            }

            @Override
            public void errorResultPostExecute(String error, Cursor cursor) {

            }
        }, QueryTask.READ_ALL).execute("");
    }
}