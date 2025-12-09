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

        // Menu ReturnBook
        while (true) {
            Utils.clearConsole();
            System.out.println("Zwrot książki");
            System.out.println("Wybierz książkę do zwrotu (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");

            // Wyświetlenie książek użytkownika
            for (int i = 0; i < library.loggedUser.card.borrowedBooks.size(); i++) {
                Book book = library.loggedUser.card.borrowedBooks.get(i);
                System.out.println((i + 1) + ". " + book.title);
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
                System.out.println("BŁĄD: " + e.getMessage());
                System.out.println("Naciśnij Enter i spróbuj ponownie...");
                scanner.nextLine();
            }
        }
    }
}