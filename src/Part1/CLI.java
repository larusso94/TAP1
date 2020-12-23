package Part1;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

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
        if(args.length == 3){
            try{
                Predicate<Message> pred;
                switch (args[1]) {
                    case "contains" -> {
                        pred = m -> m.contains(args[2]);
                        if (user == null) {
                            system.getMessagesFiltered(pred).forEach(System.out::println);
                        } else {
                            system.getMailbox(user.getUsername()).getMailsFiltered(pred).forEach(System.out::println);
                        }
                    }
                    case "lessthan" -> {
                        pred = m -> m.getBody().split(" ").length < Integer.decode(args[2]);
                        if (user == null) {
                            system.getMessagesFiltered(pred).forEach(System.out::println);
                        } else {
                            system.getMailbox(user.getUsername()).getMailsFiltered(pred).forEach(System.out::println);
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Wrong parameters");
            }
        }

    }
    private static void logas(String[] args){
        if (args.length == 2 && system.getUser(args[1])!= null ){
            user = system.getUser(args[1]);
            System.out.println("Logged in successfully");
        }
    }
    private static void send(String[] args){
        if (args.length >= 4 && user != null){
            int i = 3;
            String body = args[i];
            while(true){
                try{
                    i++;
                    body = body.concat(" ")+args[i];
                }catch (Exception e){
                    break;
                }
            }
            system.getMailbox(user.getUsername()).sendMail(args[1], args[2], body);
            System.out.println("Message sent");
        }
    }
    private static void update(){
        System.out.println("Uptdating mail...");
        system.getMailbox(user.getUsername()).updateMail();
    }
    private static void list (){
        System.out.println("Mailbox content:");
        system.getMailbox(user.getUsername()).getMailsSorted(Comparator.comparing(Message::getCreation)).forEach(System.out::println);
    }
    private static void sort (String[] args){
        if(args.length == 2 && user != null){
            Mailbox mail = system.getMailbox(user.getUsername());
            switch (args[1]) {
                case "date" -> mail.setMessages(mail.getMailsSorted(Comparator.comparing(Message::getCreation)));
                case "sender" -> mail.setMessages(mail.getMailsSorted(Comparator.comparing(Message::getSender)));
                case "reciever" -> mail.setMessages(mail.getMailsSorted(Comparator.comparing(Message::getReciever)));
                case "body" -> mail.setMessages(mail.getMailsSorted(Comparator.comparing(Message::getBody)));
                case "subject" -> mail.setMessages(mail.getMailsSorted(Comparator.comparing(Message::getSubject)));
            }
        }
    }

    private static boolean comand(String[] args){
        switch(args[0]){
            case "/help":
                System.out.println("Avalible comands are:");
                if (user == null){
                    System.out.println("/createuser <username> <name> <birthdate> format yyyy-mm-dd : Create a new user as admin");
                    System.out.println("/filter : Filter at a system level arguments: contains <word> or lessthan <num>");
                    System.out.println("/logas <username> Log in as a user. No passwords");
                }
                else {
                    System.out.println("/send <to> <subject> <body>");
                    System.out.println("/update retrieve messages from the mail store");
                    System.out.println("/list show messages sorted by sent time");
                    System.out.println("/sort sort messages by preddefined comparators : dare, sender, reciever, vody, subject");
                    System.out.println("/filter : Filter at a user level or user level if logged Arguments: - contains <word> - lessthan <num>");
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
                sort(args);
                break;
            case "/logout":
                if (args.length == 1 && user != null)
                    user = null;
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean exit = false;
        String comand;
        while (!exit){
            printHeader();
            System.out.print(">>");
            System.out.flush();
            comand = sc.nextLine();
            exit = comand(comand.split(" "));
        }
    }


}
