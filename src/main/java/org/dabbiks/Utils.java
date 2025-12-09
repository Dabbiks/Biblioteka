package org.dabbiks;

import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;

import java.io.IOException;

import static org.dabbiks.Main.library;

public class Utils {

    public static void clearConsole() {
        for (int i = 0; i < 30; i++)
            System.out.println(" ");
    }

    public static void saveAllData() {
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
    }

}
