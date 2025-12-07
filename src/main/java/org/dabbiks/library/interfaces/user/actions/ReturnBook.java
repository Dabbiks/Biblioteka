package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.LibraryException;

import java.util.Scanner;

import static org.dabbiks.Main.library;

public class ReturnBook {

    // Scanner do obsługi wejścia od użytkownika
    Scanner scanner = new Scanner(System.in);

    public void returnBook() {

        // Sprawdzenie czy lista wypożyczeń nie jest pusta
        if (library.loggedUser.card.borrowedBooks == null || library.loggedUser.card.borrowedBooks.isEmpty()) {
            System.out.println("Nie masz żadnych wypożyczonych książek.");
            System.out.println("Naciśnij Enter...");
            scanner.nextLine();
            return;
        }

        // Pętla główna pozwala użytkownikowi próbować ponownie w przypadku pomyłki (np. wpisania złego numeru)
        while (true) {
            Utils.clearConsole();
            System.out.println("Zwrot książki");
            System.out.println("Wybierz książkę do zwrotu (lub 'X' aby wyjść):");
            System.out.println("---------------------------------");

            // Wyświetlamy tylko książki, które znajdują się na liście wypożyczeń zalogowanego użytkownika.
            // Używamy numeracji (i + 1), aby użytkownik widział numery od 1.
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
                    // Próba konwersji tekstu na liczbę. Jeśli użytkownik wpisze litery,
                    // blok catch poniżej rzuci nasz własny wyjątek LibraryException.
                    bookNumber = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new LibraryException("To nie jest liczba! Wpisz cyfrę.");
                }

                // Przeliczenie numeru z menu (np. 1) na indeks listy (np. 0)
                int index = bookNumber - 1;

                // Walidacja zakresu: sprawdzamy, czy podany numer faktycznie istnieje na liście
                if (index < 0 || index >= library.loggedUser.card.borrowedBooks.size()) {
                    throw new LibraryException("Nieprawidłowy numer.");
                }

                // --- OPERACJA ZWROTU ---
                // Metoda remove(index) usuwa element z listy w pamięci i jednocześnie go zwraca.
                // Dzięki temu możemy przypisać usuniętą książkę do zmiennej 'removedBook'
                // i wyświetlić jej tytuł w komunikacie o sukcesie.
                Book removedBook = library.loggedUser.card.borrowedBooks.remove(index);

                System.out.println("Sukces! Zwróciłeś książkę: " + removedBook.title);

                System.out.println("Naciśnij Enter, aby kontynuować...");
                scanner.nextLine();
                return; // Wyjście z metody po udanym zwrocie

            } catch (LibraryException e) {
                // Przechwycenie błędów logicznych (zły format, zły numer) i wyświetlenie komunikatu
                System.out.println("BŁĄD: " + e.getMessage());
                System.out.println("Naciśnij Enter i spróbuj ponownie...");
                scanner.nextLine();
            }
        }
    }
}