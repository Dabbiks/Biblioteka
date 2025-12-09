package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;

import org.dabbiks.item.Book;
import org.dabbiks.library.LibraryException;

import java.util.ArrayList;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class BorrowBook {
    // Dodajemy scanner do pobierania danych
    Scanner scanner = new Scanner(System.in);

    public void borrowBook() {
    //Sprawdzamy czy w bibliotece są książki
        if (library.books.isEmpty()) {
            System.out.println("Niestety, biblioteka jest pusta.");
            return;
        }
        //Główna pętla odpowiadająca za wybór książki
        Utils.clearConsole();
        System.out.println("=== WYPOŻYCZANIE KSIĄŻKI ===");


        System.out.println("Wpisz tytuł/autora, aby wyszukać (lub wciśnij Enter, aby zobaczyć wszystkie):");

        String searchPhrase = scanner.nextLine().trim().toLowerCase();

        System.out.println("---------------------------------");
        System.out.println("Dostępne książki:");

        boolean foundAny = false; // Sprawdzamy, czy cokolwiek znaleźliśmy

        //Pętla wyświetlająca tylko pasujące książki
        for (int i = 0; i < library.books.size(); i++) {
            Book book = library.books.get(i);

            // Pobieramy dane książki i zamieniamy na małe litery
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
