package org.dabbiks.library.interfaces.employee.actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.interfaces.ErrorType;
import org.dabbiks.library.interfaces.employee.EmployeeInterface;
import org.dabbiks.library.interfaces.user.UserLogin;
import org.dabbiks.person.User;
import org.dabbiks.person.card.Card;
import java.io.IOException;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class AddNewUser {

    Scanner scanner = new Scanner(System.in);
    Card card;

    public void addUser() {
        try {
            addNewUser();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public void addNewUser() throws IOException {

        Utils.clearConsole();
        System.out.println("DODAWANIE NOWEGO UŻYTKOWNIKA");
        System.out.println("Krok 1: Wpisz imię: ");
        String name = scanner.nextLine();
        while (isCorrect(name, 2, 20, true, false) != ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Proszę wpisać imie! (Od 2 do 20 znaków)");
            name = scanner.nextLine();
        }

        Utils.clearConsole();
        System.out.println("Krok 2: Wpisz nazwisko: ");
        String surname = scanner.nextLine();
        while (isCorrect(surname, 2, 30, true, false) != ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Proszę wpisać nazwisko! (Od 2 do 30 znaków)");
            surname = scanner.nextLine();
        }

        Utils.clearConsole();

        String pesel = "";
        while (isCorrect(pesel, 11, 11, false, true) != ErrorType.NULL){
            Utils.clearConsole();
            System.out.println("Krok 3: Wpisz PESEL (musi mieć 11 cyfr): ");
            pesel = scanner.nextLine();
        }

        Utils.clearConsole();
        card = new Card(Card.getNextId());

        //Dodawanie użytkownika do listy
        User addUser = new User(name, surname, pesel, card);
        library.users.add(addUser);
        Data.saveAll(DataType.USER, library.users);

        Utils.clearConsole();
        System.out.println("Użytkownik " + name + " " + surname
                + " został/a dodany/a do listy użytkowników " +
                "\nNumer karty bibliotecznej to: " + card.getId());
        String s;

        System.out.println("---------------------------------");
        do {
            System.out.println("Wpisz '1', aby wyjść");
            s = scanner.nextLine();

        } while (!s.equals("1"));

        EmployeeInterface empInterface = new EmployeeInterface();
        empInterface.employeeInterface();

    }

    private ErrorType isCorrect(String string, int minimal, int maximal, boolean lettersAllowed, boolean numbersAllowed) {
        if (string.length() < minimal) return ErrorType.TOO_SHORT;
        if (string.length() > maximal) return ErrorType.TOO_LONG;
        if (!string.matches("[a-zA-Z]+") && lettersAllowed && !numbersAllowed) return ErrorType.ONLY_LETTERS;
        if (!string.matches("\\d+") && !lettersAllowed && numbersAllowed) return ErrorType.ONLY_NUMBERS;
        return ErrorType.NULL;
    }
}


