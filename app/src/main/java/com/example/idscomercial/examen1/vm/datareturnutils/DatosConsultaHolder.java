package com.example.idscomercial.examen1.vm.datareturnutils;

import android.database.Cursor;

import com.example.idscomercial.examen1.datasource.DataRow;

import java.util.List;

public class DatosConsultaHolder {
    private List<DataRow> mDataRow;
    private int estatusConsulta;
    private Cursor cursorData;

    public static final int ACCESO_CORRECTO = 1;
    public static final int NO_HAY_DATA = 2;
    public static final int NO_EXISTE_DB = 3;
    public static final int OPERACION_REALIZADA = 4;

    public DatosConsultaHolder() {
    }

    public void setListOfData(List<DataRow> mDataRow) {
        this.mDataRow = mDataRow;
    }

    public void setEstatusConsulta(int estatusConsulta) {
        this.estatusConsulta = estatusConsulta;
    }

    public void setCursorData(Cursor cursorData) {
        this.cursorData = cursorData;
    }

    public List<DataRow> getmDataRow() {
        return mDataRow;
    }

    public int getEstatusConsulta() {
        return estatusConsulta;
    }

    public Cursor getCursorData() {
        return cursorData;
    }
}