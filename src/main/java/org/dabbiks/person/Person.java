package org.dabbiks.person;

public abstract class Person {

    private String name;
    private String surname;
    private String pesel;

    public Person(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return name + " hej witamy ciebie " + surname;
    }

    public abstract String getIdentificator();

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }

}
