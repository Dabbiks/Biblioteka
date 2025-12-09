package org.dabbiks.library.interfaces.user;

import org.dabbiks.Utils;
import org.dabbiks.library.interfaces.LibraryGui;
import org.dabbiks.library.interfaces.user.actions.*;
import java.util.Scanner;

public class UserInterface implements LibraryGui {

    Scanner scanner = new Scanner(System.in);

    public void gui() {
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
                    break;
                case "2":
                    new ReturnBook().returnBook();
                    break;
                case "3":
                    new CheckCard().checkCard();
                    break;
                case "4":
                    new Unregister().unregister();
                    break;
                case "X":
                    { Utils.saveAllData(); System.exit(0); }
                default:
                    System.out.println("Nieznana opcja.");
            }
        }
    }
}