package org.dabbiks.library.interfaces.user.actions;

import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;

import java.io.IOException;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class Unregister {

    Scanner scanner = new Scanner(System.in);

    public void unregister() {
        System.out.println("Czy na pewno chcesz usunąć konto?");
        System.out.println("Wpisz 'TAK' aby potwierdzić, lub cokolwiek innego aby anulować:");

        String decision = scanner.nextLine();

        // Sprawdzamy, czy użytkownik wpisał dokładnie "TAK"
        if (decision.equalsIgnoreCase("TAK")) {

            //Usuwamy użytkownika z listy
            library.users.remove(library.loggedUser);

            //Aktualizacja plików
            try {
                //Kasujemy stare pliki
                Data.clearAll(DataType.USER);
                //Zapisujemy nową listę
                Data.saveAll(DataType.USER, library.users);

                System.out.println("Twoje konto zostało usunięte.");
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas usuwania plików.");
            }

          //Zamykamy program
            System.exit(0);

        } else {
            System.out.println("Anulowano usuwanie konta.");
            System.out.println("Naciśnij Enter...");
            scanner.nextLine();
        }
    }
}