package student.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import student.Interface.IStudent;
import student.Models.Student;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Implemantations implements IStudent {

    public SortedMap<String, Student> studentSortedMap;
    public SortedMap<String, Student> studentName;
    public SortedMap<String, Student> studentSurname;
    public SortedMap<String, Student> studentFatherName;
    public Set<String> email;

    public Implemantations() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        Gson gson = new Gson();
        Type type4Student = new TypeToken<SortedMap<String, Student>>() {
        }.getType();
        Type type4StudentEmail = new TypeToken<Set<String>>() {
        }.getType();


        SortedMap<String, Student> map4MainStudent =new File(properties.getProperty("app.studentMainJsonFile")).exists()?gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentMainJsonFile"))), type4Student):null;
        SortedMap<String, Student> map4StudentName =new File(properties.getProperty("app.studentNameJsonFile")).exists()? gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentNameJsonFile"))), type4Student):null;
        SortedMap<String, Student> map4StudentSurname =new File(properties.getProperty("app.studentSurnameJsonFile")).exists()? gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentSurnameJsonFile"))), type4Student):null;
        SortedMap<String, Student> map4StudentFatherName =new File(properties.getProperty("app.studentFatherNameJsonFile")).exists()? gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentFatherNameJsonFile"))), type4Student):null;
        Set<String> map4StudentEmail =new File(properties.getProperty("app.studentEmailJsonFile")).exists()? gson.fromJson(new BufferedReader(new FileReader(properties.getProperty("app.studentEmailJsonFile"))), type4StudentEmail):null;

        studentSortedMap =new File(properties.getProperty("app.studentMainJsonFile")).exists()? map4MainStudent.size() != 0 ? map4MainStudent : new TreeMap<>():new TreeMap<>();
        studentName =new File(properties.getProperty("app.studentNameJsonFile")).exists()?  map4StudentName.size() != 0 ? map4StudentName : new TreeMap<>():new TreeMap<>();
        studentSurname =new File(properties.getProperty("app.studentSurnameJsonFile")).exists()? map4StudentSurname.size() != 0 ? map4StudentSurname : new TreeMap<>():new TreeMap<>();
        studentFatherName =new File(properties.getProperty("app.studentFatherNameJsonFile")).exists()? map4StudentFatherName.size() != 0 ? map4StudentFatherName : new TreeMap<>():new TreeMap<>();
        email =new File(properties.getProperty("app.studentEmailJsonFile")).exists()? map4StudentEmail.size() != 0 ? map4StudentEmail : new TreeSet<>():new TreeSet<>();
    }


    @Override
    public void createStudent(Student student) {
        studentSortedMap.put(student.id, student);
        studentName.put(student.name + student.id, student);
        studentSurname.put(student.surname + student.id, student);
        studentFatherName.put(student.fatherName + student.id, student);
        email.add(student.email);
    }

    @Override
    public void updateStudent(String id, String name, String surname, String fatherName, String mail, String phoneNumber) {
        Student updatedStudent = studentSortedMap.get(id);

        if (updatedStudent != null) {
            studentName.remove(updatedStudent.name + updatedStudent.id);
            studentSurname.remove(updatedStudent.surname + updatedStudent.id);
            studentFatherName.remove(updatedStudent.fatherName + updatedStudent.id);
            email.remove(updatedStudent.email);

            updatedStudent.name = name;
            updatedStudent.surname = surname;
            updatedStudent.fatherName = fatherName;
            updatedStudent.email = mail;
            updatedStudent.phoneNumber = phoneNumber;
            studentName.put(name + id, updatedStudent);
            studentSurname.put(surname + id, updatedStudent);
            studentFatherName.put(fatherName + id, updatedStudent);
            email.add(mail);
        }
    }

    @Override
    public void deleteStudent(String id) {
        Student student = getStudent(id);
        studentSortedMap.remove(id);
        studentName.remove(student.name + student.id);
        studentSurname.remove(student.surname + student.id);
        studentFatherName.remove(student.fatherName + student.id);
        email.remove(student.email);
    }

    @Override
    public Student getStudent(String id) {
        return studentSortedMap.get(id);
    }

    @Override
    public SortedMap<String, Student> getStudentList() {
        return studentSortedMap;
    }

    @Override
    public SortedMap<String, Student> getStudentListByName() {
        return studentName;
    }

    public Set<String> getStudentListByEmail() {
        return email;
    }

    @Override
    public SortedMap<String, Student> getStudentListBSurname() {
        return studentSurname;
    }

    @Override
    public SortedMap<String, Student> getStudentListByFatherName() {
        return studentFatherName;
    }
}
