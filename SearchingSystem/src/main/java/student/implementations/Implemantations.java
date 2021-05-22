package student.implementations;

import student.Interface.IStudent;
import student.Models.Student;

import java.util.*;

public class Implemantations implements IStudent {

    public SortedMap<String, Student> studentSortedMap;
    public SortedMap<String, Student> studentName;
    public SortedMap<String, Student> studentSurname;
    public SortedMap<String, Student> studentFatherName;
    public Map<String, Student> email;

    public Implemantations() {
        studentSortedMap = new TreeMap<>();
        studentName = new TreeMap<>();
        studentSurname = new TreeMap<>();
        studentFatherName = new TreeMap<>();
        email = new HashMap<>();
    }

    @Override
    public Student getEmail(String mail) {
        return email.get(mail);
    }

    @Override
    public void createStudent(Student student) {
        studentSortedMap.put(student.id, student);
        studentName.put(student.name + student.id, student);
        studentSurname.put(student.surname + student.id, student);
        studentFatherName.put(student.fatherName + student.id, student);
        email.put(student.email, student);
    }

    @Override
    public void updateStudent(String id, String name, String surname, String fatherName, String mail, String phoneNumber) {
        Student updatedStudent =studentSortedMap.get(id);

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
            email.put(mail, updatedStudent);
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

    @Override
    public SortedMap<String, Student> getStudentListBSurname() {
        return studentSurname;
    }

    @Override
    public SortedMap<String, Student> getStudentListByFatherName() {
        return studentFatherName;
    }
}
