package com.example.idscomercial.examen1.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.idscomercial.examen1.vm.validateutils.ValidateDataUtils;

public class EnrollmentValidacionViewModel extends AndroidViewModel {

    public EnrollmentValidacionViewModel(@NonNull Application application) {
        super(application);
    }

    public int validaNumero(String numeroTelefonicoEt) {
        String PHONE_REGEX = "\\d{2}\\d{8}";

        return ValidateDataUtils.validateData(numeroTelefonicoEt, PHONE_REGEX, true);
    }

    public String returnFormattedNumber(String numeroFromET) {
        numeroFromET = numeroFromET.replace(" ", "");

        return numeroFromET;
    }
}