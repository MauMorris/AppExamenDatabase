package com.example.idscomercial.examen1.datasource;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.idscomercial.examen1.repository.WebTaskInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.net.HttpURLConnection;


public class QueryFromInternet extends AsyncTask<String, Void, String> {
    private Context mContext;
    private WebTaskInterface mTaskInterface;
    private String name, salary, age;

    public QueryFromInternet(Context mContext, WebTaskInterface mTaskInterface, String name, String salary, String age) {
        this.mContext = mContext;
        this.mTaskInterface = mTaskInterface;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL("https://dummy.restapiexample.com/api/v1/create");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;

        try {
            connection =  (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream oStream = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(oStream, "UTF-8"));
            String json = "{" +
                    "\"name\":\"test\"," +
                    "\"salary\":\"123\"," +
                    "\"age\":\"23\"" +
                    "}";
            writer.write(json);
            writer.flush();
            writer.close();

            oStream.close();

            connection.connect();

            int responseCode = connection.getResponseCode();
            Log.d("LOG REQUEST", responseCode + "result");
            stream = connection.getInputStream();
            result = readStream(stream);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null)
                connection.disconnect();
        }
        return result;
    }

    private String readStream(InputStream stream) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(stream,"utf-8"));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        br.close();

        return sb.toString();
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
