package com.example.idscomercial.examen1.repository;

import android.database.Cursor;

public interface DatabaseTaskInterface {
    void sucessResultPostExecute(String result, Cursor cursor);
    void errorResultPostExecute(String error, Cursor cursor);
}