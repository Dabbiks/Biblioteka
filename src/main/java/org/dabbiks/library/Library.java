package org.dabbiks.library;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.item.Audiobook;
import org.dabbiks.item.Book;
import org.dabbiks.library.interfaces.employee.EmployeeLogin;
import org.dabbiks.library.interfaces.owner.OwnerLogin;
import org.dabbiks.library.interfaces.user.UserLogin;
import org.dabbiks.person.Employee;
import org.dabbiks.person.User;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Library {

    // Poniższe listy books, audiobooks, employees i users przechowują wszystkie zapisywane dane
    // Poradnik wkrótce

    public List<Book> books = Data.loadAll(DataType.BOOK, Book.class);
    public List<Audiobook> audiobooks = Data.loadAll(DataType.AUDIOBOOK, Audiobook.class);
    public List<Employee> employees = Data.loadAll(DataType.EMPLOYEE, Employee.class);
    public List<User> users = Data.loadAll(DataType.USER, User.class);

    public UserLogin userLogin = new UserLogin();
    public EmployeeLogin employeeLogin = new EmployeeLogin();
    public OwnerLogin ownerLogin = new OwnerLogin();

    public User loggedUser = null;
    public Employee loggedEmployee = null;

    public Library() throws IOException {
    }

    Scanner scanner = new Scanner(System.in);

    public void lobbyInterface() {
        List<String> options = List.of("1", "2", "3", "4", "X");
        String answer = "";

        while (!options.contains(answer)) {
            Utils.clearConsole();
            System.out.println("Wybierz akcję");
            System.out.println("Wpisz odpowiednią cyfrę w konsoli, żeby przejść dalej");
            System.out.println("-----------------------");
            System.out.println("Zaloguj jako:");
            System.out.println("1. Użytkownik");
            System.out.println("2. Pracownik");
            System.out.println("3. Właściciel");
            System.out.println("-----------------------");
            System.out.println("4. Sprawdź godziny otwarcia");
            System.out.println("X. Zamknij program");
            System.out.println("-----------------------");
            answer = scanner.nextLine();
            Utils.clearConsole();
        }

        if (answer.equals("1")) userLogin.userLogin();
        if (answer.equals("2")) employeeLogin.employeeInterface();
        if (answer.equals("3")) ownerLogin.login();
        if (answer.equals("4")) openingHours();
        if (answer.equals("X")) { Utils.saveAllData(); System.exit(0); }
    }

    private void openingHours() {
        String[] days = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
        String[] hours = {"10:00 - 19:00", "10:00 - 19:00", "10:00 - 19:00", "10:00 - 19:00", "10:00 - 19:00", "12:00 - 15:00", "Zamknięte"};
        for (int i = 0; i < days.length; i++) {
            System.out.println(days[i] + " | " + hours[i]);
        }
        System.out.println("Wpisz cokolwiek, żeby wrócić do menu głównego");
        scanner.nextLine();
        lobbyInterface();
    }
}
