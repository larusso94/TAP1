package Part1;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.function.Predicate;


public class Test1 {

    public static void main(String[] args) {

        // Initialize the mail system with an in-memory mail store.
        MailSystem system1 = new MailSystem(new MailMem());

        //Create at least 3 users, two have the same name but different username.
        Mailbox mail_user1 = system1.createUser("user1","pepe", Timestamp.valueOf("2010-10-10 00:00:00"));
        Mailbox mail_user2 = system1.createUser("user2","pepe", Timestamp.valueOf("2011-11-11 00:00:00"));
        Mailbox mail_user3 = system1.createUser("user3","papo", Timestamp.valueOf("2012-12-12 00:00:00"));

        // Then, use the mailboxes to send a few emails between them. Make some of them share the same subject and make enough so that the following tests have results.
        mail_user1.sendMail("user2","Saludo1", "hola bro1");
        mail_user1.sendMail("user2","Saludo2", "hola bro2");
        mail_user1.sendMail("user3","Saludo1", "hola bro3");
        mail_user1.sendMail("user3","Saludo2", "hola bro4");

        mail_user2.sendMail("user1","Respuesta1", "hola sis1");
        mail_user2.sendMail("user1","Respuesta2", "hola sis2");
        mail_user2.sendMail("user3","Saludo1", "hola sis3");
        mail_user2.sendMail("user3","Saludo2", "hola sis4");

        mail_user3.sendMail("user2","Respuesta3", "hola sis1");
        mail_user3.sendMail("user2","Respuesta4", "hola sis2");
        mail_user3.sendMail("user3","Respuesta5", "hola sis3");
        mail_user3.sendMail("user3","Respuesta6", "hola sis4");

        // Get one of the mailboxes and update its mail.
        mail_user1.updateMail();
        mail_user2.updateMail();
        mail_user3.updateMail();

        // List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities of the mailbox!
        Comparator<Message> comp = Comparator.comparing(Message::getCreation);
        mail_user2.getMailsSorted(comp).forEach(System.out::println);
        System.out.println("---------------------------------");

        // Now list the messages by sender username using the mailbox feature.
        comp = Comparator.comparing(Message::getSender);
        mail_user2.getMailsSorted(comp).forEach(System.out::println);
        System.out.println("---------------------------------");

        // Filter the messages with the following conditions:
        //- The message subject contains a certain word.
        //- The message sender is a certain user.
        mail_user3.getMailsFiltered(m -> m.getSender().equals("user1") && m.getSubject().contains("2")).forEach(System.out::println);
        System.out.println("---------------------------------");

        //Use the mail system object to retrieve all messages and print them.
        system1.getMessages().forEach(System.out::println);
        System.out.println("---------------------------------");

        //Filter messages globally that fulfill the following conditions:
        //- The message subject is a single word.
        //- The sender was born after year 2000.
        Predicate<Message> pred = m -> m.getSubject().split(" ").length == 1 && system1.getUser(m.getSender()).getDateOfBirth().after(Timestamp.valueOf("2000-01-01 00:00:00"));
        system1.getMessagesFiltered(pred).forEach(System.out::println);
        System.out.println("---------------------------------");

        //Get the count of messages in the system and print it.
        System.out.println(system1.getNumMessages());
        System.out.println("---------------------------------");

        // Get the average number of messages received per user and print it.
        System.out.println(system1.getMessagesPerUser());
        System.out.println("---------------------------------");

        // Group the messages per subject in a Map<String, List<Message>> and print it.
        system1.getGroupMessagesPerSubject().forEach((s,l)-> l.forEach(System.out::println));
        System.out.println("---------------------------------");
        // Count the words of all messages sent by users with a certain real name
        system1.getAllNames().forEach(s -> System.out.println(s+" "+system1.getNumWordsSentByName(s)));

        // Print the messages received by users born before year 2000.
        pred  = m -> system1.getUser(m.getReciever()).getDateOfBirth().before(Timestamp.valueOf("2000-01-01 00:00:00"));
        system1.getMessagesFiltered(pred).forEach(System.out::println);
        System.out.println("---------------------------------");
        // Now change the mail store to the file implementation.
    }
}
