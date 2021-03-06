package student.inputs;

import student.baseClass.ConsoleColors;
import student.implementations.Implemantations;

import java.util.Scanner;

import static student.inputs.StringInput.existStudent;

public class IntegerInput {
    public static String getStudentID(Implemantations student) {
        String studentID=null;
        do {
            System.out.print(ConsoleColors.BLUE + "Telebenin id-ni daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);

            try {
                studentID = scanner.nextLine();
                if (!existStudent(studentID, student)) {
                    System.out.println(ConsoleColors.RED+"Telebenin id-ni dogru sekilde daxil edin."+ConsoleColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED+"Numeric tipde input daxi edin"+ConsoleColors.RESET);
            }


        } while (!existStudent(studentID, student));
        return studentID;
    }
}
