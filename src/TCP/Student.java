/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 20151107;
    private int id;
    private String code, gpaLetter;
    private float gpa;

    public Student() {
    }

    public Student(int id, String code, float gpa) {
        this.id = id;
        this.code = code;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGpaLetter() {
        return gpaLetter;
    }

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", code=" + code + ", gpaLetter=" + gpaLetter + ", gpa=" + gpa + '}';
    }
    
}
