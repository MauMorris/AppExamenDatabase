package com.example.idscomercial.examen1.repository;

import com.example.idscomercial.examen1.repository.datareturnutils.RetornoDatosConsultaDB;
import com.example.idscomercial.examen1.ui.dapterutils.DataRow;

import java.util.List;

public interface CrudDatabaseView {
    RetornoDatosConsultaDB readData();
    void insertData(String nombre, String apellidos, String direccion, String telefono,
                     String mail, String fecha, String edo_civil, String usuario, String contraseña);
    void updateData(String id, String nombre, String apellidos, String direccion, String telefono,
                    String mail,String fecha,String edo_civil,String usuario, String contraseña);
    void deleteData(String id);
}