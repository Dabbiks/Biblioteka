package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;

import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.LibraryException;

import java.util.ArrayList;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class BorrowBook {
    Scanner scanner = new Scanner(System.in);

    public void borrowBook() {
    //Sprawdzenie czy są książki
        if (library.books.isEmpty()) {
            System.out.println("Niestety, biblioteka jest pusta.");
            return;
        }
        //Wybór książki
        Utils.clearConsole();
        System.out.println("=== WYPOŻYCZANIE KSIĄŻKI ===");


        System.out.println("Wpisz tytuł/autora, aby wyszukać (lub wciśnij Enter, aby zobaczyć wszystkie):");

        String searchPhrase = scanner.nextLine().trim().toLowerCase();

        System.out.println("---------------------------------");
        System.out.println("Dostępne książki:");

        boolean foundAny = false;

        for (int i = 0; i < library.books.size(); i++) {
            Book book = library.books.get(i);


            String title = book.title.toLowerCase();
            String author = book.author.toLowerCase();


            if (searchPhrase.isEmpty() || title.contains(searchPhrase) || author.contains(searchPhrase)) {

                System.out.println((i + 1) + ". " + book.title + " - " + book.author);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("(Nie znaleziono książek pasujących do: '" + searchPhrase + "')");
        }

        System.out.println("---------------------------------");
        System.out.println("Wpisz numer książki do wypożyczenia (lub 'X' aby wyjść):");

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

                //Sprawdzenie czy numer mieści się w zakresie
                if (index < 0 || index >= library.books.size()) {
                    throw new LibraryException("Nieprawidłowy numer. Taka książka nie istnieje.");
                }

                Book selectedBook = library.books.get(index);
                if (library.loggedUser.card.borrowedBooks == null) {
                    //Utworzenie nowej listy jeśli jest taka potrzeba
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
