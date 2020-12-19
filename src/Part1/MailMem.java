package Part1;

import java.util.LinkedList;

public class MailMem implements MailStore{
    private LinkedList <Message> messages;

    public MailMem (){
        this.messages = new LinkedList<Message>();
    }

    public void sendMail(Message message){
        this.messages.add(message);
    }
    public LinkedList<Message> getMail(User user){
        return new LinkedList<Message>();
    }
}
