package student.inputs;

import student.baseClass.ConsoleColors;
import student.implementations.Implemantations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringInput {

    private static final String EMAIL_REGEX = "([a-zA-Z0-9_.+-])+\\@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})";
    private static final String PHONE_REGEX = "[+]{1}[9]{2}[4]{1}(([5]([0]|[1]|[5]))|([7]([0]|[7]))|([9]([9])))[1-9][0-9]{6}";

    public static String addString(String text,boolean isPhone) {
        String myString="";
        do {
            System.out.print(ConsoleColors.BLUE+text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            myString = scanner.nextLine();
            if (isPhone==true){
                if (!isValidEmailOrPhone(myString, PHONE_REGEX)) {
                    System.out.println(ConsoleColors.RED+"Nomrenizi dogru sekilde daxil edin.(+994551234567)"+ConsoleColors.RESET);
                }
            }

        } while ((!isValidEmailOrPhone(myString, PHONE_REGEX)&&isPhone==true)||myString.length() == 0);
        return myString;
    }

    public static String emailValidation() throws IOException {
        Implemantations implemantations=new Implemantations();
        String myString;
        do {
            System.out.print(ConsoleColors.BLUE + "Telebenin emailini daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            myString = scanner.nextLine();

            if (!isValidEmailOrPhone(myString, EMAIL_REGEX)) {
                System.out.println(ConsoleColors.RED+"Emailinizi dogru sekilde daxil edin."+ConsoleColors.RESET);
            }
            if (implemantations.getStudentListByEmail().contains(myString)){
                System.out.println(ConsoleColors.RED+"Bu adda email artiq var"+ConsoleColors.RESET);
            }

        }
        while (!isValidEmailOrPhone(myString, EMAIL_REGEX) || implemantations.getStudentListByEmail().contains(myString));
        return myString;

    }

    public static boolean isValidEmailOrPhone(String emailOrNumber, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailOrNumber);
        return matcher.matches();
    }

    public static boolean existStudent(String id, Implemantations implemantations) {
        if (implemantations.getStudent(id)!=null){
            return true;
        }
        return false;
    }

}
