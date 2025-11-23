package org.dabbiks.item;

import org.dabbiks.data.Data;

public class Audiobook extends Item {

    public int recordTime;

    public Audiobook(String title, String author, int releaseDate, Genre genre, int recordTime) {
        super(title, author, releaseDate, genre);
        this.recordTime = recordTime;

    }

    @Override
    public String generateFileName() {
        return title.replaceAll(" ", "");
    }
}
