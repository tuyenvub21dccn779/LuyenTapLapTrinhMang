/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 20241119L;
    private String id, name;
    private double baseSalary, finalSalary;
    private int experienceYears;

    public Employee() {
    }

    public Employee(String id, String name, double baseSalary, int experienceYears) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.experienceYears = experienceYears;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", baseSalary=" + baseSalary + ", finalSalary=" + finalSalary + ", experienceYears=" + experienceYears + '}';
    }
    
    
}
