package Part1;

import java.util.List;

public interface MailStore {
    public void sendMail(Message message);
    public List<Message> getMail(User user);
}
