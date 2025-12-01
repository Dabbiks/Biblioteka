package org.dabbiks.library.interfaces.employee;

import org.dabbiks.Utils;
import org.dabbiks.library.interfaces.ErrorType;
import org.dabbiks.person.Employee;
import org.dabbiks.person.User;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class EmployeeLogin {

    Scanner scanner = new Scanner(System.in);

    public void employeeInterface() {
        logInToEmployeeAccount();
    }

    private void logInToEmployeeAccount() {
        String index = "";
        String password = "";

        int i = 0;
        while (isCorrect(index, 1, 3, false, true) != ErrorType.NULL || i == 0) {
            System.out.println("Logowanie na konto. Wpisz X, żeby wyjść");
            System.out.println("Krok 1: Wpisz numer pracownika (1-3 znaki)");
            System.out.println("Nr. ... | .....");
            index = scanner.nextLine();

            if (index.equals("X")) {
                library.LobbyInterface();
                return;
            }

            Utils.clearConsole();

            ErrorType errorType = isCorrect(index, 1, 3, false, true);
            if (errorType != ErrorType.NULL) {
                System.out.println(errorType.getString());
                continue;
            }
            i = findCardId(Integer.parseInt(index));
            if (i == 0) {
                System.out.println(ErrorType.NOT_REGISTERED.getString());
                continue;
            }
            index = Integer.toString(i);
        }

        while (isCorrect(password, 10, 32, true, true) != ErrorType.NULL || checkPassword(password, i) == null) {
            System.out.println("Logowanie na konto. Wpisz X, żeby wyjść");
            System.out.println("Krok 2: Wpisz swoje hasło (10-32 znaki)");
            System.out.println("Nr. " + index + "  | .....");
            password = scanner.nextLine();

            if (password.equals("X")) {
                library.LobbyInterface();
                return;
            }

            Utils.clearConsole();

            if (isCorrect(password, 10, 32, true, false) != ErrorType.NULL) {
                System.out.println(isCorrect(password, 10, 32, true, false).getString());
                continue;
            }
            if (checkPassword(password, i) == null)
                System.out.println(ErrorType.WRONG_PASSWORD.getString());
        }

        System.out.println("Nr. " + index + " | " + "**********");
        System.out.println("Jesteś teraz zalogowany");

        System.out.println(" ");
        library.loggedEmployee = checkPassword(password, i);
        if (library.loggedEmployee == null) throw new RuntimeException();
    }

    private ErrorType isCorrect(String string, int minimal, int maximal, boolean lettersAllowed, boolean numbersAllowed) {
        if (string.length() < minimal) return ErrorType.TOO_SHORT;
        if (string.length() > maximal) return ErrorType.TOO_LONG;
        if (!string.matches("[a-zA-Z]+") && lettersAllowed && !numbersAllowed) return ErrorType.ONLY_LETTERS;
        if (!string.matches("\\d+") && !lettersAllowed && numbersAllowed) return ErrorType.ONLY_NUMBERS;
        return ErrorType.NULL;
    }

    private int findCardId(int index) {
        for (Employee employee : library.employees) {
            if (index == employee.getEmployeeId()) {
                return employee.getEmployeeId();
            }
        }
        return 0;
    }

    private Employee checkPassword(String password, int index) {
        for (Employee employee : library.employees) {
            if (employee.getPassword().equals(password) && employee.getEmployeeId() == index)
                return employee;
        }
        return null;
    }

}
