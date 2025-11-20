package org.dabbiks.item;

public class Audiobook extends Item {

    public int recordTime;

    public Audiobook(String title, String author, int releaseDate, Genre genre, int recordTime) {
        super(title, author, releaseDate, genre);
        this.recordTime = recordTime;
    }

}
