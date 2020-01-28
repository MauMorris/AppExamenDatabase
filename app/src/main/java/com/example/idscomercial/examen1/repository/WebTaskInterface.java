package com.example.idscomercial.examen1.repository;

import android.database.Cursor;

public interface WebTaskInterface {
    void sucessResultPostExecute(String result, String cursor);
    void errorResultPostExecute(String error, String cursor);
}
