package com.example.baris.whatis.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entries {

    @SerializedName("senses")
    @Expose
    private List<Senses> senses = null;

    public Entries(){
    }

    public List<Senses> getSenses() {
        return senses;
    }
    public void setSenses(List<Senses> senses) {
        this.senses = senses;
    }
}