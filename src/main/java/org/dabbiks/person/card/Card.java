package org.dabbiks.person.card;

import org.dabbiks.item.Book;
import org.dabbiks.person.User;

import java.util.ArrayList;
import java.util.List;

import static org.dabbiks.Main.library;

public class Card {

    private final int id;
    private Status status;
    public List<Book> borrowedBooks = new ArrayList<>();
    public Card(int id) {
        this.id     = id;
        this.status = Status.WHITE_CARD;
    }

    public int getId() { return id; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public static int getNextId() {
        int index = 1;
        for (User user : library.users) {
            if (user.card == null || user.card.getId() <= index) continue;
            index = user.card.getId();
        }
        index++;
        return index;
    }

}
