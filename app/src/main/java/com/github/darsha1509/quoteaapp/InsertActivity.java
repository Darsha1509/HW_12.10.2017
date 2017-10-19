package com.github.darsha1509.quoteaapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dasha.quoteapp.backend.quoteApi.QuoteApi;
import com.example.dasha.quoteapp.backend.quoteApi.model.Quote;

import java.io.IOException;

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

                new InsertEndpointsAsyncTask().execute(id, author, text);
            }
        });

    }

     class InsertEndpointsAsyncTask extends AsyncTask<String, Void, Quote> {
        private QuoteApi myApiService = null;

        @Override
        protected Quote doInBackground(String ...pVoids) {
            if(myApiService == null) {  // Only do this once
                myApiService = ApiBuilder.builApi();
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
