package org.dabbiks.library.interfaces.employee;
import org.dabbiks.Utils;
import org.dabbiks.library.interfaces.employee.actions.*;
import org.dabbiks.person.Employee;

import java.util.List;
import java.util.Scanner;

import static org.dabbiks.Main.library;

public class EmployeeInterface {

    Scanner scanner = new Scanner(System.in);
    List<String> options = List.of("1", "2", "3", "4");
    String answer = "";

    Employee employee = library.loggedEmployee;

    public AddNewUser newUser = new AddNewUser();
    public SearchBook searchBook = new SearchBook();
    public AddBook addBook = new AddBook();
    public QuitJob quitJob = new QuitJob();
    public AddAudiobook addAudiobook = new AddAudiobook();

    public void employeeInterface() {
        while (!options.contains(answer)) {
            Utils.clearConsole();
            System.out.println("Witaj " + employee.getName() + '!');
            System.out.println("Wybierz co chcesz zrobic:");
            System.out.println("1. Dodaj nowego użytkownika");
            System.out.println("2. Wyszukaj książkę");
            System.out.println("3. Dodaj nową książkę");
            System.out.println("4. Dodaj nowy audiobook");
            System.out.println("5. Złóż wypowiedzenie");
            answer = scanner.nextLine();
            if (answer.equals("1")) newUser.addUser();
            if (answer.equals("2")) searchBook.searchItems();
            if (answer.equals("3")) addBook.addNewBook();
            if (answer.equals("4")) addAudiobook.addNewAudiobook();
            if (answer.equals("5")) quitJob.quitJob(employee);
        }
    }
}
