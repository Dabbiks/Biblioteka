package org.dabbiks.library;

import org.dabbiks.Utils;
import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.item.Audiobook;
import org.dabbiks.item.Book;
import org.dabbiks.library.accounts.EmployeeAccount;
import org.dabbiks.library.accounts.OwnerAccount;
import org.dabbiks.library.accounts.UserAccount;
import org.dabbiks.person.Employee;
import org.dabbiks.person.Owner;
import org.dabbiks.person.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Library {

    public List<Book> books = Data.loadAll(DataType.BOOK, Book.class);
    public List<Audiobook> audiobooks = Data.loadAll(DataType.AUDIOBOOK, Audiobook.class);
    public List<Employee> employees = Data.loadAll(DataType.EMPLOYEE, Employee.class);
    public List<User> users = Data.loadAll(DataType.USER, User.class);

    public UserAccount userAccount = new UserAccount();
    public EmployeeAccount employeeAccount = new EmployeeAccount();
    public OwnerAccount ownerAccount = new OwnerAccount();

    public Library() throws IOException {
    }

    public void LobbyInterface() {
        Scanner scanner = new Scanner(System.in);
        List<String> options = List.of("1", "2", "3");
        String answer = "";

        while (!options.contains(answer)) {
            System.out.println("Wybierz, na jaki typ konta chcesz się zalogować");
            System.out.println("Wpisz odpowiednią cyfrę w konsoli, żeby przejść dalej");
            System.out.println("1. Użytkownik");
            System.out.println("2. Pracownik");
            System.out.println("3. Właściciel");
            System.out.println("...............");
            answer = scanner.nextLine();
            Utils.clearConsole();
        }

        if (answer.equals("1")) userAccount.UserInterface();
        if (answer.equals("2")) employeeAccount.EmployeeInterface();
        if (answer.equals("3")) ownerAccount.OwnerInterface();
    }
}
