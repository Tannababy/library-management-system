package com.nology;

import com.nology.user.User;

public class Book {

    private String title;
    private String author;
    private boolean isBorrowed;
    private User borrowedBy;

    // constructor - 2 args
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }


    @Override
    public String toString() {
        return "com.nology.Book: " +  this.title + ", Author: " + this.author;
    }
}