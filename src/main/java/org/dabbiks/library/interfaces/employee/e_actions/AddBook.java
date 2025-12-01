package org.dabbiks.library.interfaces.employee.e_actions;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.item.Book;
import org.dabbiks.item.Genre;
import org.dabbiks.library.interfaces.employee.EmployeeInterface;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class AddBook {

    Scanner scanner = new Scanner(System.in);
    List<String> options = List.of("1", "2", "3", "4", "5", "6", "7", "8");
    String answer = "";

    public void addNewBook() {
        try {
            addBook();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public void addBook() throws IOException {

        Utils.clearConsole();
        System.out.println("DODAWANIE NOWEJ KSIĄŻKI");
        System.out.println("Krok 1: Podaj tytuł: ");
        String title = scanner.nextLine();
        Genre genre = null;

        Utils.clearConsole();
        System.out.println("Krok 2: Podaj autora (Imię Nazwisko): ");
        String author = scanner.nextLine();

        Utils.clearConsole();
        System.out.println("Krok 3: Podaj rok wydania: ");
        int year = scanner.nextInt();

        Utils.clearConsole();
        System.out.println("Krok 4: Wybierz gatunek książki: ");

        while (!options.contains(answer)) {
            Utils.clearConsole();
            System.out.println("Wpisz numer gatunku książki:");
            System.out.println("1. SCIENCE_FICTION");
            System.out.println("2. ROMANCE");
            System.out.println("3. CRIMINAL");
            System.out.println("4. FANTASY");
            System.out.println("5. HORROR");
            System.out.println("6. BIOGRAPHY");
            System.out.println("7. GUIDE");
            System.out.println("8. COMIC");
            answer = scanner.nextLine();
            if (answer.equals("1")) genre = Genre.SCIENCE_FICTION;
            if (answer.equals("2")) genre = Genre.ROMANCE;
            if (answer.equals("3")) genre = Genre.CRIMINAL;
            if (answer.equals("4")) genre = Genre.FANTASY;
            if (answer.equals("5")) genre = Genre.HORROR;
            if (answer.equals("6")) genre = Genre.BIOGRAPHY;
            if (answer.equals("7")) genre = Genre.GUIDE;
            if (answer.equals("8")) genre = Genre.COMIC;
            else System.out.println("Nieprawidłowy numer");
        }


        Utils.clearConsole();
        System.out.println("Krok 5: Podaj liczbę stron: ");
        int pages = scanner.nextInt();

        Utils.clearConsole();

        //Dodawanie książki do listy
        Book addNewBook = new Book(title, author, year, genre, pages);
        library.books.add(addNewBook);
        Data.saveAll(DataType.BOOK, library.books);

        Utils.clearConsole();
        System.out.println("Książka '" + title + "' została dodana do biblioteki");
        scanner.nextLine();
        String s;

        do {
            System.out.println("Wpisz '1', aby wyjść");
            s = scanner.nextLine();

        } while (!s.equals("1"));

            EmployeeInterface empInterface = new EmployeeInterface();
            empInterface.employeeInterface();

    }
}
