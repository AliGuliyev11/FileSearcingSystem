package student.menu;

import student.Models.Student;
import student.baseClass.ConsoleColors;
import student.implementations.Implemantations;

import java.io.IOException;
import java.util.Scanner;

import static student.crud.StudentCRUD.*;

public class Menu {
    public static void Menu() throws IOException {
        int choice;
        boolean check = true;
        Implemantations student = new Implemantations();

//    label:
        do {
            do {
                System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Telebe uzerinde emeliyyat aparmaq.");
                System.out.println("0.Cixis." + ConsoleColors.RESET);

                try {
                    System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
                }


            } while (true);

            switch (choice) {
                case 1:
                    System.out.println(ConsoleColors.GREEN + "\nTelebeler sistemine xos gelmisiniz.\n" + ConsoleColors.RESET);
                    while (check) {
                        do {

                            System.out.println(ConsoleColors.YELLOW_BRIGHT + "1.Yeni telebe elave et.");
                            System.out.println("2.Telebe uzerinde duzelis et.");
                            System.out.println("3.Telebeni sil.");
                            System.out.println("4.Butun telebeleri goster.");
                            System.out.println("5.Telebeleri ada gore axtar.");
                            System.out.println("6.Telebeleri soyada gore axtar.");
                            System.out.println("7.Telebeleri ata adina gore axtar.");
                            System.out.println("0.Sistemden cixis." + ConsoleColors.RESET);

                            try {
                                System.out.print(ConsoleColors.BLUE + "Ededi daxil edin:" + ConsoleColors.RESET);

                                Scanner scanner = new Scanner(System.in);
                                choice = scanner.nextInt();

                                break;
                            } catch (Exception e) {
                                System.out.println(ConsoleColors.RED + "\nNumber tipinde eded daxil edin." + ConsoleColors.RESET);
                            }


                        } while (true);

                        switch (choice) {
                            case 1:
                                addNewStudent(student);
                                break;
                            case 2:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }else {
                                    updateStudent(student);
                                }
                                break;
                            case 3:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }
                                else {
                                    removeStudent(student);
                                }
                                break;
                            case 4:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }
                                else {
                                    getAllStudent(student);
                                }
                                break;
                            case 5:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }else {
                                    searchByName(student);
                                }
                                break;
                            case 6:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }else {
                                    searchBySurname(student);
                                }
                                break;
                            case 7:
                                if (student.getStudentList().size()==0){
                                    System.out.println(ConsoleColors.RED+"\nTelebe elave olunmayib\n"+ConsoleColors.RESET);
                                }else {
                                    searchByFatherName(student);
                                }
                                break;
                            case 0:
                                System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                                check = false;
                                break;
                            default:
                                System.out.println(ConsoleColors.RED + "\nZehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                                break;

                        }
                    }
                    check = false;
                    break;
                case 0:
                    System.out.println(ConsoleColors.RED + "\nSistemden cixdiniz" + ConsoleColors.RESET);
                    check = false;
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Zehmet olmasa duzgun secim edin" + ConsoleColors.RESET);
                    break;
            }


        } while (check);
    }
}
