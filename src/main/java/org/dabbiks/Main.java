package org.dabbiks;

import org.dabbiks.data.Data;
import org.dabbiks.data.DataType;
import org.dabbiks.library.Library;

import java.io.IOException;

public class Main {

    public static Library library;

    public static void main(String[] args) {

        {
            try {
                library = new Library();
                library.lobbyInterface();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}