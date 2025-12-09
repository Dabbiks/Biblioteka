package org.dabbiks.library.interfaces.user;

import org.dabbiks.Utils;
import org.dabbiks.library.interfaces.user.actions.*;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public void userInterface() {
        // --- TA PĘTLA JEST KLUCZOWA ---
        // Bez niej, po wykonaniu jednej akcji (np. CheckCard), program dojdzie do końca metody i się wyłączy.
        while (true) {
            Utils.clearConsole();
            System.out.println("=== MENU UŻYTKOWNIKA ===");
            System.out.println("1. Wypożycz książkę");
            System.out.println("2. Zwróć książkę");
            System.out.println("3. Sprawdź kartę");
            System.out.println("4. Usuń konto");
            System.out.println("X. Wyloguj");

            String input = scanner.nextLine();

            switch (input.toUpperCase()) {
                case "1":
                    new BorrowBook().borrowBook();
                    break; // Po zakończeniu wracamy do pętli while
                case "2":
                    new ReturnBook().returnBook();
                    break; // Po zakończeniu wracamy do pętli while
                case "3":
                    new CheckCard().checkCard();
                    break; // Tu właśnie wracasz po wpisaniu 'X' w CheckCard!
                case "4":
                    new Unregister().unregister();
                    break;
                case "X":
                    return; // Dopiero tutaj wychodzimy całkowicie z menu użytkownika
                default:
                    System.out.println("Nieznana opcja.");
            }
        }
    }
}