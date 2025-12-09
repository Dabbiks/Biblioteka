package org.dabbiks.library.interfaces.employee.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Audiobook;
import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.employee.EmployeeInterface;
import org.dabbiks.person.User;

import java.util.*;

import static org.dabbiks.Main.library;

public class SearchBook {

    Scanner scanner = new Scanner(System.in);

    public void searchItems() {

            List<Book> searchedBooks = new ArrayList<>();
            List<Audiobook> searchedAudiobooks = new ArrayList<>();

            Utils.clearConsole();

            Scanner scanner = new Scanner(System.in);
            System.out.println("WYSZUKIWANIE KSIĄŻEK I AUDIOBOOKÓW");
            System.out.println("Wpisz tytuł lub autora: ");

            // Odczytanie frazy wyszukiwania
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.isEmpty()) {
                System.out.println("Fraza wyszukiwania nie może być pusta.");
                return;
            }

            // Logika przeszukiwania listy zasobów
            for (Book searchBooks : library.books) {
                String title = searchBooks.title.toLowerCase();
                String author = searchBooks.author.toLowerCase();

                // Sprawdzanie, czy fraza pasuje do tytułu lub autora
                if (title.contains(answer) || author.contains(answer)) {
                    searchedBooks.add(searchBooks);
                }
            }

            for (Audiobook searchAudiobooks : library.audiobooks) {
                String title = searchAudiobooks.title.toLowerCase();
                String author = searchAudiobooks.author.toLowerCase();

                // Sprawdzanie, czy fraza pasuje do tytułu lub autora
                if (title.contains(answer) || author.contains(answer)) {
                    searchedAudiobooks.add(searchAudiobooks);
                }
            }

            Utils.clearConsole();

            //Sortowanie ksiązek
            Collections.sort(searchedBooks);

            //Sortowanie audiobookow
            searchedAudiobooks.sort(Comparator.comparing(audiobook -> audiobook.title));

            // Wyświetlanie wyników
            if (searchedBooks.isEmpty()) {
                System.out.println("Brak książek pasujących do frazy: '" + answer + "'");
            } else {
                System.out.println("Znalezione książki: ");
                for (Book foundBooks : searchedBooks) {
                    System.out.println(foundBooks);;
                }
            }

            if (searchedAudiobooks.isEmpty()) {
                System.out.println("Brak audiobooków pasujących do frazy: '" + answer + "'");
            } else {
                System.out.println("Znalezione audiobooki: ");
                for (Audiobook foundAudiobooks : searchedAudiobooks) {
                    System.out.println(foundAudiobooks);;
                }
            }

            String s;

            do {
            System.out.println("\nWpisz '1', aby wyjść");
            s = scanner.nextLine();

            } while (!s.equals("1"));

            EmployeeInterface empInterface = new EmployeeInterface();
            empInterface.gui();

    }
}
