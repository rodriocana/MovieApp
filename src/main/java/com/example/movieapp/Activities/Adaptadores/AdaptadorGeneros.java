package com.example.movieapp.Activities.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activities.Domain.Genre;
import com.example.movieapp.Activities.Domain.Result;

import com.example.movieapp.R;

import java.util.List;

public class AdaptadorGeneros  extends RecyclerView.Adapter<AdaptadorGeneros.ViewHolder>{

    private List<Genre> itemss;
    private Context context;

    public AdaptadorGeneros(List<Genre> itemss) {
        this.itemss = itemss;
    }

    @NonNull
    @Override
    public AdaptadorGeneros.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categoria, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorGeneros.ViewHolder holder, int position) {


        holder.TextoTitlo.setText(itemss.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // aqui hace falta un intent

            }
        });


    }

    @Override
    public int getItemCount() {
        return itemss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TextoTitlo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextoTitlo = itemView.findViewById(R.id.txtTitulo);

        }

    }
}
