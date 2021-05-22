package student.crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import student.Models.Student;
import student.baseClass.ConsoleColors;
import student.implementations.Implemantations;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static student.inputs.IntegerInput.getStudentID;
import static student.inputs.StringInput.*;

public class StudentCRUD {
    public static void addNewStudent(Implemantations student) throws IOException {
        String studentName = addString("Telebenin adini daxil edin:", false);
        String studentSurname = addString("Telebenin surname daxil edin:", false);
        String fatherName = addString("Telebenin ata adini daxil edin", false);
        String email = emailValidation();
        String number = addString("Telebenin mobil nomresini daxil edin", true);
        Student addedStudent = new Student(studentName, studentSurname, fatherName, email, number);
        student.createStudent(addedStudent);

        addToJson(student.getStudentList());
        addToJsonName(student.getStudentListByName());
        addToJsonSurname(student.getStudentListBSurname());
        addToJsonFatherName(student.getStudentListByFatherName());
        addToJsonEmail(student.getStudentListByEmail());
    }

    public static void addToJsonFatherName(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(properties.getProperty("app.studentFatherNameJsonFile")));
        new Gson().toJson(map);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
        bufferedWriter.close();
    }

    public static void addToJsonEmail(Set<String> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(properties.getProperty("app.studentEmailJsonFile")));
        new Gson().toJson(studentSortedMap);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(studentSortedMap));
        bufferedWriter.close();
    }

    public static void addToJson(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(properties.getProperty("app.studentMainJsonFile")));
        new Gson().toJson(studentSortedMap);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(studentSortedMap));
        bufferedWriter.close();
    }

    public static void addToJsonName(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(properties.getProperty("app.studentNameJsonFile")));
        new Gson().toJson(map);
        bufferedWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(map));
        bufferedWriter.close();
    }

    public static void addToJsonSurname(SortedMap<String, Student> studentSortedMap) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("app.config"));
        SortedMap<String, Student> map = Collections.synchronizedSortedMap(studentSortedMap);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(properties.getProperty("app.studentSurnameJsonFile")));
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
        addToJsonEmail(student.getStudentListByEmail());
    }

    public static void getAllStudent(Implemantations student) throws IOException {
        SortedMap<String, Student> map = student.getStudentList();

        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Butun telebelerin siyahisi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);
        for (var item : map.values()) {
            System.out.println("Telebenin adi:" + ConsoleColors.RED_UNDERLINED
                    + item.name + ConsoleColors.RESET + "-soyadi:"
                    + ConsoleColors.RED_UNDERLINED + item.surname
                    + ConsoleColors.RESET + "-ata adi:"
                    + ConsoleColors.RED_UNDERLINED + item.fatherName
                    + ConsoleColors.RESET + "-emaili:"
                    + ConsoleColors.RED_UNDERLINED + item.email
                    + ConsoleColors.RESET + "-nomresi:"
                    + ConsoleColors.RED_UNDERLINED
                    + item.phoneNumber + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);

    }

    public static void updateStudent(Implemantations student) throws IOException {
        String studentID = getStudentID(student);
        Student updatedStudent = student.getStudent(studentID);
        String yesOrNot;
        String studentName;
        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED + "Telebenin adini deyismek isteyirsizse(y),istemirsizse(n)?y/n:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            yesOrNot = scanner.nextLine();
        } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));
        switch (yesOrNot.toLowerCase()) {
            case "n":
                studentName = updatedStudent.name;
                break;
            default:
                studentName = addString("Telebenin adini daxil edin:", false);
                break;
        }
        String studentSurname;
        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED + "Telebenin soyadini deyismek isteyirsizse(y),istemirsizse(n)?y/n:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            yesOrNot = scanner.nextLine();
        } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));

        switch (yesOrNot.toLowerCase()) {
            case "n":
                studentSurname = updatedStudent.surname;
                break;
            default:
                studentSurname = addString("Telebenin soyadini daxil edin:", false);
                break;
        }
        String fatherName;
        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED + "Telebenin ata adini deyismek isteyirsizse(y),istemirsizse(n)?y/n:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            yesOrNot = scanner.nextLine();
        } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));

        switch (yesOrNot.toLowerCase()) {
            case "n":
                fatherName = updatedStudent.fatherName;
                break;
            default:
                fatherName = addString("Telebenin ata adini daxil edin:", false);
                break;
        }
        String email;
        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED + "Telebenin emalini deyismek isteyirsizse(y),istemirsizse(n)?y/n:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            yesOrNot = scanner.nextLine();
        } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));

        switch (yesOrNot.toLowerCase()) {
            case "n":
                email = updatedStudent.email;
                break;
            default:
                email = emailValidation();
                break;
        }
        String number;

        do {
            System.out.print(ConsoleColors.BLUE_UNDERLINED + "Telebenin nomresini deyismek isteyirsizse(y),istemirsizse(n)?y/n:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            yesOrNot = scanner.nextLine();
        } while (!yesOrNot.toLowerCase().contentEquals("y") && !yesOrNot.toLowerCase().contentEquals("n"));

        switch (yesOrNot.toLowerCase()) {
            case "n":
                number = updatedStudent.phoneNumber;
                break;
            default:
                number = addString("Telebenin mobil nomresini daxil edin", true);
                break;
        }
        student.updateStudent(studentID, studentName, studentSurname, fatherName, email, number);
        SortedMap<String, Student> studentSortedMap = student.getStudentList();
        addToJson(studentSortedMap);
        addToJsonName(student.getStudentListByName());
        addToJsonSurname(student.getStudentListBSurname());
        addToJsonFatherName(student.getStudentListByFatherName());
        addToJsonEmail(student.getStudentListByEmail());
    }

    public static void searchByName(Implemantations student) throws IOException {
        String studentName = addString("Telebenin adinina gore axtaris ucun input daxil edin:", false);
        SortedMap<String, Student> sortedMap = student.getStudentListByName();
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentName.toUpperCase())).findFirst().orElse(null);
        if (key == null) {
            System.out.println(ConsoleColors.RED + "\nAxtaris neticesi tapilmadi\n" + ConsoleColors.RESET);
            return;
        }
        showSearchResult(sortedMap.tailMap(key).values().stream().filter(a->a.name.startsWith(studentName)).collect(Collectors.toList()));
    }

    public static void searchBySurname(Implemantations student) throws IOException {
        String studentSurname = addString("Telebenin soyadina gore axtaris ucun input daxil edin:", false);
        SortedMap<String, Student> sortedMap = student.getStudentListBSurname();
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentSurname.toUpperCase())).findFirst().orElse(null);
        if (key == null) {
            System.out.println(ConsoleColors.RED + "\nAxtaris neticesi tapilmadi\n" + ConsoleColors.RESET);
            return;
        }
        showSearchResult(sortedMap.tailMap(key).values().stream().filter(a->a.surname.startsWith(studentSurname)).collect(Collectors.toList()));
    }

    public static void searchByFatherName(Implemantations student) throws IOException {
        String studentFatherName = addString("Telebenin ata adina gore axtaris ucun input daxil edin:", false);
        SortedMap<String, Student> sortedMap = student.getStudentListByFatherName();
        String key = sortedMap.keySet().stream().filter(a -> a.toUpperCase().startsWith(studentFatherName.toUpperCase())).findFirst().orElse(null);
        if (key == null) {
            System.out.println(ConsoleColors.RED + "\nAxtaris neticesi tapilmadi\n" + ConsoleColors.RESET);
            return;
        }
        showSearchResult(sortedMap.tailMap(key).values().stream().filter(a->a.fatherName.startsWith(studentFatherName)).collect(Collectors.toList()));

    }

    private static void showSearchResult(Collection<Student> values) {
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Axtaris neticesi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);
        for (var item : values) {
            System.out.println("Telebenin adi:" + ConsoleColors.RED_UNDERLINED + item.name + ConsoleColors.RESET + "-soyadi:" + ConsoleColors.RED_UNDERLINED + item.surname + ConsoleColors.RESET + "-ata adi:" + ConsoleColors.RED_UNDERLINED + item.fatherName + ConsoleColors.RESET + "-emaili:" + ConsoleColors.RED_UNDERLINED + item.email + ConsoleColors.RESET + "-nomresi:" + ConsoleColors.RED_UNDERLINED + item.phoneNumber + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ConsoleColors.RESET);

    }


}
