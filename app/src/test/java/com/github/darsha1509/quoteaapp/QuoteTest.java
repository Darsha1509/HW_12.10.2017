package com.github.darsha1509.quoteaapp;

import com.github.darsha1509.quoteaapp.api.Quote;
import com.github.darsha1509.quoteaapp.api.QuoteList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QuoteTest {

    @Spy
    private QuoteList mQuoteList;

    @Captor
    ArgumentCaptor<List<Quote>> qouteCaptor;

    @Test
    public void whenNotUseCaptorAnnotation_thenCorrect() {
        Quote quote = mock(Quote.class);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

        quote.setAuthor("Ernest Hemingway");
        verify(quote).setAuthor(arg.capture());

        assertEquals("Ernest Hemingway", arg.getValue());

        quote.setText("I\'m happy one");
        verify(quote).setText(arg.capture());

        doReturn("I\'m happy one").when(quote).getText();

        assertEquals("I\'m happy one", arg.getValue());
    }

    @Test
    public void whenUseCaptorAnnotation_thenTheSam() {
        List<Quote> list = new ArrayList<>();
        list.add(new Quote());
        list.get(0).setAuthor("Ernest Hemingway");
        mQuoteList.setQuoteList(list);
        verify(mQuoteList).setQuoteList(qouteCaptor.capture());

        assertEquals("Ernest Hemingway", qouteCaptor.getValue().get(0).getAuthor());

    }
}
