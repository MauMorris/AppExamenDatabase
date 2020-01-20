package com.example.idscomercial.examen1.ui.modalutils;

import android.app.AlertDialog;
import android.content.Context;

public class ModalDialogs {
    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message);

        builder.show();
    }
}
