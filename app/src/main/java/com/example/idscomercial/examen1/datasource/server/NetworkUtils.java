package com.example.idscomercial.examen1.datasource.server;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final int TIME_OUT = 10000;
    private static final String CHARSET_NAME = "UTF-8";

    public static URL getUrl(String urlPath){
        Uri serverQueryUri = Uri.parse(urlPath);

        try {
            URL serverQueryUrl = new URL(serverQueryUri.toString());
            Log.v(LOG_TAG, "URL: " + serverQueryUrl);
            return serverQueryUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttpUrl(URL url, HashMap header, String requestType, String json){
        InputStream stream = null;
        HttpURLConnection connection = null;

        String result = null;

        try {
            connection =  (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            connection.setRequestMethod(requestType);
            connection.setDoInput(true);

            for (Object entry : header.entrySet()) {
                Map.Entry entryValue = (Map.Entry) entry;

                connection.setRequestProperty((String) entryValue.getKey(), (String) entryValue.getValue());
            }

            OutputStream oStream = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(oStream, CHARSET_NAME));

            writer.write(json);
            writer.flush();
            writer.close();

            oStream.close();

            connection.connect();

            int responseCode = connection.getResponseCode();

            Log.d(LOG_TAG, responseCode + " result");

            stream = connection.getInputStream();
            result = readStream(stream);

        } catch (MalformedURLException e) {
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
        //TODO agregar clase generica para guardar el codigo de respuesta y en otra variable
        //el contenido o string de resultado, para parsear de acuerdo al codigo de respuesta
        return result;
    }

    private static String readStream(InputStream stream) throws IOException {

        Log.d(LOG_TAG, stream.toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(stream,CHARSET_NAME));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }

        br.close();
        Log.d(LOG_TAG, sb.toString());

        return sb.toString();
    }
}