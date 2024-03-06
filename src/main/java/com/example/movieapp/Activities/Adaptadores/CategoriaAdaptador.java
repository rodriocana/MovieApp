package com.example.movieapp.Activities.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapp.Activities.Activities.detalleActivity;
import com.example.movieapp.Activities.Domain.Genero;
import com.example.movieapp.Activities.Domain.ListaPeliculas;
import com.example.movieapp.Activities.Domain.itemGeneros;
import com.example.movieapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoriaAdaptador extends RecyclerView.Adapter<CategoriaAdaptador.ViewHolder> {

    ArrayList<itemGeneros> items;
    Context context;


    public CategoriaAdaptador(ArrayList<itemGeneros> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoriaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categoria,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdaptador.ViewHolder holder, int position) {

        holder.TextoTitlo.setText(items.get(position).getName());


        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView TextoTitlo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextoTitlo = itemView.findViewById(R.id.txtTitulo);

        }
    }
}
