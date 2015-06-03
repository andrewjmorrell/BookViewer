package com.example.andrewmorrell.bookviewer;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * custom request that parses the books json in the background thread instead of returning it to volley and
 * having the parsing done on the main thread.
 */

public class BooksRequest extends Request<List<Book>> {
    private final Response.Listener<List<Book>> listener;

    public BooksRequest(String url, Response.Listener<List<Book>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(List<Book> response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<List<Book>> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            Gson gson = new Gson();

            Book[] books = gson.fromJson(json, Book[].class);

            List<Book> bookList = Arrays.asList(books);
            return Response.success(
                    bookList,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
