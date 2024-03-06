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
import com.example.movieapp.Activities.Domain.PeliculasMejorValorada;
import com.example.movieapp.Activities.Domain.Result;
import com.example.movieapp.R;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class PeliculasMejorValoradaAdaptador extends RecyclerView.Adapter<PeliculasMejorValoradaAdaptador.ViewHolder> {

    private List<Result> itemss;
    private Context context;

    public PeliculasMejorValoradaAdaptador(List<Result> itemss) {
        this.itemss = itemss;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result currentItem = itemss.get(position);

        holder.TextoTitulo.setText(currentItem.getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        double voteAverage = currentItem.getVoteAverage();

        // esta funcion formatea el valor de las notas para dejarlo en un decimal solo.
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        String formattedVoteAverage = df.format(voteAverage);
        holder.nota.setText(formattedVoteAverage);


        String imageUrl = "https://image.tmdb.org/t/p/w500/" + currentItem.getPosterPath();

        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(holder.portada);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), detalleActivity.class);
            intent.putExtra("id", currentItem.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TextoTitulo;
        ImageView portada;

        TextView nota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextoTitulo = itemView.findViewById(R.id.textViewTituloPelicula);
            portada = itemView.findViewById(R.id.portada);
            nota = itemView.findViewById(R.id.txtviewNota);
        }
    }
}
