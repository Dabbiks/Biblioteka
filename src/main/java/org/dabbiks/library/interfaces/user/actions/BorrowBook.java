package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.item.Book;

import java.io.IOException;

import static org.dabbiks.Main.library;
import java.util.Scanner;

public class BorrowBook {
    Scanner scanner = new Scanner(System.in);
    public void borrowBook() {

        // Zabezpieczenie: Jeśli lista książek w bibliotece jest pusta, kończymy działanie.
        if (library.books.isEmpty()) {
            System.out.println("Niestety, w bibliotece nie ma żadnych książek.");
            return;
        }

        // Nieskończona pętla pozwala użytkownikowi próbować do skutku lub zrezygnować (wpisując X)
        while (true) {
            Utils.clearConsole();
            System.out.println("Dostępne książki:");
            System.out.println("Wpisz numer książki, którą chcesz wypożyczyć (lub X aby wyjść):");

            // Wyświetlamy listę książek z numerkami
            for (int i = 0; i < library.books.size(); i++) {
                Book book = library.books.get(i);
                System.out.println((i + 1) + ". " + book.title + " - " + book.author);
            }

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("X")) {
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;

                if (index >= 0 && index < library.books.size()) {
                    Book selectedBook = library.books.get(index);

                    // Dodajemy książkę do karty zalogowanego użytkownika
                    library.loggedUser.card.borrowedBooks.add(selectedBook);
                    System.out.println("Pomyślnie wypożyczono: " + selectedBook.title);

                    // Zapisujemy zmiany
                    try {
                        Data.saveAll(DataType.USER, library.users);
                    } catch (IOException e) {
                        System.out.println("Błąd zapisu danych!");
                    }

                    System.out.println("Naciśnij Enter, aby kontynuować...");
                    scanner.nextLine();
                    return;
                } else {
                    System.out.println("Nieprawidłowy numer. Spróbuj ponownie.");
                    scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba.");
                scanner.nextLine();
            }
        }
    }
}

