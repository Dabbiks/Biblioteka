package org.dabbiks.library.accounts;

public enum ErrorType {
    NULL(""),
    TOO_SHORT("⚠ Wpisany parametr jest zbyt krótki"),
    TOO_LONG("⚠ Wpisany parametr jest zbyt długi"),
    ONLY_LETTERS("⚠ Wpisany parametr powinien zawierać jedynie litery"),
    ONLY_NUMBERS("⚠ Wpisany parametr powinien zawierać jedynie cyfry"),
    ALREADY_REGISTERED("⚠ Konto o podanym numerze PESEL już istnieje"),
    NOT_REGISTERED("⚠ Wpisany parametr nie znajduje się w naszym systemie");

    private final String string;

    ErrorType(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
