package org.dabbiks.item;

public class Book extends Item implements Comparable<Book> {

    public int pages;

    public Book(String title, String author, int releaseDate, Genre genre, int pages) {
        super(title, author, releaseDate, genre);
        this.pages = pages;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        // Książki są równe, jeśli mają ten sam tytuł i autora
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }

    @Override
    public String generateFileName() {
        return title.replaceAll(" ", "");
    }

    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.title);
    }
}
