package org.dabbiks.library.interfaces.owner;

import org.dabbiks.Utils;
import org.dabbiks.library.Library;

import org.dabbiks.library.interfaces.LibraryGui;
import org.dabbiks.library.interfaces.owner.actions.GiveBonus;
import org.dabbiks.library.interfaces.owner.actions.ManageSchedule;
import org.dabbiks.library.interfaces.owner.actions.RegisterNewEmployee;

import java.util.Scanner;

import static org.dabbiks.Main.library;


public class OwnerInterface implements LibraryGui {

    public void gui() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Utils.clearConsole();
            System.out.println("Panel właściciela");
            System.out.println("1. Zarejestruj nowego pracownika");
            System.out.println("2. Menu wypłacania premii");
            System.out.println("3. Zarządzaj grafikiem pracownika");
            System.out.println("X. Zamknij program");

            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    new RegisterNewEmployee().registerNewEmployee(library, scanner);
                    break;
                case "2":
                    new GiveBonus().giveBonus(library, scanner);
                    break;
                case "3":
                    new ManageSchedule().manageSchedule(library, scanner);
                    break;
                case "X":
                    return;
                default:
                    System.out.println("Wybierz poprawną opcję.");
            }

        }
    }
}
