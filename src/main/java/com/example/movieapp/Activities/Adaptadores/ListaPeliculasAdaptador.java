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
import com.example.movieapp.Activities.Domain.ListaPeliculas;
import com.example.movieapp.R;

public class ListaPeliculasAdaptador  extends RecyclerView.Adapter<ListaPeliculasAdaptador.ViewHolder> {

    ListaPeliculas items;
    Context context;


    public ListaPeliculasAdaptador(ListaPeliculas items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ListaPeliculasAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pelicula,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPeliculasAdaptador.ViewHolder holder, int position) {

        holder.TextoTitlo.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.portada);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(holder.itemView.getContext(), detalleActivity.class);
            intent.putExtra("id", items.getData().get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView TextoTitlo;
        ImageView portada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextoTitlo = itemView.findViewById(R.id.textViewTituloPelicula);
            portada = itemView.findViewById(R.id.portada);
        }
    }
}
