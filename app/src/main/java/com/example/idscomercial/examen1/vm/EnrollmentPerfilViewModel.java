package com.example.idscomercial.examen1.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.idscomercial.examen1.vm.validateutils.ValidateDataUtils;

public class EnrollmentPerfilViewModel extends AndroidViewModel {
    private MutableLiveData<String> mutableLiveData;

    public EnrollmentPerfilViewModel(@NonNull Application application) {
        super(application);
        if( mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }
    }

    public int validaTexto(String textoEt){
        final String NAME_REGEX = "[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ ]+";
        return ValidateDataUtils.validateData(textoEt, NAME_REGEX, true);
    }

    public int validaFecha(String fechaEt){
        final String DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";
        int error = ValidateDataUtils.validateData(fechaEt, DATE_REGEX, true);

        if(error == ValidateDataUtils.WRONG_DATA)
            error = ValidateDataUtils.WRONG_DATE;

        return error;
    }
}