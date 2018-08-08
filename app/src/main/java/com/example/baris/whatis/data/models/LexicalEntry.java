package com.example.baris.whatis.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LexicalEntry {

    @SerializedName("entries")
    @Expose
    private List<Entries> entries = null;
    @SerializedName("lexicalCategory")
    @Expose
    private String lexicalCategory;

    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }

    public String getLexicalCategory() {
        return lexicalCategory;
    }

    public void setLexicalCategory(String lexicalCategory) {
        this.lexicalCategory = lexicalCategory;
    }

}