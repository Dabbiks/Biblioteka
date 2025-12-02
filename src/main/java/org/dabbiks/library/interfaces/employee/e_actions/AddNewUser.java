package org.dabbiks.library.interfaces.employee.e_actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.interfaces.employee.EmployeeInterface;
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

        Utils.clearConsole();
        System.out.println("Krok 2: Wpisz nazwisko: ");
        String surname = scanner.nextLine();

        Utils.clearConsole();

        String pesel = "";
        while (pesel.length() != 11) {
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
}


