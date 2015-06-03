package com.example.andrewmorrell.bookviewer;

/**
 * Created by andrew on 4/20/15.
 */
public class Book {
    private String title;
    private String author;
    private String imageURL;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }
}
