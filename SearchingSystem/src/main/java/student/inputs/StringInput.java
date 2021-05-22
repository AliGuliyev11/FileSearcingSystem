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
    private static final String ONLY_WORD_REGEX = "[a-zA-Z]+";

    public static String addString(String text,boolean isPhone) {
        String myString="";
        do {
            System.out.print(ConsoleColors.BLUE+text+ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            myString = scanner.nextLine();
            if (isPhone==true){
                if (!isValidEmailWordOrPhone(myString, PHONE_REGEX)) {
                    System.out.println(ConsoleColors.RED+"Nomrenizi dogru sekilde daxil edin.(+994551234567)"+ConsoleColors.RESET);
                }
            }
            if (isPhone==false){
                if (!isValidEmailWordOrPhone(myString, ONLY_WORD_REGEX)) {
                    System.out.println(ConsoleColors.RED+"Daxil etdiyiniz inputun terkibinde reqem ola bilmez"+ConsoleColors.RESET);
                }
            }
        } while ((!isValidEmailWordOrPhone(myString, PHONE_REGEX)&&isPhone==true) || (!isValidEmailWordOrPhone(myString, ONLY_WORD_REGEX)&&isPhone==false) ||myString.length() == 0);
        return myString;
    }

    public static String emailValidation() throws IOException {
        Implemantations implemantations=new Implemantations();
        String myString;
        do {
            System.out.print(ConsoleColors.BLUE + "Telebenin emailini daxil edin:" + ConsoleColors.RESET);
            Scanner scanner = new Scanner(System.in);
            myString = scanner.nextLine();

            if (!isValidEmailWordOrPhone(myString, EMAIL_REGEX)) {
                System.out.println(ConsoleColors.RED+"Emailinizi dogru sekilde daxil edin."+ConsoleColors.RESET);
            }
            if (implemantations.getStudentListByEmail().contains(myString)){
                System.out.println(ConsoleColors.RED+"Bu adda email artiq var"+ConsoleColors.RESET);
            }

        }
        while (!isValidEmailWordOrPhone(myString, EMAIL_REGEX) || implemantations.getStudentListByEmail().contains(myString));
        return myString;

    }

    public static boolean isValidEmailWordOrPhone(String emailOrNumber, String regex) {
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
