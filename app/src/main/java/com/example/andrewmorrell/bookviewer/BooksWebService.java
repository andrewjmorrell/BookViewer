package com.example.andrewmorrell.bookviewer;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import java.util.List;

/**
 * Created by andrew on 4/20/15.
 *
 * This class uses Volley to fetch the data.  It is a singleton here but these methods could also be
 * static.  The network response is processed in the UI thread which is not ideal.  Given more time I would
 * put the processing into an async task or I would extend JSONArrayRequest to put the processing in the same
 * thread as the network call.
 */
public class BooksWebService {

    private Context mContext;

    private RequestQueue mVolleyQueue;

    private static BooksWebService mInstance;

    private BooksWebService(Context context) {
        mContext = context;
        mVolleyQueue = Volley.newRequestQueue(mContext);
    }

    public static BooksWebService getInstance(Context context)  {
        if (mInstance == null) {
            mInstance = new BooksWebService(context);
        }

        return mInstance;
    }

    /**
     * The call to get the books.
     * @param response
     */
    public void getBooks(final BooksWebServiceResponse response) {
        String url = mContext.getResources().getString(R.string.book_url);

        BooksRequest request = new BooksRequest(url.toString(), new Response.Listener<List<Book>>() {

            @Override
            public void onResponse(List<Book> responseBody) {
                try {

                    try {
                        response.onResponse(true, responseBody);
                    } catch (Exception ex) {
                        if (response != null) {
                            response.onResponse(false, null);
                        }
                    }
                } catch (Exception ex) {
                    //uh-oh.  send a failure response
                    //normally more would be added here
                    if (response != null) {
                        response.onResponse(false, null);
                    }
                }
                mVolleyQueue.getCache().clear();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (response != null) {
                    response.onResponse(false, null);
                }
                mVolleyQueue.getCache().clear();
            }
        });

        request.setShouldCache(false);
        mVolleyQueue.add(request);
    }

    //Callback for the web service
    public interface BooksWebServiceResponse {
        void onResponse(boolean success, Object response);
    }
}
