package student.crud;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import student.Models.Student;
import student.baseClass.ConsoleColors;
import student.implementations.Implemantations;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static student.inputs.IntegerInput.getStudentID;
import static student.inputs.StringInput.*;

public class StudentCRUD {
    public static void addNewStudent(Implemantations student) throws IOException {
        String studentName = addString("Telebenin adini daxil edin:");
        String studentSurname = addString("Telebenin surname daxil edin:");
        String fatherName = addString("Telebenin ata adini daxil edin");
        String email = emailValidation();
        String number = addString("Telebenin mobil nomresini daxil edin");
        Student addedStudent = new Student(studentName, studentSurname, fatherName, email, number);
        student.createStudent(addedStudent);

        addToJson(student.getStudentList());
        addToJsonName(student.getStudentListByName());
        addToJsonSurname(student.getStudentListBSurname());
        addToJsonFatherName(student.getStudentListByFatherName());
    }

    public static void addToJsonFatherName(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/student/Files/studentFatherName.json"));
        new Gson().toJson(map);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
        bufferedWriter.close();
    }

    public static void addToJson(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
//        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/student/Files/student2.json"));
        new Gson().toJson(studentSortedMap);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(studentSortedMap));
        bufferedWriter.close();
    }

    public static void addToJsonName(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/student/Files/studentName2.json"));
        new Gson().toJson(map);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
        bufferedWriter.close();
    }

    public static void addToJsonSurname(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/student/Files/studentSurname2.json"));
        new Gson().toJson(map);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
        bufferedWriter.close();
    }


    public static void removeStudent(Implemantations student) throws IOException {

        String studentID = getStudentID(student);
        student.deleteStudent(studentID);
        SortedMap<String, Student> studentSortedMap = student.getStudentList();
        addToJson(studentSortedMap);
        addToJsonName(student.getStudentListByName());
        addToJsonSurname(student.getStudentListBSurname());
        addToJsonFatherName(student.getStudentListByFatherName());
    }

    public static void getAllStudent(Implemantations student) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Student>>() {
        }.getType();
        HashMap<String, Student> map = gson.fromJson(new BufferedReader(new FileReader("src/main/java/student/Files/student2.json")), type);

        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Butun telebelerin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);
        for (var item : map.values()) {
            System.out.println("Telebenin adi:" + item.name + "-soyadi" + item.surname + "-ata adi" + item.fatherName + "-emaili" + item.email + "-nomresi" + item.phoneNumber);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);

    }

    public static void updateStudent(Implemantations student) throws IOException {
        String studentID = getStudentID(student);
        String studentName = addString("Telebenin adini daxil edin:");
        String studentSurname = addString("Telebenin surname daxil edin:");
        String fatherName = addString("Telebenin ata adini daxil edin");
        String email = emailValidation();
        String number = addString("Telebenin mobil nomresini daxil edin");
        student.updateStudent(studentID, studentName, studentSurname, fatherName, email, number);
        SortedMap<String, Student> studentSortedMap = student.getStudentList();
        addToJson(studentSortedMap);
        addToJsonName(student.getStudentListByName());
        addToJsonSurname(student.getStudentListBSurname());
        addToJsonFatherName(student.getStudentListByFatherName());
    }

    public static void searchByName(Implemantations student) throws FileNotFoundException {
        String studentName = addString("Telebenin adinina gore axtaris ucun input daxil edin:");
        Gson gson = new Gson();
        Type type = new TypeToken<SortedMap<String, Student>>() {
        }.getType();
//        SortedMap<String, Student> sortedMap2 = gson.fromJson(new BufferedReader(new FileReader("src/main/java/student/Files/studentName.json")), type);
        SortedMap<String, Student> sortedMap = gson.fromJson(new BufferedReader(new FileReader("src/main/java/student/Files/studentName2.json")), type);
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentName.toUpperCase())).findFirst().orElse(null);
        showSearchResult(sortedMap.tailMap(key).values().stream().filter(a -> a.name.toUpperCase().startsWith(studentName.toUpperCase())).collect(Collectors.toList()));
    }

    public static void searchBySurname(Implemantations student) {
        String studentSurname = addString("Telebenin soyadina gore axtaris ucun input daxil edin:");
        SortedMap<String, Student> sortedMap = student.getStudentListByName();
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentSurname.toUpperCase())).findFirst().orElse(null);
        showSearchResult(sortedMap.subMap(key, studentSurname + Character.MAX_VALUE).values());
    }

    public static void searchByFatherName(Implemantations student) {
        String studentFatherName = addString("Telebenin ata adina gore axtaris ucun input daxil edin:");
        SortedMap<String, Student> sortedMap = student.getStudentListByName();
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentFatherName.toUpperCase())).findFirst().orElse(null);
        showSearchResult(sortedMap.subMap(key, studentFatherName + Character.MAX_VALUE).values());

    }

    private static void showSearchResult(Collection<Student> values) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Axtaris neticesi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);
        for (var item : values) {
            System.out.println("Telebenin adi:" + item.name + "-soyadi" + item.surname + "-ata adi" + item.fatherName + "-emaili" + item.email + "-nomresi" + item.phoneNumber);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);

    }


}
