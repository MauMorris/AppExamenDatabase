package com.example.idscomercial.examen1.ui.dapterutils;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.idscomercial.examen1.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<DataRow> dataList;

    public DataAdapter(List<DataRow> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final boolean shouldAttachToParentImmediately = false;
        int layoutIdForListItem = R.layout.row_resultado;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new DataAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        DataRow ci = dataList.get(position);

        holder.id.setText(ci.getIdRow());

        holder.nombre.setText(ci.getNombreRow());
        holder.apellidos.setText(ci.getApellidosRow());
        holder.direccion.setText(ci.getDireccionRow());

        holder.telefono.setText(ci.getTelefonoRow());
        holder.mail.setText(ci.getMailRow());
        holder.fechaNacimiento.setText(ci.getFechaNacimientoRow());

        holder.edoCivil.setText(ci.getEdoCivilRow());
        holder.usuario.setText(ci.getUsuarioRow());
        holder.contraseña.setText(ci.getContraseñaRow());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected AppCompatTextView id;

        protected AppCompatTextView nombre;
        protected AppCompatTextView apellidos;
        protected AppCompatTextView direccion;

        protected AppCompatTextView telefono;
        protected AppCompatTextView mail;
        protected AppCompatTextView fechaNacimiento;

        protected AppCompatTextView edoCivil;
        protected AppCompatTextView usuario;
        protected AppCompatTextView contraseña;


        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_requested_text_view);
            nombre = itemView.findViewById(R.id.nombre_requested_text_view);
            apellidos = itemView.findViewById(R.id.apellidos_requested_text_view);
            direccion = itemView.findViewById(R.id.direccion_requested_text_view);

            telefono = itemView.findViewById(R.id.telefono_requested_text_view);
            mail = itemView.findViewById(R.id.mail_requested_text_view);
            fechaNacimiento = itemView.findViewById(R.id.fecha_nacimiento_requested_text_view);

            edoCivil = itemView.findViewById(R.id.estado_civil_requested_text_view);
            usuario = itemView.findViewById(R.id.usuario_requested_text_view);
            contraseña = itemView.findViewById(R.id.contraseña_requested_text_view);
        }
    }
}