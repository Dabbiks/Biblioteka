package org.dabbiks.library.interfaces.user;

import org.dabbiks.Utils;
import org.dabbiks.person.User;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    List<String> options = List.of("1", "2", "3");
    String answer = "";

    User user = library.loggedUser;

    public void UserInterface() {
        while (!options.contains(answer)) {
            Utils.clearConsole();
            System.out.println("Witaj" + user.getName());
            System.out.println("Wybierz co chcesz zrobic");
            System.out.println("1. Wynajmij książkę");
            System.out.println("2. Sprawdź swoją kartę");
            System.out.println("3. Zwróć książkę");
            System.out.println("4. Wypisz się z biblioteki");
            answer = scanner.nextLine();
            if (answer.equals("1"))

        }


    }
}
