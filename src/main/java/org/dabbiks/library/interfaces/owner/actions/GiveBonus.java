package org.dabbiks.library.interfaces.owner.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.Scanner;

public class GiveBonus {
    public void giveBonus(Library library, Scanner scanner) {
        Utils.clearConsole();
        System.out.println("Wypłacanie premii dla pracownika:");

        System.out.println("Lista pracownikow:");
        for (Employee emp : library.employees) {
            System.out.println("#" + emp.getEmployeeId() + " " + emp.getName() + " " + emp.getSurname() + " | Obecna premia: " + emp.getTotalBonus() + " zł");
        }
        System.out.println("Wybierz pracownika, któremu chcesz wypłacić premię: ");
        String idString = scanner.nextLine();

        int bonusToFind;
        try {
            bonusToFind = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            System.out.println("Błąd! ID musi być liczbą!");
            scanner.nextLine();
            return;
        }
        Employee foundID = null;
        for (Employee emp : library.employees) {
            if (emp.getEmployeeId() == bonusToFind) {
                foundID = emp;
                break;
            }
        }
        if (foundID == null) {
            System.out.println("Nie odnaleziono pracownika od ID: " + bonusToFind);
            scanner.nextLine();
            return;
        }

        System.out.println("Podaj kwotę premii dla " + foundID.getName() + ":");
        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Kwota musi być większa od zera!");
            } else {
                foundID.addBonus(amount);
                Data.saveAll(DataType.EMPLOYEE, library.employees);
                System.out.println("Przyznano " + amount + " zł premii");
                System.out.println("Całkowita premia: " + foundID.getTotalBonus() + " zł premii.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Błąd! Wpisz poprawną kwotę(150.00).");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania danych");
        }
        System.out.println("Naciśnij ENTER, aby wrócić...");
        scanner.nextLine();
    }
}
