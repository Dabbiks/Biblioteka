package org.dabbiks.item;

public enum Genre {
    SCIENCE_FICTION("Sci-fi"),
    ROMANCE("Romans"),
    CRIMINAL("Kryminał"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    BIOGRAPHY("Biografia"),
    GUIDE("Poradnik"),
    COMIC("Komiks");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
