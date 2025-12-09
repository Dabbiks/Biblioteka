package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Book;
import java.util.Scanner;


import static org.dabbiks.Main.library;

public class CheckCard {

    Scanner scanner = new Scanner(System.in);

    public void checkCard() {

        Utils.clearConsole();

        System.out.println("Dane karty:");
        //Pokazanie danych użytkownika
        System.out.println("Właściciel: " + library.loggedUser.getName() + " " + library.loggedUser.getSurname());
        System.out.println(library.loggedUser.getIdentificator());
        System.out.println("\n--- Lista wypożyczonych książek ---");


        if (library.loggedUser.card.borrowedBooks == null || library.loggedUser.card.borrowedBooks.isEmpty()) {
            System.out.println("(Brak wypożyczeń)");
        } else {
            // Pętla "foreach" - przechodzi przez każdą książkę na liście
            for (Book book : library.loggedUser.card.borrowedBooks) {
                System.out.println("- " + book.title + " (" + book.author + ")");
            }
        }


        System.out.println("\nNaciśnij Enter, aby wrócić do menu...");
        scanner.nextLine();
    }
}