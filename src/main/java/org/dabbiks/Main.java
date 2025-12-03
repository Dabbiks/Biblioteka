package org.dabbiks;

import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;
import org.dabbiks.person.Employee;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Library library;
    public static Scanner scanner;

    public static void main(String[] args) {

        {
            try {
                library = new Library();
                library.LobbyInterface();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try {
            Data.clearAll(DataType.BOOK);
            Data.clearAll(DataType.AUDIOBOOK);
            Data.clearAll(DataType.EMPLOYEE);
            Data.clearAll(DataType.USER);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Data.saveAll(DataType.BOOK, library.books);
                Data.saveAll(DataType.AUDIOBOOK, library.audiobooks);
                Data.saveAll(DataType.EMPLOYEE, library.employees);
                Data.saveAll(DataType.USER, library.users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}