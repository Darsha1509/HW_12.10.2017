package com.github.darsha1509.quoteaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.darsha1509.quoteaapp.api.QuoteApi;
import com.github.darsha1509.quoteaapp.http.HttpClient;

public class InsertActivity extends AppCompatActivity {

    EditText idEditText;
    EditText whoEditText;
    EditText whatEditText;
    Button insertQuoteButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        idEditText = (EditText) findViewById(R.id.id_edit_text);
        whoEditText = (EditText) findViewById(R.id.who_edit_text);
        whatEditText = (EditText) findViewById(R.id.what_edit_text);
        insertQuoteButton = (Button) findViewById(R.id.insert_quote_button);

        insertQuoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String id = idEditText.getText().toString();
                        final String author = whoEditText.getText().toString();
                        final String text = whatEditText.getText().toString();

                        final String data = new QuoteApi().createQuote(id, author, text);
                        new HttpClient().requestPost("https://quoteapp-183210.appspot.com/_ah/api/quoteApi/v1/quote", data);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InsertActivity.this, "New quote is created!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }).start();
            }
        });

    }

}
