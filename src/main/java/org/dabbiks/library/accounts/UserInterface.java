package org.dabbiks.library.accounts;

import org.dabbiks.Utils;
import org.dabbiks.person.User;
import org.dabbiks.person.card.Card;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    public void userInterface() {
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

        if (answer.equals("1")) logInToUserAccount();
        if (answer.equals("2")) registerUserAccount();
    }

    private void registerUserAccount() {
        String name = "";
        String surname = "";
        String pesel = "";

        while (isCorrect(name, 2, 20, true, false) != ErrorType.NULL) {
            System.out.println("Rejestracja konta. Wpisz X, żeby wyjść");
            System.out.println("Krok 1: Wpisz swoje imię (2-20 znaków)");
            System.out.println("..... | ..... | .....");
            name = scanner.nextLine();
            Utils.clearConsole();
            if (isCorrect(name, 2, 20, true, false) != ErrorType.NULL)
                System.out.println(isCorrect(name, 2, 20, true, false).getString());
        }

        while (isCorrect(surname, 2, 20, true, false) != ErrorType.NULL) {
            System.out.println("Rejestracja konta. Wpisz X, żeby wyjść");
            System.out.println("Krok 2: Wpisz swoje nazwisko (2-20 znaków)");
            System.out.println(name + " | ..... | .....");
            surname = scanner.nextLine();
            Utils.clearConsole();
            if (isCorrect(surname, 2, 20, true, false) != ErrorType.NULL)
                System.out.println(isCorrect(surname, 2, 20, true, false).getString());
        }

        while (!User.canRegister(pesel) || isCorrect(pesel, 11, 11, false, true) != ErrorType.NULL) {
            System.out.println("Rejestracja konta. Wpisz X, żeby wyjść");
            System.out.println("Krok 3: Wpisz swój pesel (11 znaków)");
            System.out.println(name + " | " + surname + " | .....");
            pesel = scanner.nextLine();
            Utils.clearConsole();
            if (!User.canRegister(pesel)) System.out.println(ErrorType.ALREADY_REGISTERED);
            if (isCorrect(pesel, 11, 11, false, true) != ErrorType.NULL)
                System.out.println(isCorrect(pesel, 11, 11, false, true).getString());
        }

        int cardId = Card.getNextId();
        Card card = new Card(cardId);
        User user = new User(name, surname, pesel, card);
        System.out.println(name + " | " + surname + " | " + pesel);
        System.out.println("Rejestracja ukończona pomyślnie");
        System.out.println("Twoja karta ma nr. " + cardId);
        System.out.println(" ");
        library.users.add(user);
        logInToUserAccount();
    }

    private void logInToUserAccount() {
        String name = "";
        String index = "";

        while (isCorrect(name, 2, 20, true, false) != ErrorType.NULL || findName(name).isEmpty()) {
            System.out.println("Logowanie na konto. Wpisz X, żeby wyjść");
            System.out.println("Krok 1: Wpisz swoje imię (2-20 znaków)");
            System.out.println("..... | Nr. ... ");
            name = scanner.nextLine();
            Utils.clearConsole();
            if (isCorrect(name, 2, 20, true, false) != ErrorType.NULL) {
                System.out.println(isCorrect(name, 2, 20, true, false).getString());
                continue;
            }
            if (findName(name).isEmpty())
                System.out.println(ErrorType.NOT_REGISTERED.getString());
        }

        int i = 0;
        while (isCorrect(index, 1, 3, false, true) != ErrorType.NULL || i == 0) {
            System.out.println("Logowanie na konto. Wpisz X, żeby wyjść");
            System.out.println("Krok 2: Wpisz numer karty bibliotecznej (1-3 znaki)");
            System.out.println(name + " | Nr. ... ");
            index = scanner.nextLine();
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

        System.out.println(name + " | " + "Nr. " + index);
        System.out.println("Jesteś teraz zalogowany");
        System.out.println(" ");
    }

    private ErrorType isCorrect(String string, int minimal, int maximal, boolean lettersAllowed, boolean numbersAllowed) {
        if (string.length() < minimal) return ErrorType.TOO_SHORT;
        if (string.length() > maximal) return ErrorType.TOO_LONG;
        if (!string.matches("[a-zA-Z]+") && lettersAllowed && !numbersAllowed) return ErrorType.ONLY_LETTERS;
        if (!string.matches("\\d+") && !lettersAllowed && numbersAllowed) return ErrorType.ONLY_NUMBERS;
        return ErrorType.NULL;
    }

    private String findName(String string) {
        for (User user : library.users) {
            if (string.equals(user.getName())) return user.getName();
        }
        return "";
    }

    private int findCardId(int index) {
        for (User user : library.users) {
            if (index == user.card.getId()) {
                return user.card.getId();
            }
        }
        return 0;
    }

}
