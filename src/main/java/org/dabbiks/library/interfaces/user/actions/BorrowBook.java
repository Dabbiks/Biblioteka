package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.item.Book;
// Import Twojego wyjątku
import org.dabbiks.library.interfaces.LibraryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class BorrowBook {

    Scanner scanner = new Scanner(System.in);

    public void borrowBook() {

        // 1. Sprawdzenie czy są książki
        if (library.books.isEmpty()) {
            System.out.println("Niestety, biblioteka jest pusta.");
            return;
        }

        while (true) {
            Utils.clearConsole();
            System.out.println("Wypożyczenie książki");
            System.out.println("Wpisz numer książki (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");

            // 2. Wyświetlanie listy (z numeracją od 1)
            for (int i = 0; i < library.books.size(); i++) {
                Book book = library.books.get(i);
                System.out.println((i + 1) + ". " + book.title + " - " + book.author);
            }

            String input = scanner.nextLine();

            // 3. Wyjście z programu
            if (input.equalsIgnoreCase("X")) {
                return;
            }

            try {
                //Próba zamiany tekstu na liczbę
                int bookNumber;
                try {
                    bookNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    // Jeśli wpisano litery zamiast cyfr wyświetla się błąd
                    throw new LibraryException("To nie jest liczba! Wpisz cyfrę.");
                }

                //Zamiana numeru na indeks (komputer liczy od 0)
                int index = bookNumber - 1;

                //Sprawdzenie czy numer mieści się w liście
                if (index < 0 || index >= library.books.size()) {
                    throw new LibraryException("Nieprawidłowy numer. Taka książka nie istnieje.");
                }

                //Dane są poprawne

                Book selectedBook = library.books.get(index);

                // Zabezpieczenie: Jeśli użytkownik nie ma jeszcze listy (stary plik JSON), tworzymy ją
                if (library.loggedUser.card.borrowedBooks == null) {
                    library.loggedUser.card.borrowedBooks = new ArrayList<>();
                }

                // Dodanie do listy
                library.loggedUser.card.borrowedBooks.add(selectedBook);
                System.out.println("Sukces! Wypożyczyłeś: " + selectedBook.title);

                // Zapis do pliku
                try {
                    Data.saveAll(DataType.USER, library.users);
                } catch (IOException e) {
                    System.out.println("Błąd zapisu danych.");
                }

                System.out.println("Naciśnij Enter, aby kontynuować...");
                scanner.nextLine();
                return;

            } catch (LibraryException e) {
                // Obsługa naszych błędów logicznych (zły numer, zły format)
                System.out.println("BŁĄD: " + e.getMessage());
                System.out.println("Naciśnij Enter i spróbuj ponownie...");
                scanner.nextLine();
            }
        }
    }
}

