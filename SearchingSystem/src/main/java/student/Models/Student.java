package student.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.SortedMap;
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
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("app.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type4Student = new TypeToken<SortedMap<String, Student>>() {
        }.getType();
        Integer key = null;
        try {
            if (new File(properties.getProperty("app.studentMainJsonFile")).exists()) {
                SortedMap<String, Student> map4MainStudent = gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentMainJsonFile"))), type4Student);
                key = map4MainStudent.size() != 0 ? Integer.valueOf(map4MainStudent.lastKey()) : null;
            } else {
                key = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        studentCounter = key == null ? 0 : key;
    }

    public Student(String name, String surname, String fatherName, String email, String phoneNumber) {
        studentCounter++;
//        UUID uuid = UUID.randomUUID();
        this.id = String.valueOf(studentCounter);
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
