package com.example.idscomercial.examen1.datatoshow;

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

            id = itemView.findViewById(R.id.tv_id_resultado);
            nombre = itemView.findViewById(R.id.tv_nombre_resultado);
            apellidos = itemView.findViewById(R.id.tv_apellidos_resultado);
            direccion = itemView.findViewById(R.id.tv_direccion_resultado);

            telefono = itemView.findViewById(R.id.tv_telefono_resultado);
            mail = itemView.findViewById(R.id.tv_mail_resultado);
            fechaNacimiento = itemView.findViewById(R.id.tv_fecha_nacimiento_resultado);

            edoCivil = itemView.findViewById(R.id.tv_estado_civil_resultado);
            usuario = itemView.findViewById(R.id.tv_usuario_resultado);
            contraseña = itemView.findViewById(R.id.tv_contraseña_resultado);
        }
    }
}