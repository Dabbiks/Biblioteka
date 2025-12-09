package org.dabbiks.person;

import org.dabbiks.library.Library;
import org.dabbiks.person.card.Card;

import static org.dabbiks.Main.library;

public class User extends Person {

    public Card card;

    public User(String name, String surname, String pesel, Card card) {
        super(name, surname, pesel);
        this.card = card;
    }

    @Override
    public String toString() {
        return "Witaj " + super.getName();
    }

    @Override
    public String getIdentificator() {
        return "Identyfikator użytkownika: " + card.getId() + ", Status: " + card.getStatus().getName();
    }

    @Override
    public String generateFileName() {
        return getName() + getSurname();
    }

}
