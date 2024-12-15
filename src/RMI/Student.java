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
public class Student implements Serializable {
    private static final long serialVersionUID = 20241130L;
    private String id, name, code;
    private int enrollmentYear;

    public Student() {
    }

    public Student(String id, String name, String code, int enrollmentYear) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.enrollmentYear = enrollmentYear;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", code=" + code + ", enrollmentYear=" + enrollmentYear + '}';
    }
    
}
