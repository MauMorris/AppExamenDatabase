package com.example.idscomercial.examen1.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_DATABASE = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, 1);
        Log.d(LOG_DATABASE, " db: constructor de la base de datos");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + DatabaseContract.TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseContract.COL_NOMBRE + " TEXT, " +
                DatabaseContract.COL_APELLIDOS + " TEXT, " +
                DatabaseContract.COL_DIRECCION + " TEXT, " +
                DatabaseContract.COL_TELEFONO + " TEXT, " +
                DatabaseContract.COL_MAIL + " TEXT, " +
                DatabaseContract.COL_NACIMIENTO + " TEXT, " +
                DatabaseContract.COL_EDO_CIVIL + " TEXT, " +
                DatabaseContract.COL_USUARIO + " TEXT, " +
                DatabaseContract.COL_CONTRASEÑA + " TEXT " + ")";

        db.execSQL(CREATE_TABLE);

        Log.d(LOG_DATABASE, " db: crea tabla");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(db);

        Log.d(LOG_DATABASE, " db: actualiza db");
    }

    public boolean insertData(String nombre, String apellidos, String direccion, String telefono,
                              String mail, String fecha, String edo_civil, String usuario, String contraseña) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.COL_NOMBRE, nombre);
        contentValues.put(DatabaseContract.COL_APELLIDOS, apellidos);
        contentValues.put(DatabaseContract.COL_DIRECCION, direccion);
        contentValues.put(DatabaseContract.COL_TELEFONO, telefono);
        contentValues.put(DatabaseContract.COL_MAIL, mail);
        contentValues.put(DatabaseContract.COL_NACIMIENTO, fecha);
        contentValues.put(DatabaseContract.COL_EDO_CIVIL, edo_civil);
        contentValues.put(DatabaseContract.COL_USUARIO, usuario);
        contentValues.put(DatabaseContract.COL_CONTRASEÑA, contraseña);

        long result = db.insert(DatabaseContract.TABLE_NAME, null, contentValues);

        Log.d(LOG_DATABASE, " db: datos insertados");

        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;

        try {
            res = db.rawQuery("SELECT * FROM " + DatabaseContract.TABLE_NAME, null);
        } catch (Exception e) {
            res = null;
            Log.d(LOG_DATABASE, " db: "+ e.getMessage());
        }
        Log.d(LOG_DATABASE, "db: regresa datos " + res.toString());

        return res;
    }


    public boolean updateData(String id, String nombre, String apellidos, String direccion, String telefono,
                              String mail, String fecha, String edo_civil, String usuario, String contraseña) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.COL_ID, id);
        contentValues.put(DatabaseContract.COL_NOMBRE, nombre);
        contentValues.put(DatabaseContract.COL_APELLIDOS, apellidos);
        contentValues.put(DatabaseContract.COL_DIRECCION, direccion);
        contentValues.put(DatabaseContract.COL_TELEFONO, telefono);
        contentValues.put(DatabaseContract.COL_MAIL, mail);
        contentValues.put(DatabaseContract.COL_NACIMIENTO, fecha);
        contentValues.put(DatabaseContract.COL_EDO_CIVIL, edo_civil);
        contentValues.put(DatabaseContract.COL_USUARIO, usuario);
        contentValues.put(DatabaseContract.COL_CONTRASEÑA, contraseña);

        db.update(DatabaseContract.TABLE_NAME, contentValues, "ID = ?", new String[]{id});

        Log.d(LOG_DATABASE, " db: datos actualizados");
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d(LOG_DATABASE, " db: datos eliminados");

        return db.delete(DatabaseContract.TABLE_NAME, "ID = ?", new String[]{id});
    }
}
