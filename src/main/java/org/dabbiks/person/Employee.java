package org.dabbiks.person;

public class Employee extends Person {

    private final int employeeId;
    private double salary;
    private final String password;

    public Employee(String name, String surname, String pesel, String password, int employeeId) {
        super(name, surname, pesel);
        this.employeeId = employeeId;
        this.password = password;
    }

    @Override
    public String getIdentificator() {
        return "Identyfikator pracownika: " + employeeId;
    }

    @Override
    public String generateFileName() {
        return getName() + getSurname();
    }

    public double getSalary() {
        return salary;
    }

    public String getPassword() {
        return password;
    }

}
