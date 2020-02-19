package com.example.idscomercial.examen1.datasource;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.idscomercial.examen1.datasource.database.DatabaseHelper;
import com.example.idscomercial.examen1.repository.DatabaseTaskInterface;

public class QueryDatabaseTask extends AsyncTask<String, Void, Cursor> {
    public static final String READ_ALL = "real_all_data";
    public static final String DELETE_ALL = "delete_all_data";
    public static final String INSERT_DATA = "insert_data_by_id";
    public static final String UPDATE_DATA = "update_data_by_id";

    private DatabaseTaskInterface mInterface;
    private Context mContext;
    private String mQuery;
    private DatabaseHelper mDatabase;

    public QueryDatabaseTask(Context context, DatabaseTaskInterface databaseTaskInterface, String query) {
        mContext = context;
        mDatabase = new DatabaseHelper(context);

        mInterface = databaseTaskInterface;
        mQuery = query;
    }

    @Override
    protected Cursor doInBackground(String... query) {
        Cursor res;
        switch (mQuery) {
            case READ_ALL:
                try {
                    res = mDatabase.getAllData();
                } catch (Exception e) {
                    res = null;
                }
                break;
            case DELETE_ALL:
                res = null;
                break;
            case INSERT_DATA:
                res = null;
                break;
            case UPDATE_DATA:
                res = null;
                break;
            default:
                res = null;
                break;
        }
        return res;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        if (cursor == null) {
            mInterface.errorResultPostExecute("error", cursor);
        } else {
            mInterface.sucessResultPostExecute("ok", cursor);
        }
    }
}