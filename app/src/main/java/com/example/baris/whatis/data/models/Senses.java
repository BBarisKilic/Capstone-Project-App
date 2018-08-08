package com.example.baris.whatis.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Senses {

    @SerializedName("crossReferenceMarkers")
    @Expose
    private List<String> crossReferenceMarkers = null;
    @SerializedName("definitions")
    @Expose
    private List<String> definitions = null;
    @SerializedName("examples")
    @Expose
    private List<Examples> examples = null;
    @SerializedName("domains")
    @Expose
    private List<String> domains = null;


    public List<String> getCrossReferenceMarkers() {
        return crossReferenceMarkers;
    }

    public void setCrossReferenceMarkers(List<String> crossReferenceMarkers) {
        this.crossReferenceMarkers = crossReferenceMarkers;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<Examples> getExamples() {
        return examples;
    }

    public void setExamples(List<Examples> examples) {
        this.examples = examples;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}