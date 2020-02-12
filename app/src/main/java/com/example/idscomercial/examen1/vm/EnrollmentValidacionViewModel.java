package com.example.idscomercial.examen1.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.regex.Pattern;

public class EnrollmentValidacionViewModel extends AndroidViewModel {
    public static final int NO_DATA = 0;
    public static final int WRONG_DATA = 1;
    public static final int DATA_OK = 2;

    public EnrollmentValidacionViewModel(@NonNull Application application) {
        super(application);
    }

    public int validaNumero(String numeroTelefonicoEt) {
        String PHONE_REGEX = "\\d{2}\\d{8}";


        String data;
        data = numeroTelefonicoEt.trim();

        data = data.replace(",", "");
        data = data.replace(".", "");
        data = data.replace(";", "");
        data = data.replace("(", "");
        data = data.replace(")", "");
        data = data.replace("/", "");
        data = data.replace(" ", "");

        if(data.equals("") || data.length() == 0) {
            return NO_DATA;
        }

        boolean result = isValid(data, PHONE_REGEX, true);

        return result? DATA_OK : WRONG_DATA;
    }

    private static boolean isValid(String data, String regex, boolean required) {
        if (required && !Pattern.matches(regex, data)){
            return false;
        }
        return true;
    }

    public String returnFormattedNumber(String numeroFromET) {
        numeroFromET = numeroFromET.replace(" ", "");

        return numeroFromET;
    }
}