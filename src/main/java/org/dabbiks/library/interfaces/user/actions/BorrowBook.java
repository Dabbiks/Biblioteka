package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;

import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.LibraryException;

import java.util.ArrayList;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class BorrowBook {
    // Obiekt Scanner służy do pobierania danych wpisywanych przez użytkownika w konsoli
    Scanner scanner = new Scanner(System.in);

    public void borrowBook() {
    //Sprawdzamy czy w bibliotece są książki
        if (library.books.isEmpty()) {
            System.out.println("Niestety, biblioteka jest pusta.");
            return;
        }
        // Główna pętla programu - pozwala użytkownikowi próbować wypożyczyć książkę
        // wielokrotnie w przypadku błędu (np. wpisania złego numeru), dopóki nie wybierze 'X'.
        while (true) {
            Utils.clearConsole();
            System.out.println("Wypożyczenie książki");
            System.out.println("Wpisz numer książki (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");
            //Wyświetlamy listę wszystkich książek dodając (i+1) przy wyświetlaniu numeru by numeracja nie zaczynała się od 0
            for (int i = 0; i < library.books.size(); i++) {
                Book book = library.books.get(i);
                System.out.println((i + 1) + ". " + book.title + " - " + book.author);
            }

            String input = scanner.nextLine();
            //Jeśli użytkownik wprowadzi X przerywamy operacje
            if (input.equalsIgnoreCase("X")) {
                return;
            }

            try {
                int bookNumber;
                // Próba konwersji wpisanego tekstu na liczbę całkowitą
                try {
                    bookNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    // Jeśli użytkownik wpisze tekst zamiast liczby, rzucamy wyjątek z komunikatem
                    throw new LibraryException("To nie jest liczba! Wpisz cyfrę.");
                }
                // Zamiana numeru wybranego przez użytkownika (np. 1) na indeks tablicy (np. 0)
                int index = bookNumber - 1;

                //Sprawdzenie, czy podany numer mieści się w zakresie listy książek
                if (index < 0 || index >= library.books.size()) {
                    throw new LibraryException("Nieprawidłowy numer. Taka książka nie istnieje.");
                }
                // Pobranie konkretnego obiektu książki z listy
                Book selectedBook = library.books.get(index);
                // Sprawdzenie, czy użytkownik ma już zainicjalizowaną listę wypożyczonych książek
                if (library.loggedUser.card.borrowedBooks == null) {
                    //Utworzenie nowej listy jeśli jest taka potrzeba
                    library.loggedUser.card.borrowedBooks = new ArrayList<>();
                }

                //Dodanie książki do reszty wypożyczeń na karcie użytkownika.
                library.loggedUser.card.borrowedBooks.add(selectedBook);

                System.out.println("Sukces! Wypożyczyłeś: " + selectedBook.title);


                System.out.println("Naciśnij Enter, aby kontynuować...");
                scanner.nextLine();
                return;

            } catch (LibraryException e) {
                System.out.println("BŁĄD: " + e.getMessage());
                System.out.println("Naciśnij Enter i spróbuj ponownie...");
                scanner.nextLine();
            }
        }
    }
}