package Part1;

import java.io.IOException;
import java.util.ArrayList;

public interface MailStore {
    void sendMail(Message message) throws IOException;
    ArrayList<Message> getMail(String user) throws IOException;
}
