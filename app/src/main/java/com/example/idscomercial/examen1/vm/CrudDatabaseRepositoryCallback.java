package com.example.idscomercial.examen1.vm;

import android.content.Context;

public interface CrudDatabaseRepositoryCallback {
    void readData(ReturnDataFromTask returnToViewModel);
    void insertData(String nombre, String apellidos, String direccion, String telefono,
                     String mail, String fecha, String edo_civil, String usuario, String contraseña);
    void updateData(String id, String nombre, String apellidos, String direccion, String telefono,
                    String mail,String fecha,String edo_civil,String usuario, String contraseña);
    void deleteData(String id);

    void readAllData(Context context, ReturnDataFromTask returnDataFromTask);
}