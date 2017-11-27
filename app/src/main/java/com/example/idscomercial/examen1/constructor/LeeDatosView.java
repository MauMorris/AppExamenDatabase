package com.example.idscomercial.examen1.constructor;

import com.example.idscomercial.examen1.datatoshow.DataRow;

import java.util.List;

public interface LeeDatosView {
    List<DataRow> read();
    void destroy();
}