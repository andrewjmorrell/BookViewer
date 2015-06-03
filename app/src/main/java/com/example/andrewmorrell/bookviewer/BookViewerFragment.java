package com.example.andrewmorrell.bookviewer;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class BookViewerFragment extends Fragment {

    private RecyclerView mBooksList;
    private ProgressBar mLoadingProgress;
    private BooksRecyclerAdapter mBooksAdapter;


    public static BookViewerFragment newInstance() {
        BookViewerFragment fragment = new BookViewerFragment();
        return fragment;
    }

    public BookViewerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_viewer, container, false);
        mBooksList = (RecyclerView)v.findViewById(R.id.book_list);
        mBooksList.setLayoutManager(new LinearLayoutManager(getActivity()));

        //spinner in the middle of the screen showing that we are loading data
        mLoadingProgress = (ProgressBar) v.findViewById(R.id.progress_bar);
        mLoadingProgress.setVisibility(View.VISIBLE);

        return v;
    }

    /**
     *
     * set the books in the list once they have been loaded
     */
    public void setBookList(List<Book> books) {

        mBooksAdapter = new BooksRecyclerAdapter(getActivity(), books);
        mBooksList.setAdapter(mBooksAdapter);
        mLoadingProgress.setVisibility(View.GONE);
    }
}
