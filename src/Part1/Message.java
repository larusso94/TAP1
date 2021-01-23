package Part1;

import java.sql.Timestamp;

public class Message {
    private final String subject;
    private final String body;
    private final String sender;
    private final String reciever;
    private final Timestamp creation;

    public Message(String subject, String body, String sender, String reciever, Timestamp creation) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.reciever = reciever;
        this.creation = creation;
    }

    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public String getSender() {
        return sender;
    }
    public String getReciever() {
        return reciever;
    }
    public Timestamp getCreation() {
        return creation;
    }
    public boolean contains(String word){
        String[] words = this.body.split(" ");
        for (String w : words) if (w.equals(word)) return true;
        return false;
    }


    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                ", creation=" + creation +
                '}';
    }

    public long numWords (){
        return this.getBody().split(" ").length;
    }
}
