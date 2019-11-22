package com.example.practicajuego21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.ViewHolder> {

    private ArrayList<Persona> lista;

    public AdaptadorPersona(ArrayList<Persona> lista){
        this.lista = lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView numero;
        ImageView foto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            numero = itemView.findViewById(R.id.numero);
            foto = itemView.findViewById(R.id.foto);
        }
    }
    @NonNull
    @Override
    public AdaptadorPersona.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultado,null,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersona.ViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).getNombre());
        holder.numero.setText(String.valueOf(lista.get(position).getNumero()));
        holder.foto.setImageResource(R.drawable.borracho);
    }

    @Override
    public int getItemCount() { return lista.size(); }
}
