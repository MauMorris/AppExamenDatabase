package com.example.idscomercial.examen1.impl;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.idscomercial.examen1.constructor.CapturaDatosView;
import com.example.idscomercial.examen1.data.CrudDatabaseImpl;
import com.example.idscomercial.examen1.data.DatabaseHelper;

import java.util.regex.Pattern;

public class CapturaDatosImpl implements CapturaDatosView {
    private CrudDatabaseImpl mCrud;

    public CapturaDatosImpl(Context mContext, DatabaseHelper mDatabase) {
        mCrud = new CrudDatabaseImpl(mContext, mDatabase);
    }

    @Override
    public boolean validaDatos(TextInputEditText nombre, TextInputLayout tilNombre,
                               TextInputEditText apellido, TextInputLayout tilApellido,
                               TextInputEditText direccion, TextInputLayout tilDireccion,
                               TextInputEditText telefono, TextInputLayout tilTelefono,
                               TextInputEditText mail, TextInputLayout tilMail,
                               TextInputEditText fechaNacimiento, TextInputLayout tilFechaNacimiento,
                               TextInputEditText edoCivil, TextInputLayout tilEdoCivil,
                               TextInputEditText usuario, TextInputLayout tilUsuario,
                               TextInputEditText contraseña, TextInputLayout tilContraseña) {

        final String NAME_REGEX = "[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ ]+";
        final String LAST_REGEX = "[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ ]+";
        final String ADDRESS_REGEX = "[A-Za-z0-9äÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ#. -]+";
        final String PHONE_REGEX = "\\d{2}-\\d{8}";
        final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final String DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
        final String SINGLE_REGEX = "^[A-Za-z]";
        final String USER_REGEX = "[A-Za-z0-9. -_]+";
        final String PASSWORD_REGEX = "[A-Za-z0-9!@#$%&=. -]+";

        final String MSJ_NAME = "dato incorrecto";
        final String MSJ_LAST = "dato incorrecto";
        final String MSJ_ADDRESS = "dato incorrecto: ej calle a #123-1 col abc";
        final String MSJ_PHONE = "formato incorrecto: intenta de nuevo ej (55-11223344)";
        final String MSJ_MAIL = "formato incorrecto: ej (add@bie.co)";
        final String MSJ_DATE = "formato incorrecto: ej (12-13-1990)";
        final String MSJ_SINGLE = "dato incorrecto";
        final String MSJ_USER = "dato incorrecto";
        final String MSJ_PASSWORD = "dato incorrecto";

        Boolean [] result = new Boolean[9];
        String [] datos = new String[9];
        Boolean resultado = true;

        datos[0] = nombre.getText().toString().trim();
        datos[1] = apellido.getText().toString().trim();
        datos[2] = direccion.getText().toString().trim();

        datos[3] = telefono.getText().toString().trim();
        datos[4] = mail.getText().toString().trim();
        datos[5] = fechaNacimiento.getText().toString().trim();

        datos[6] = edoCivil.getText().toString().trim();
        datos[7] = usuario.getText().toString().trim();
        datos[8] = contraseña.getText().toString().trim();

        for (String dato : datos) {
            dato = dato.replace(",", "");
            dato = dato.replace(".", "");
            dato = dato.replace(";", "");
            dato = dato.replace("(", "");
            dato = dato.replace(")", "");
            dato = dato.replace("/", "");
            dato = dato.replace(" ", "");

            if(dato.equals("")){
                isValid(nombre, tilNombre, NAME_REGEX, MSJ_NAME, true);
                isValid(apellido, tilApellido, LAST_REGEX, MSJ_LAST, true);
                isValid(direccion, tilDireccion, ADDRESS_REGEX, MSJ_ADDRESS, true);
                isValid(telefono, tilTelefono, PHONE_REGEX, MSJ_PHONE, true);
                isValid(mail, tilMail, EMAIL_REGEX, MSJ_MAIL, true);
                isValid(fechaNacimiento, tilFechaNacimiento, DATE_REGEX, MSJ_DATE, true);
                isValid(edoCivil, tilEdoCivil, SINGLE_REGEX, MSJ_SINGLE, true);
                isValid(usuario, tilUsuario, USER_REGEX, MSJ_USER, true);
                isValid(contraseña, tilContraseña, PASSWORD_REGEX, MSJ_PASSWORD, true);

                return false;
            }
        }
        result[0] = isValid(nombre, tilNombre, NAME_REGEX, MSJ_NAME, true);
        result[1] = isValid(apellido, tilApellido, LAST_REGEX, MSJ_LAST, true);
        result[2] = isValid(direccion, tilDireccion, ADDRESS_REGEX, MSJ_ADDRESS, true);
        result[3] = isValid(telefono, tilTelefono, PHONE_REGEX, MSJ_PHONE, true);
        result[4] = isValid(mail, tilMail, EMAIL_REGEX, MSJ_MAIL, true);
        result[5] = isValid(fechaNacimiento, tilFechaNacimiento, DATE_REGEX, MSJ_DATE, true);
        result[6] = isValid(edoCivil, tilEdoCivil, SINGLE_REGEX, MSJ_SINGLE, true);
        result[7] = isValid(usuario, tilUsuario, USER_REGEX, MSJ_USER, true);
        result[8] = isValid(contraseña, tilContraseña, PASSWORD_REGEX, MSJ_PASSWORD, true);

        resultado = result[0] && result[1] && result[2] && result[3] && result[4] && result[5] && result[6] && result[7] && result[8];

       return resultado;
    }

    @Override
    public void getSnackbar(View view, String mensaje) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void insert(String nombre, String apellidos,
                       String direccion, String telefono, String mail,String fecha,
                       String edoCivil,String usuario, String contraseña) {
        mCrud.insertData(nombre, apellidos, direccion, telefono, mail, fecha, edoCivil, usuario, contraseña);
    }

    private static boolean isValid(TextInputEditText editText, TextInputLayout til, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();

        til.setError(null);

        if ( required && !tieneText(editText, til))
            return false;
        if (required && !Pattern.matches(regex, text)) {
            til.setError(errMsg);
            return false;
        }

        return true;
    }

    private static boolean tieneText(TextInputEditText editText, TextInputLayout til) {
        final String mError = "dato requerido";

        String text = editText.getText().toString().trim();
        til.setError(null);

        if (text.length() == 0) {
            til.setError(mError);
            return false;
        }
        return true;
    }
}