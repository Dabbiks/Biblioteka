package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Book;
import java.util.Scanner;

// Importujemy bibliotekę, żeby mieć dostęp do 'library.loggedUser'
import static org.dabbiks.Main.library;

public class CheckCard {

    Scanner scanner = new Scanner(System.in);

    public void checkCard() {
        // Czyścimy konsolę dla czytelności
        Utils.clearConsole();
        System.out.println("=== TWOJA KARTA BIBLIOTECZNA ===");

        // Wyświetlamy podstawowe dane użytkownika
        // Metody getName() i getSurname() mamy dzięki dziedziczeniu po klasie Person
        System.out.println("Właściciel: " + library.loggedUser.getName() + " " + library.loggedUser.getSurname());

        // Wyświetlamy ID i status karty (metoda getIdentificator jest w klasie User)
        System.out.println(library.loggedUser.getIdentificator());

        System.out.println("\n--- Lista wypożyczonych książek ---");

        // Sprawdzamy, czy lista książek w ogóle istnieje (czy nie jest nullem)
        if (library.loggedUser.card.borrowedBooks == null || library.loggedUser.card.borrowedBooks.isEmpty()) {
            System.out.println("(Brak wypożyczeń)");
        } else {
            // Pętla "foreach" - przechodzi przez każdą książkę na liście

            for (Book book : library.loggedUser.card.borrowedBooks) {
                System.out.println("- " + book.title + " (" + book.author + ")");
            }
        }

        // Czekamy na reakcję użytkownika przed powrotem do menu
        System.out.println("\nNaciśnij Enter, aby wrócić do menu...");
        scanner.nextLine();
    }
}