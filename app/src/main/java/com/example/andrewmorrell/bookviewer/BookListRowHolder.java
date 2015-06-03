package com.example.andrewmorrell.bookviewer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by andrew on 4/20/15.
 */
public class BookListRowHolder extends RecyclerView.ViewHolder {

    protected View container;

    private TextView title;
    private TextView author;
    private ImageView image;

    public BookListRowHolder(View bookView) {
        super(bookView);

        container = bookView;
        title = (TextView) bookView.findViewById(R.id.book_title);
        author = (TextView) bookView.findViewById(R.id.book_author);
        image = (ImageView) bookView.findViewById(R.id.book_image);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getAuthor() {
        return author;
    }

    public ImageView getImage() {
        return image;
    }
}
