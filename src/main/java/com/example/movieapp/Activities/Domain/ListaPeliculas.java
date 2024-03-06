
package com.example.movieapp.Activities.Domain;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListaPeliculas {

    @SerializedName("data")
    @Expose
    private List<com.example.movieapp.Domain.Datum> data;
    @SerializedName("metadata")
    @Expose
    private com.example.movieapp.Activities.Domain.Metadata metadata;

    public List<com.example.movieapp.Domain.Datum> getData() {
        return data;
    }

    public void setData(List<com.example.movieapp.Domain.Datum> data) {
        this.data = data;
    }

    public com.example.movieapp.Activities.Domain.Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(com.example.movieapp.Activities.Domain.Metadata metadata) {
        this.metadata = metadata;
    }

}
