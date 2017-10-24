package com.github.darsha1509.quoteaapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoteList {

    @SerializedName("items")
    private List<Quote> quoteList;

    public List<Quote> getQuoteList() {
        return quoteList;
    }

}
