package Part1;

import java.util.ArrayList;
import java.util.Hashtable;

public class MailMem implements MailStore{
    private final Hashtable <String, ArrayList<Message>> messages;

    public MailMem (){
        this.messages = new Hashtable<>();
    }

    public void sendMail(Message message){

        if (!this.messages.containsKey(message.getReciever())){
            this.messages.put(message.getReciever(), new ArrayList<>());
        }
        this.messages.get(message.getReciever()).add(message);
    }

    public ArrayList<Message> getMail(String user){
        if (messages.containsKey(user))
            return messages.get(user);
        else
            return new ArrayList<>();
    }
}
