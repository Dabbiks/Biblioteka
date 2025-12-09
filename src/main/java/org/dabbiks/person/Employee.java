package org.dabbiks.person;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Employee extends Person {
    private final int employeeId;
    private final String password;
    private double salary;
    private double totalBonus = 0.0;
    private Map<String, String> workSchedule;

    public Employee(String name, String surname, String pesel, String password, int employeeId, boolean isOwner) {
        super(name, surname, pesel);
        this.employeeId = employeeId;
        this.password = password;
        this.totalBonus = 0.0;
        this.workSchedule = new LinkedHashMap<>();
        String[] days = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
        for(String day : days){
            this.workSchedule.put(day, "Wolne");
        }
    }

    @Override
    public String getIdentificator() {
        return "" + employeeId;
    }

    @Override
    public String generateFileName() {
        return getName() + getSurname();
    }

    public int getEmployeeId() { return employeeId; }

    public double getSalary() {
        return salary;
    }

    public String getPassword() {
        return password;
    }

    public void addBonus(double amount){
        if (amount > 0){
            this.totalBonus += amount;
        }
    }

    public double getTotalBonus(){
        return totalBonus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public void setShift(String day, String hours){
        for (String key : workSchedule.keySet()){
            if(key.equalsIgnoreCase(day)){
                workSchedule.put(key, hours);
                return;
            }
        }
        System.out.println("Błąd! Niepoprawny dzień tygodnia.");
    }
    public Map<String, String> getWorkSchedule(){
        if (workSchedule == null) {
            workSchedule = new LinkedHashMap<>();
            String[] days = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
            for (String day : days) {
                this.workSchedule.put(day, "Wolne");
            }
        }
        return workSchedule;
    }
}
