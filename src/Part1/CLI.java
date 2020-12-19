package Part1;

import java.io.IOException;
import java.util.Scanner;

public class CLI {

    private static final Scanner sc = new Scanner(System.in);
    private static final MailSystem system = new MailSystem(new MailMem());
    private static User user = null;

    /*public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/

    private static void printHeader(){
        //clearScreen();
        if (user != null)
            System.out.println("State : logged  User : "+user.getUsername());
        else
            System.out.println("State : not logged");
        System.out.println("Enter comand:");
    }
    /*private static int printLoginMenu(){
        printHeader();
        System.out.println("||  1. Login            ");
        System.out.println("||  2. CreateUser       ");
        System.out.println("||  3. Filter Messages  ");
        int swValue = Keyin.inInt(" Select option: ");
        while(swValue < 0 || swValue > 3){
            System.out.println("Error, select option between 1 and 3");
            swValue = Keyin.inInt(" Select option: ");
        }
        return swValue;
    }
    private static int printUserMenu(){
        printHeader();
        System.out.println("||  1. Login            ");
        System.out.println("||  2. CreateUser       ");
        System.out.println("||  3. Filter Messages  ");
        int swValue = Keyin.inInt(" Select option: ");
        while(swValue < 0 || swValue > 3){
            System.out.println("Error, select option between 1 and 3");
            swValue = Keyin.inInt(" Select option: ");
        }
        return swValue;
    }*/
    private static void createUser (String[] args){

    }
    private static void filter (String[] args){

    }
    private static void logas(String[] args){

    }
    private static void send(String[] args){

    }
    private static void update(){

    }
    private static void list (){

    }
    private static void sort (String[] args){

    }

    private static boolean comand(String[] args){
        switch(args[0]){
            case "/help":
                System.out.println("Avalible comands are:");
                System.out.println("/createuser string <username> string <name> string <birthdate> format yyyy/mm/dd hh:mm:ss : Create a new user as admin");
                break;
            case "/createuser":
                createUser(args);
                break;
            case "/filter":
                filter(args);
                break;
            case "/logas":
                logas(args);
                break;
            case "/send":
                send(args);
                break;
            case "/update":
                if (args.length == 1 && user != null)
                    update();
                break;
            case "/list":
                if (args.length == 1 && user != null)
                    list();
                break;
            case "/sort":
                break;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        String comand;
        while (!exit){
            printHeader();
            System.out.printf(">>");
            System.out.flush();
            comand = sc.nextLine();
            args = comand.split(" ");
            exit = comand(args);
        }
    }


}
