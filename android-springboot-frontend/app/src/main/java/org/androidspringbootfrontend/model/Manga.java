package org.androidspringbootfrontend.model;

public class Manga {
    private Long id;
    private String title;
    private String author;
    private int year;
    private boolean status;
    private String url;

    // Constructors, getters, and setters

    public Manga() {
    }

    public Manga(Long id, String title, String author, int year, boolean status, String url) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // This method will return the URL for Picasso
    public String getImageUrl() {
        return this.url;
    }
}
