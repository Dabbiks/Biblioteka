package org.dabbiks.library.interfaces.owner.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;
import org.dabbiks.library.interfaces.ErrorType;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.Scanner;

public class RegisterNewEmployee {
    public void registerNewEmployee(Library library, Scanner scanner) {
        Utils.clearConsole();
        System.out.println("Rejestrowanie nowego pracownika:");

        System.out.println("Podaj imię: ");
        String name = scanner.nextLine();
        while (isCorrect(name, 2, 20, true, false) !=ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Podaj imię (od 2 do 20 znaków):");
            name = scanner.nextLine();
        }

        Utils.clearConsole();
        System.out.println("Podaj nazwisko:");
        String surname = scanner.nextLine();
        while (isCorrect(surname, 2, 30, true, false) !=ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Podaj nazwisko (od 2 do 30 znaków):");
            surname = scanner.nextLine();
        }

        Utils.clearConsole();
        System.out.println("Podaj PESEL:");
        String pesel = scanner.nextLine();
        while(isCorrect(pesel, 11, 11, false, true) !=ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Podaj PESEL (11 cyfr):");
            pesel = scanner.nextLine();
        }
        Utils.clearConsole();
        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();


        int newID = library.employees.size() + 1;

        Employee newEmployee = new Employee(name, surname, pesel, password, newID, false);
        library.employees.add(newEmployee);

        System.out.println("Pomyślnie dodano nowego pracownika: " + name + " " + surname);
        System.out.println("ID Pracownika: " + newID);
        System.out.println("Naciśnij ENTER, aby kontynuować...");
        scanner.nextLine();
    }
    private ErrorType isCorrect(String string, int minimal, int maximal, boolean lettersAllowed, boolean numbersAllowed) {
        if (string.length() < minimal) return ErrorType.TOO_SHORT;
        if (string.length() > maximal) return ErrorType.TOO_LONG;
        if (!string.matches("[a-zA-Z]+") && lettersAllowed && !numbersAllowed) return ErrorType.ONLY_LETTERS;
        if (!string.matches("\\d+") && !lettersAllowed && numbersAllowed) return ErrorType.ONLY_NUMBERS;
        return ErrorType.NULL;
    }
}
