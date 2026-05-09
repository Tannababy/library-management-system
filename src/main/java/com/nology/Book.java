package com.nology;

import com.nology.user.User;

public class Book {

    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrowedByEmail;
    private int borrowCount;

    // constructor - 2 args
    public Book(int id, String title, String author, boolean isBorrowed, String borrowedByEmail, int borrowCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
        this.borrowedByEmail = "";
        this.borrowCount = borrowCount;
    }


    public int getId() {
        return this.id;
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

    public String getBorrowedByEmail() {
        return borrowedByEmail;
    }

    public void setBorrowedByEmail(String borrowedByEmail) {
        this.borrowedByEmail = borrowedByEmail;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void increaseBorrowCount() {
        this.borrowCount++;
    }


    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Title: " + this.getTitle() + ", Author: " + this.getAuthor() + ", Available: " + !this.isBorrowed();
    }


}