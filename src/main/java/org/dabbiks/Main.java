package org.dabbiks;

import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;

import java.io.IOException;

public class Main {

    public static Library library;

    static {
        try {
            library = new Library();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        library.LobbyInterface();


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