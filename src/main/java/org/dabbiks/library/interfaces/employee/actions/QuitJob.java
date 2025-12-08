package org.dabbiks.library.interfaces.employee.actions;

import org.dabbiks.Utils;
import org.dabbiks.library.Library;
import org.dabbiks.library.interfaces.employee.EmployeeInterface;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import static org.dabbiks.Main.library;

public class QuitJob {

    Scanner scanner = new Scanner(System.in);

    public void quitJob(Employee loggedEmployee){

        String answer = "";
        List<String> options = List.of("1", "2");

        while (!options.contains(answer)) {
            Utils.clearConsole();
            System.out.println("Czy napewno chcesz złożyć wypowiedzenie?:");
            System.out.println("1. TAK");
            System.out.println("2. NIE. WYJŚCIE");
            answer = scanner.nextLine();
            if (answer.equals("1")) {
                try {
                    quit(loggedEmployee);
                } catch (IOException e){
                    System.out.println("Wystapił błąd " + e.getMessage());
                }
            }
            if (answer.equals("2")) {
                EmployeeInterface empInterface = new EmployeeInterface();
                empInterface.employeeInterface();
            }
        }
    }

    public void quit(Employee employeeToQuit) throws IOException {

            int id = employeeToQuit.getEmployeeId();

            library.employees.remove(employeeToQuit);

            Utils.clearConsole();
            System.out.println("Pracownik o numerze: " + id + " pomyślnie złożył rezygnację");
            String s;

            do {
                System.out.println("Wpisz '1', aby wyjść");
                s = scanner.nextLine();

            } while (!s.equals("1"));

            Library library = new Library();
            library.LobbyInterface();
    }
}
