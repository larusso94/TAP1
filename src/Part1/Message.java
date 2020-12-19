package Part1;

import java.util.Date;

public class Message {
    private String subject;
    private String body;
    private User sender;
    private User reciever;
    private Date creation;

    public Message(String subject, String body, User sender, User reciever, Date creation) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.reciever = reciever;
        this.creation = creation;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
