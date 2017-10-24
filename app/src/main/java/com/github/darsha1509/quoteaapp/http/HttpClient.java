package com.github.darsha1509.quoteaapp.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private HttpURLConnection con;

    @Override
    public void requestGet(final String url, final ResponseListener listener) {
        try {
            final InputStream is = openStream(url);
            listener.onResponse(is);
            con.disconnect();
        } catch (final Throwable t) {
            listener.onError(t);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    @Override
    public String requestPost(final String url, final String data) {
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("POST");
            final OutputStream out = new BufferedOutputStream(con.getOutputStream());
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();
            con.connect();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line).append("\n");
            }


            return sb.toString();

        }catch(final Exception e){
            return  null;
        }finally {
            con.disconnect();
        }
    }

    private InputStream openStream(final String url) throws IOException {
        con = (HttpURLConnection) (new URL(url)).openConnection();
        return con.getInputStream();
    }

    public interface ResponseListener {

        void onResponse(InputStream pInputStream) throws Exception;

        void onError(Throwable pThrowable);
    }

}
