package org.dabbiks.library.accounts;

import org.dabbiks.Utils;
import org.dabbiks.library.Library;
import org.dabbiks.person.User;
import org.dabbiks.person.card.Card;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class UserAccount {

    Scanner scanner = new Scanner(System.in);

    public void UserInterface() {
        List<String> accountTypes = List.of("1", "2");
        String answer = "";

        while (!accountTypes.contains(answer)) {
            System.out.println("Wpisz odpowiednią cyfrę w konsoli, żeby przejść dalej");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Załóż konto");
            System.out.println("...............");
            answer = scanner.nextLine();
            Utils.clearConsole();
        }

        if (answer.equals("1")) LogInToUserAccount();
        if (answer.equals("2")) RegisterUserAccount();
    }

    private void RegisterUserAccount() {
        String name = "";
        String surname = "";
        String pesel = "";

        String tooShortError = "⚠ Wpisane przez Ciebie dane miały zbyt mało znaków";
        String tooLongError = "⚠ Wpisane przez Ciebie dane miały zbyt wiele znaków";
        String shouldUseLettersError = "⚠ Możesz używać jedynie liter od A do Z";
        String shouldUseNumbersError = "⚠ Możesz używać jedynie liter od A do Z";
        String alreadyRegisteredError = "⚠ Twoje konto jest już zarejestrowane! Przekierowano do logowania";

        while (checkNameAndSurnameCompatibility(name) > 0) {
            System.out.println("..... | ..... | .....");
            System.out.println("Krok 1: Wpisz swoje imię");
            name = scanner.nextLine();
            Utils.clearConsole();
            if (checkNameAndSurnameCompatibility(name) == 1) System.out.println(tooShortError);
            if (checkNameAndSurnameCompatibility(name) == 2) System.out.println(tooLongError);
            if (checkNameAndSurnameCompatibility(name) == 3) System.out.println(shouldUseLettersError);
        }

        while (checkNameAndSurnameCompatibility(surname) > 0) {
            System.out.println(name + " | ..... | .....");
            System.out.println("Krok 2: Wpisz swoje nazwisko");
            surname = scanner.nextLine();
            Utils.clearConsole();
            if (checkNameAndSurnameCompatibility(surname) == 1) System.out.println(tooShortError);
            if (checkNameAndSurnameCompatibility(surname) == 2) System.out.println(tooLongError);
            if (checkNameAndSurnameCompatibility(surname) == 3) System.out.println(shouldUseLettersError);
        }

        while (pesel.isEmpty() || !User.canRegister(pesel) || checkPeselCompatibility(pesel) != 0) {
            System.out.println(name + " | " + surname + " | .....");
            System.out.println("Krok 3: Wpisz swój pesel");
            pesel = scanner.nextLine();
            Utils.clearConsole();
            if (!User.canRegister(pesel)) System.out.println(alreadyRegisteredError);
            if (checkPeselCompatibility(pesel) == 1) System.out.println(tooShortError);
            if (checkPeselCompatibility(pesel) == 2) System.out.println(tooLongError);
            if (checkPeselCompatibility(pesel) == 3) System.out.println(shouldUseNumbersError);
        }

        Card card = new Card(1); //TODO
        User user = new User(name, surname, pesel, card);
        System.out.println(name + " | " + surname + " | " + pesel);
        System.out.println("Rejestracja ukończona pomyślnie");
        System.out.println("Wciśnij dowolny przycisk, żeby przejść do logowania");
        library.users.add(user);
    }

    private int checkNameAndSurnameCompatibility(String string) {
        if (string.length() <= 1) return 1;
        if (string.length() >= 21) return 2;
        if (!string.matches("[a-zA-Z]+")) return 3;
        return 0;
    }

    private int checkPeselCompatibility(String string) {
        if (string.length() < 11) return 1;
        if (string.length() > 11) return 2;
        if (!string.matches("\\d+")) return 3;
        return 0;
    }

    private void LogInToUserAccount() {

    }

}
