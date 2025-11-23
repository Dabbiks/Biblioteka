package org.dabbiks.item;

public abstract class Item {

    public String title;
    public String author;
    public int releaseDate;
    public Genre genre;

    public Item(String title, String author, int releaseDate, Genre genre) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Tytuł: " + title + ", Autor: " + author + ", Rok wydania: " + releaseDate + ", Gatunek: " + genre.name();
    }

    public abstract String generateFileName();

}
