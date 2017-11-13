package com.example.idscomercial.examen1.constructor;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

public interface CapturaDatosView {
    boolean validaDatos(TextInputEditText nombre, TextInputLayout tilNombre,
                        TextInputEditText apellido, TextInputLayout tilApellido,
                        TextInputEditText direccion, TextInputLayout tilDireccion,
                        TextInputEditText telefono, TextInputLayout tilTelefono,
                        TextInputEditText mail, TextInputLayout tilMail,
                        TextInputEditText fechaNacimiento, TextInputLayout tilFechaNacimiento,
                        TextInputEditText edoCivil, TextInputLayout tilEdoCivil,
                        TextInputEditText usuario, TextInputLayout tilUsuario,
                        TextInputEditText contraseña, TextInputLayout tilContraseña);
    void getSnackbar(View view, String mensaje);
    void insert(String nombre, String apellidos,
                String direccion, String telefono, String mail,String fecha,
                String edoCivil,String usuario, String contraseña);
}