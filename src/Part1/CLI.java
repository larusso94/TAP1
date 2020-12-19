package Part1;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Scanner;

public class CLI {

    private static final Scanner sc = new Scanner(System.in);
    private static final MailSystem system = new MailSystem(new MailMem());
    private static User user = null;

    private static void printHeader(){
        if (user != null)
            System.out.println("State : logged  User : "+user.getUsername());
        else
            System.out.println("State : not logged");
        System.out.println("Enter comand:");
    }

    private static void createUser (String[] args){
        if (args.length == 4 && user == null){
            try{
                system.createUser(args[1], args[2], Timestamp.valueOf(args[3].concat(" 00:00:00")));
            } catch (Exception e) {
                System.out.println("Wrong date format");
            }
        }
    }
    private static void filter (String[] args){

    }
    private static void logas(String[] args){
        if (args.length == 2 && system.getUser(args[1])!= null ){
            user = system.getUser(args[1]);
        }
    }
    private static void send(String[] args){

    }
    private static void update(){
        System.out.println("Uptdating mail...");
        system.getMailbox(user.getUsername()).updateMail();
    }
    private static void list (){
        System.out.println("Mailbox content:");
        system.getMailbox(user.getUsername()).getMessages().forEach(System.out::println);
    }
    private static void sort (String[] args){

    }

    private static boolean comand(String[] args){
        switch(args[0]){
            case "/help":
                System.out.println("Avalible comands are:");
                if (user == null){
                    System.out.println("/createuser : string <username> string <name> string <birthdate> format yyyy-mm-dd : Create a new user as admin");
                    System.out.println("/filter");
                    System.out.println("/logas");
                }
                else {
                    System.out.println("/send");
                    System.out.println("/update");
                    System.out.println("/list");
                    System.out.println("/sort");
                    System.out.println("/filter");
                }
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
            case "/logout":
                if (args.length == 1 && user != null)
                    user = null;
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
