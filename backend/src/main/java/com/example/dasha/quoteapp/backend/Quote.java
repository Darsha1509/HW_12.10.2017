package com.example.dasha.quoteapp.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Quote {
    @Id
    Long id;
    String author;
    String text;

    public Quote() {}

    public Quote(Long pId, String pAuthor, String pText) {
        id = pId;
        author = pAuthor;
        text = pText;
    }

    public Quote(String pAuthor, String pText) {
        author = pAuthor;
        text = pText;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWho() {
        return author;
    }
    public void setWho(String who) {
        this.author = who;
    }
    public String getWhat() {
        return text;
    }
    public void setWhat(String what) {
        this.text = what;
    }
}
