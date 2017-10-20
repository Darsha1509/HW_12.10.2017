package com.github.darsha1509.quoteaapp.api;

import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("id")
    private long id;

    @SerializedName("who")
    private String author;

    @SerializedName("what")
    private String text;

    public long getId() {
        return id;
    }

    public void setId(final long pId) {
        id = pId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String pAuthor) {
        author = pAuthor;
    }

    public String getText() {
        return text;
    }

    public void setText(final String pText) {
        text = pText;
    }
}
