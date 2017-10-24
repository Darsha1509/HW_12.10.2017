package com.github.darsha1509.quoteaapp.http;

interface IHttpClient {

    void requestGet(String url, HttpClient.ResponseListener listener);
    String requestPost(String url, String data);

}
