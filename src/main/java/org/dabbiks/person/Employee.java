package org.dabbiks.person;

public class Employee extends Person {

    private final int employeeId;
    private final String password;
    private final boolean isOwner;
    private double salary;

    public Employee(String name, String surname, String pesel, String password, int employeeId, boolean isOwner) {
        super(name, surname, pesel);
        this.employeeId = employeeId;
        this.password = password;
        this.isOwner = isOwner;
    }

    @Override
    public String getIdentificator() {
        return "Identyfikator pracownika: " + employeeId;
    }

    @Override
    public String generateFileName() {
        return getName() + getSurname();
    }

    public boolean isOwner() {
        return isOwner;
    }

    public double getSalary() {
        return salary;
    }

    public String getPassword() {
        return password;
    }

}
