package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.LibraryException;

import java.util.Scanner;

import static org.dabbiks.Main.library;

public class ReturnBook {

    // Scanner pobierający informacje
    Scanner scanner = new Scanner(System.in);

    public void returnBook() {

        // Sprawdzenie czy lista wypożyczeń nie jest pusta
        if (library.loggedUser.card.borrowedBooks == null || library.loggedUser.card.borrowedBooks.isEmpty()) {
            System.out.println("Nie masz żadnych wypożyczonych książek.");
            System.out.println("Naciśnij Enter...");
            scanner.nextLine();
            return;
        }

        // Pętla główna
        while (true) {
            Utils.clearConsole();
            System.out.println("Zwrot książki");
            System.out.println("Wybierz książkę do zwrotu (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");

            // Wyświetlamy książki, które znajdują się na liście wypożyczeń zalogowanego użytkownika.
            for (int i = 0; i < library.loggedUser.card.borrowedBooks.size(); i++) {
                Book book = library.loggedUser.card.borrowedBooks.get(i);
                System.out.println((i + 1) + ". " + book.title);
            }

            String input = scanner.nextLine();

            // Obsługa wyjścia z menu bez zwracania książki
            if (input.equalsIgnoreCase("X")) {
                return;
            }

            try {
                int bookNumber;
                try {
                    // Próba konwersji tekstu na liczbę.

                    bookNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new LibraryException("To nie jest liczba! Wpisz cyfrę.");
                }


                int index = bookNumber - 1;

                // Sprawdzenie czy podany numer istnieje na liście
                if (index < 0 || index >= library.loggedUser.card.borrowedBooks.size()) {
                    throw new LibraryException("Nieprawidłowy numer.");
                }

                //Operacja zwrotu książki
                Book removedBook = library.loggedUser.card.borrowedBooks.remove(index);

                System.out.println("Sukces! Zwróciłeś książkę: " + removedBook.title);

                System.out.println("Naciśnij Enter, aby kontynuować...");
                scanner.nextLine();
                return;

            } catch (LibraryException e) {
                // Przechwycenie błędów logicznych (zły format, zły numer) i wyświetlenie komunikatu
                System.out.println("BŁĄD: " + e.getMessage());
                System.out.println("Naciśnij Enter i spróbuj ponownie...");
                scanner.nextLine();
            }
        }
    }
}