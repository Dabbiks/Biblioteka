package org.dabbiks.library.interfaces.owner.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.Scanner;

public class RegisterNewEmployee {
    public void registerNewEmployee(Library library, Scanner scanner) {
        Utils.clearConsole();
        System.out.println("Rejestrowanie nowego pracownika:");

        System.out.println("Podaj imię: ");
        String name = scanner.nextLine();

        System.out.println("Podaj nazwisko:");
        String surname = scanner.nextLine();

        System.out.println("Podaj PESEL:");
        String pesel = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        int newID = library.employees.size() + 1;

        Employee newEmployee = new Employee(name, surname, pesel, password, newID, false);
        library.employees.add(newEmployee);

        try {
            Data.saveAll(DataType.EMPLOYEE, library.employees);
            System.out.println("Pomyślnie dodano nowego pracownika: " + name + " " + surname);
            System.out.println("ID Pracownika: " + newID);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania danych: " + e.getMessage());
        }
        System.out.println("Naciśnij ENTER, aby kontynuować...");
        scanner.nextLine();
    }
}
