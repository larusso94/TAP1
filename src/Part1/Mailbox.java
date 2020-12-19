package Part1;

import java.util.ListIterator;

public class Mailbox {
    private MailStore message_store;
    private User user;
    private ListIterator <Message> messages;

    public Mailbox(MailStore message_store, User user) {
        this.message_store = message_store;
        this.user = user;
    }

    public MailStore getMessage_store() {
        return message_store;
    }

    public void setMessage_store(MailStore message_store) {
        this.message_store = message_store;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListIterator<Message> getMessages() {
        return messages;
    }

    public void setMessages(ListIterator<Message> messages) {
        this.messages = messages;
    }
}
