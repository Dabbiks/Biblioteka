package org.dabbiks.library.interfaces.owner.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.interfaces.owner.EmployeeNotFoundException;
import org.dabbiks.library.Library;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManageSchedule {

    public static final List<String> VALID_DAYS = Arrays.asList(
           "poniedziałek", "wtorek", "środa", "czwartek", "piatek"
    );
    private String hours;

    public void manageSchedule(Library library, Scanner scanner) {
        Utils.clearConsole();
        System.out.println("Zarządzanie grafikiem pracowników:");
        System.out.println("Podaj ID pracownika");
        String idString = scanner.nextLine();

        try {
            int idToFind = Integer.parseInt(idString);
            Employee emp = findEmployeeOrThrow(library, idToFind);
            System.out.println("Grafik pracownika: " + emp.getName() + " " + emp.getSurname());
            for (Map.Entry<String, String> entry : emp.getWorkSchedule().entrySet()) {
                System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Czy chcesz zmienić godziny? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                String day;
                String hours;

                do{
                    System.out.println("Podaj dzień tygodnia: ");
                    day = scanner.nextLine().toLowerCase();
                    if (!VALID_DAYS.contains(day)){
                        System.out.println("Wprowadź poprawny dzień tygodnia.");
                    }
                }while (!VALID_DAYS.contains(day));
                do{
                    System.out.println("Podaj godziny (8:00-16:00 lub Wolne): ");
                    hours = scanner.nextLine();
                    if (!validHoursFormat(hours)){
                        System.out.println("Wprowadź poprawnie godziny");
                }
                }while (!validHoursFormat(hours));


                emp.setShift(day, hours);

                Data.saveAll(DataType.EMPLOYEE, library.employees);
                System.out.println("Zaktualizowano grafik pracownika");

            }
        } catch (NumberFormatException e) {
            System.out.println("Błąd! ID musi być liczbą");
        } catch (EmployeeNotFoundException e) {
            System.out.println("Błąd operacji: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania danych");
        }
        System.out.println("Naciśnij ENTER, aby wrócić...");
        scanner.nextLine();
    }

    private boolean validHoursFormat(String hours){
        if (hours.trim().equalsIgnoreCase("Wolne")){
            return true;
        }
        String timeFormat = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])-(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$";
        return hours.matches(timeFormat);
    }

    private Employee findEmployeeOrThrow(Library library, int id) throws EmployeeNotFoundException {
        for (Employee emp : library.employees) {
            if (emp.getEmployeeId() == id) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException("Nie odnaleziono w bazie pracownika od ID: " + id);
    }
}
