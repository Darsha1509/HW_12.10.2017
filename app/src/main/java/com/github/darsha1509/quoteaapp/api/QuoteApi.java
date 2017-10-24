package com.github.darsha1509.quoteaapp.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QuoteApi {

    public String createQuote(final String id, final String author, final String text){
        try{
            return  URLEncoder.encode("author", "UTF-8")+"=" + URLEncoder.encode(author, "UTF-8")
                    + "&" +URLEncoder.encode("id", "UTF-8") +"=" + URLEncoder.encode(id, "UTF-8")
                    + "&" + URLEncoder.encode("text", "UTF-8") +"=" + URLEncoder.encode(text, "UTF-8");
        }catch(final UnsupportedEncodingException pE){
            throw new IllegalStateException(pE);
        }
    }
}
