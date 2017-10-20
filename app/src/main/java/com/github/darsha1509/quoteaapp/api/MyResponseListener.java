package com.github.darsha1509.quoteaapp.api;

import com.github.darsha1509.quoteaapp.http.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MyResponseListener implements HttpClient.ResponseListener{
    private QuoteList quoteList;
    private Throwable mThrowable;

    @Override
    public void onResponse(final InputStream pInputStream) throws Exception {

        InputStreamReader inputStreamReader = null;

        try{
            inputStreamReader = new InputStreamReader(pInputStream);
            quoteList = new GsonBuilder()
                    .setLenient()
                    .create().fromJson(inputStreamReader, QuoteList.class);
        }finally{
            if(inputStreamReader != null){
                try{
                    inputStreamReader.close();

                } catch( final Exception ignored){}
            }
        }
    }

    @Override
    public void onError(final Throwable pThrowable) {
        mThrowable = pThrowable;
    }

    public QuoteList getQuoteList(){
        return quoteList;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
