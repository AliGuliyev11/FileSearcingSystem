package student.Interface;

import student.Models.Student;

import java.util.*;

public interface IStudent {

    Map<Integer,Student> students = new HashMap<>();
    SortedMap<String,Student> studentName = new TreeMap<>();
    SortedMap<String,Student> studentSurname = new TreeMap<>();
    SortedMap<String,Student> studentFatherName = new TreeMap<>();

    void createStudent(Student student);

    void updateStudent(String id,String name,String surname,String fatherName,String email,String phoneNumber);

    void deleteStudent(String id);
    Map<String,Student> getStudentList();
    SortedMap<String,Student> getStudentListByName();
    SortedMap<String,Student> getStudentListBSurname();
    SortedMap<String,Student> getStudentListByFatherName();

    Student getStudent(String id);

    Student getEmail(String email);
}
