
package com.example.movieapp.Activities.Domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Genero {

    private List<itemGeneros> generos;

    public List<itemGeneros> getGeneros() {
        return generos;
    }

    public void setGeneros(List<itemGeneros> generos) {
        this.generos = generos;
    }
}
