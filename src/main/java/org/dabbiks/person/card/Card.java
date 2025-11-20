package org.dabbiks.person.card;

public class Card {

    private int id;
    private Status status;

    public Card(int id) {
        this.id = id;
        this.status = Status.WHITE_CARD;
    }

    public int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
