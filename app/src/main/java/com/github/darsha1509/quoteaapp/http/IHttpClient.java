package com.github.darsha1509.quoteaapp.http;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);

}
