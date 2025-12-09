package org.dabbiks.library.interfaces.owner;

import org.dabbiks.library.Library;

import java.util.Scanner;


public class OwnerLogin {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public void login(Library library){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Logowanie właściciela:");
            System.out.println("Podaj login:");
            String login = scanner.nextLine();

            System.out.println("Podaj hasło:");
            String password = scanner.nextLine();

            if (ADMIN_LOGIN.equals(login) && ADMIN_PASSWORD.equals(password)){
                System.out.println("Pomyślnie zalogowano!");
                OwnerInterface ownerInterface = new OwnerInterface();
                ownerInterface.ownerInterface(library);
            }else{
                System.out.println("Niepoprawne dane logowania!");
        }

        }
    }
}
