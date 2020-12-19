package Part1;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Mailbox implements Iterable<Message>{
    private final MailStore store;
    private final String user;
    private final ArrayList<Message> messages;

    public Mailbox(MailStore message_store, String user) {
        this.store = message_store;
        this.user = user;
        this.messages = new ArrayList<>();
    }

    public MailStore getStore() { return store; }
    public String getUser() {
        return user;
    }
    public ArrayList<Message> getMessages(){
        return this.messages;
    }

    public ArrayList<Message> getMailsFiltered(Predicate<Message> filter){ return (ArrayList<Message>) this.messages.stream().filter(filter).collect(Collectors.toList()); }
    public ArrayList<Message> getMailsSorted(Comparator<Message> comp){ return (ArrayList<Message>) this.messages.stream().sorted(comp).collect(Collectors.toList()); }

    public void sendMail(String user, String subject, String body){
        this.store.sendMail(new Message(subject, body, this.user, user, Timestamp.valueOf(LocalDateTime.now())));
    }
    public void updateMail () {
        this.messages.addAll(this.store.getMail(this.user));
    }

    @Override
    public Iterator<Message> iterator() {
        return this.messages.iterator();
    }

}
