package org.dabbiks.item;

public class Book extends Item {

    public int pages;

    public Book(String title, String author, int releaseDate, Genre genre, int pages) {
        super(title, author, releaseDate, genre);
        this.pages = pages;
    }

    @Override
    public String generateFileName() {
        return title.replaceAll(" ", "");
    }
}
