package com.example.andrewmorrell.bookviewer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import java.util.List;

/**
 * Incredibly simple main activity.
 */
public class BookViewerActivity extends ActionBarActivity {

    private BookViewerFragment mBookViewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_viewer);

        if (savedInstanceState == null) {

            //create the fragment that will display the books
            mBookViewerFragment = BookViewerFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mBookViewerFragment)
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //Get the books from the web service and then populate the fragment
        BooksWebService.getInstance(this).getBooks(new BooksWebService.BooksWebServiceResponse() {
            @Override
            public void onResponse(boolean success, Object response) {
                if (response != null) {
                    List<Book> books = (List<Book>)response;
                    mBookViewerFragment.setBookList(books);
                } else {
                    Toast.makeText(BookViewerActivity.this, "Error loading books", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
