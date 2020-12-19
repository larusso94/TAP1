package Part1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

    public static void main(String[] args) throws ParseException {
        MailSystem system1 = new MailSystem(new MailMem());
        System.out.println(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010"));

        //Mailbox mail_user1 = system1.createUser("user1","pepe", new Date(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010")));
        //Mailbox mail_user2 = system1.createUser("user2","pepe", new Date(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2011")));
        //Mailbox mail_user3 = system1.createUser("user3","pepe", new Date(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2012")));

    }
}
