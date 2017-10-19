package com.github.darsha1509.quoteaapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dasha.quoteapp.backend.quoteApi.QuoteApi;
import com.example.dasha.quoteapp.backend.quoteApi.model.CollectionResponseQuote;
import com.example.dasha.quoteapp.backend.quoteApi.model.Quote;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    EditText idEditText;
    EditText whoEditText;
    EditText whatEditText;
    Button insertQuoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        idEditText = (EditText) findViewById(R.id.id_edit_text);
        whoEditText = (EditText) findViewById(R.id.who_edit_text);
        whatEditText = (EditText) findViewById(R.id.what_edit_text);
        insertQuoteButton = (Button) findViewById(R.id.insert_quote_button);

        insertQuoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {

                String id = idEditText.getText().toString();
                String author = whoEditText.getText().toString();
                String text = whatEditText.getText().toString();

                new EndpointsAsyncTask().execute(id, author, text);
            }
        });

    }

     class EndpointsAsyncTask extends AsyncTask<String, Void, Quote> {
        private QuoteApi myApiService = null;

        @Override
        protected Quote doInBackground(String ...pVoids) {
            if(myApiService == null) {  // Only do this once
                QuoteApi.Builder builder = new QuoteApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            Long id = Long.parseLong(pVoids[0]);
            String author = pVoids[1];
            String text = pVoids[2];

            try {
                return myApiService.insert(author, id, text).execute();
            } catch (IOException e) {
                e.getMessage();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Quote result) {
            finish();
        }
    }
}
