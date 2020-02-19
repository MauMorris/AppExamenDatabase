package com.example.idscomercial.examen1.datasource;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.idscomercial.examen1.datasource.server.NetworkUtils;
import com.example.idscomercial.examen1.repository.WebTaskInterface;

import java.net.URL;
import java.util.HashMap;

public class QueryInternetTask extends AsyncTask<String, Void, String> {
    public static final String LOG_TAG = QueryInternetTask.class.getSimpleName();

    private Context mContext;
    private String urlPath;
    private HashMap header;
    private String requestType;
    private String json;

    private WebTaskInterface mTaskInterface;

    public QueryInternetTask(Context mContext, WebTaskInterface mTaskInterface,
                             String urlPath, HashMap header, String requestType, String json) {
        this.mContext = mContext;
        this.mTaskInterface = mTaskInterface;

        this.urlPath = urlPath;
        this.header = header;
        this.requestType = requestType;
        this.json = json;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = NetworkUtils.getUrl(urlPath);
        Log.v(LOG_TAG, "Starts connection");

        return NetworkUtils.getResponseFromHttpUrl(url, header, requestType, json);
    }


    @Override
    protected void onPostExecute(String s) {
        if (s == null) {
            mTaskInterface.errorResultPostExecute("error", s);
        } else {
            mTaskInterface.sucessResultPostExecute("ok", s);
        }

    }
}