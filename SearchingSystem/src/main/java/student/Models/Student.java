package student.Models;

import java.util.UUID;

public class Student {
    static int studentCounter;
    public String id;
    public String name;
    public String surname;
    public String fatherName;
    public String email;
    public String phoneNumber;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    static {
        studentCounter = 0;
    }

    public Student(String name, String surname, String fatherName, String email, String phoneNumber) {
        studentCounter++;
        UUID uuid = UUID.randomUUID();
        this.id =uuid.toString();
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
