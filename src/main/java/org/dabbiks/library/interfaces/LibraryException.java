package org.dabbiks.library.interfaces;

// To jest Twój własny wyjątek
public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}