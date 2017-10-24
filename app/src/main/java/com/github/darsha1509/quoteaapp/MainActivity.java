package com.github.darsha1509.quoteaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.darsha1509.quoteaapp.api.MyResponseListener;
import com.github.darsha1509.quoteaapp.api.Quote;
import com.github.darsha1509.quoteaapp.http.HttpClient;

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

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final MyResponseListener listener = new MyResponseListener();
                        new HttpClient().requestGet(Constants.BASE_PATH, listener);
                        if (listener.getThrowable() != null){
                            throw new UnsupportedOperationException(listener.getThrowable());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final List<Quote> quotes = listener.getQuoteList().getQuoteList();
                                String result = "";
                                for(final Quote q: quotes){
                                    result += q.getId() +". \"" + q.getAuthor()+"\"\n"+q.getText()+"\n";
                                }
                                showResult(result);
                            }
                        });
                    }
                }).start();



            }
        });

        setQuoteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
    }

    protected void showResult(final CharSequence result) {
        qoutesTextView.setText(result);

    }

}

