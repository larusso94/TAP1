package Part1;

import java.util.ArrayList;

public interface MailStore {
    public void sendMail(Message message);
    public ArrayList<Message> getMail(String user);
}
