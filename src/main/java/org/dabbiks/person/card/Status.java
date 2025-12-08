package org.dabbiks.person.card;

public enum Status {
    RED_CARD("Czerwony"),
    WHITE_CARD("Biały"),
    GREEN_CARD("Zielony");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
