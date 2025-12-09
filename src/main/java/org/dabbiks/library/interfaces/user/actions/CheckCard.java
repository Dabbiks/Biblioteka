package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.Utils;
import org.dabbiks.item.Book;
import org.dabbiks.item.Item; // Import do Upcastingu
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class CheckCard {

    Scanner scanner = new Scanner(System.in);

    public void checkCard() {
        // Pętla główna metody
        while (true) {
            Utils.clearConsole();
            System.out.println("Dane karty:");

            System.out.println("Właściciel: " + library.loggedUser.getName() + " " + library.loggedUser.getSurname());
            System.out.println(library.loggedUser.getIdentificator());

            System.out.println("\n--- Lista wypożyczonych książek ---");

            if (library.loggedUser.card.borrowedBooks == null || library.loggedUser.card.borrowedBooks.isEmpty()) {
                System.out.println("(Brak wypożyczeń)");
            } else {
                for (Book book : library.loggedUser.card.borrowedBooks) {
                    // TU JEST UPCASTING: Przekazujemy 'Book', metoda odbiera 'Item'
                    wyswietlTytul(book);
                }
            }

            // Te instrukcje MUSZĄ być wewnątrz pętli while (przed jej zamknięciem)
            System.out.println("\nWpisz 'X' i naciśnij Enter, aby wrócić do menu...");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("X")) {
                return;
            }
        }
    }


    private void wyswietlTytul(Item item) {
        System.out.println("- " + item.title + " (" + item.author + ")");
    }
}
