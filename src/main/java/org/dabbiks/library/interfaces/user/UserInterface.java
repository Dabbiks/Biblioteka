package org.dabbiks.library.interfaces.user;

import org.dabbiks.Utils;
import org.dabbiks.library.interfaces.user.actions.BorrowBook;
import org.dabbiks.library.interfaces.user.actions.CheckCard;
import org.dabbiks.library.interfaces.user.actions.ReturnBook;
import org.dabbiks.person.User;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    List<String> options = List.of("1", "2", "3");
    String answer = "";

    User user = library.loggedUser;

    public BorrowBook borrowBook = new BorrowBook();
    public CheckCard checkCard = new CheckCard();
    public ReturnBook returnBook = new ReturnBook();

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
            if (answer.equals("1")) borrowBook.borrowBook();
            if (answer.equals("2")) checkCard.checkCard();
            if(answer.equals("3")) returnBook.returnBook();

        }


    }
}
