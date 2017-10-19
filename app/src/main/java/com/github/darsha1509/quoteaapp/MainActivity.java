package com.github.darsha1509.quoteaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dasha.quoteapp.backend.quoteApi.QuoteApi;
import com.example.dasha.quoteapp.backend.quoteApi.model.CollectionResponseQuote;
import com.example.dasha.quoteapp.backend.quoteApi.model.Quote;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView qoutesTextView;
    Button getQuoteButton;
    Button setQuoteButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qoutesTextView = (TextView) findViewById(R.id.qoutes_text_view);
        getQuoteButton = (Button) findViewById(R.id.btnGetQuotes);
        setQuoteButton = (Button) findViewById(R.id.btnSetQuotes);

        getQuoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                new ReadingEndpointsAsyncTask().execute();
            }
        });

        setQuoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
    }

    class ReadingEndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private  QuoteApi myApiService = null;

        @Override
        protected String doInBackground(final Void... pVoids) {
            if(myApiService == null) {  // Only do this once
                myApiService = ApiBuilder.builApi();
            }


            try {
                //String quote = myApiService.get(1L).execute().getWhat();
                CollectionResponseQuote list = myApiService.list().execute();
                List<Quote> quotes = list.getItems();
                String result = "";
                for(Quote q: quotes){
                    result += q.getId().toString()+". \"" + q.getWhat()+"\"\n"+q.getWho()+"\n";
                }
                return result;
            } catch (final IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(final String result) {
            qoutesTextView.setText(result);
        }
    }
}
