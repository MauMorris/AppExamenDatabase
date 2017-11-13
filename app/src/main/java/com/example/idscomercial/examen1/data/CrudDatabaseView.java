package com.example.idscomercial.examen1.data;

import com.example.idscomercial.examen1.datatoshow.DataRow;

import java.util.List;

public interface CrudDatabaseView {
    List<DataRow> readData();
    void insertData(String nombre, String apellidos, String direccion, String telefono,
                     String mail, String fecha, String edo_civil, String usuario, String contraseña);
    void updateData(String id, String nombre, String apellidos, String direccion, String telefono,
                    String mail,String fecha,String edo_civil,String usuario, String contraseña);
    void deleteData(String id);
}