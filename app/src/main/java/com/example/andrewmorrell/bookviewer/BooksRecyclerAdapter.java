package com.example.andrewmorrell.bookviewer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andrew on 4/20/15.
 */
public class BooksRecyclerAdapter extends RecyclerView.Adapter<BookListRowHolder>{

    private List<Book> bookList;
    private Context context;

    public BooksRecyclerAdapter(Context c, List<Book> books) {
        this.bookList = books;
        this.context = c;
    }

    @Override
    public BookListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, null);
        BookListRowHolder holder = new BookListRowHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BookListRowHolder holder, int position) {
        Book book = bookList.get(position);

        holder.getTitle().setText(book.getTitle());
        if (book.getAuthor() != null) {
            holder.getAuthor().setText(book.getAuthor());
        } else {
            holder.getAuthor().setVisibility(View.GONE);
        }

        Picasso.with(context).load(book.getImageUrl()).into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return null != bookList ? bookList.size() : 0;
    }
}
