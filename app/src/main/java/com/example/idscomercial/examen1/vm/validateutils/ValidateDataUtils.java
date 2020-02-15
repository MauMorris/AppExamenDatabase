package com.example.idscomercial.examen1.vm.validateutils;

import java.util.regex.Pattern;

public class ValidateDataUtils {
    public static final int NO_DATA = 0;
    public static final int WRONG_DATA = 1;
    public static final int WRONG_DATE = 3;
    public static final int DATA_OK = 2;

    public static int validateData(String dataFromString, String regexPattern, boolean required){
        String data;
        data = dataFromString.trim();

        data = data.replace(" ", "");

        if(data.equals("") || data.length() == 0) {
            return NO_DATA;
        }

        boolean result = isValid(data, regexPattern, required);

        return result? DATA_OK : WRONG_DATA;
    }

    private static boolean isValid(String data, String regexPattern, boolean required) {
        if (required && !Pattern.matches(regexPattern, data)){
            return false;
        }
        return true;
    }
}