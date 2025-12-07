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

        while (true) {
            Utils.clearConsole();
            System.out.println("Wypożyczenie książki");
            System.out.println("Wpisz numer książki (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");

            for (int i = 0; i < library.books.size(); i++) {
                Book book = library.books.get(i);
                System.out.println((i + 1) + ". " + book.title + " - " + book.author);
            }

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("X")) {
                return;
            }

            try {
                int bookNumber;
                try {
                    bookNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new LibraryException("To nie jest liczba! Wpisz cyfrę.");
                }

                int index = bookNumber - 1;

                if (index < 0 || index >= library.books.size()) {
                    throw new LibraryException("Nieprawidłowy numer. Taka książka nie istnieje.");
                }

                Book selectedBook = library.books.get(index);

                if (library.loggedUser.card.borrowedBooks == null) {
                    library.loggedUser.card.borrowedBooks = new ArrayList<>();
                }


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