package Part1;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailSystem{
    private final MailStore store;
    private final Hashtable<String, Mailbox> mailboxes = new Hashtable<>();
    private final Hashtable<String, User> users = new Hashtable<>();

    public MailSystem (MailStore store){
        this.store = store;
    }

    /**
     * Create new user
     * @param username String Username
     * @param name String Name
     * @param birth_date Timestamp Date of birth
     * @return Object mailbox from user created or null if user already exists, missing params or empty name/username
     */
    public Mailbox createUser(String username, String name, Timestamp birth_date){
        if (username == null || name == null || birth_date == null) return null;
        if (username == "" || name == "") return null;
        if (mailboxes.containsKey(username)) return null;
        mailboxes.put(username, new Mailbox(store, username));
        users.put(username, new User(username, name, birth_date));
        return mailboxes.get(username);
    }

    /**
     * Get all messages in the system
     * @return ArrayList of all Messages of the system.
     */
    public ArrayList <Message> getMessages(){
        ArrayList<Message> list = new ArrayList<>();
        mailboxes.values().forEach(m -> list.addAll(m.getMessages()));
        return list;
    }

    /**
     * Get all users in the system.
     * @return returns ArrayLyst of all Users in the system
     */
    public ArrayList<User> getUsers() { return new ArrayList<>(users.values()); }

    /**
     * Filter messages globally: Get all messages in the system that fulfill a condition
     * @param filter Predicate criteria to filter Messages if null messages won't be filtered
     * @return ArrayList containing all filtered messages
     */
    public ArrayList<Message> getMessagesFiltered(Predicate<Message> filter){
        if (filter == null) return getMessages();
        return (ArrayList<Message>) getMessages().stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * Count total number of messages
     * @return Total number of messages
     */
    public long getNumMessages (){
        return getMessages().size();
    }
    /**
     * Count total number of messages
     * @return Total number of messages
     */
    public long getNumUsers (){
        return users.size();
    }

    /**
     * Average messages per user
     * @return Average number of mesagges per user
     */
    public Float getMessagesPerUser () {
        return ((float) getNumMessages()) / getNumUsers();
    }

    /**
     * Group messages per subject. Any user
     * @return HashMap containing groups of messages classified by subject
     */
    public HashMap<String, ArrayList<Message>> getGroupMessagesPerSubject(){
        HashMap<String, ArrayList<Message>> group = new HashMap<>();
        for (Message m : getMessages()){
            if (group.get(m.getSubject()) != null){
                group.get(m.getSubject()).add(m);
            }
            else{
                ArrayList<Message> newList = new ArrayList<>();
                newList.add(m);
                group.put(m.getSubject(), newList);
            }
        }
        return group;
    }

    /**
     * Count the words of all messages recieved by users with a particular name.
     * @param name Name of the users
     * @return Number of words used by given name
     */
    public long getNumWordsRecievedByName (String name){
        long num = 0;
        ArrayList<Message> messages = getMessages();
        for (Message m : messages){
            if (users.get(m.getReciever()).getName().equals(name))
                num += m.numWords();
        }
        return num;
    }
    /**
     * Count the words of all messages sent by users with a particular name.
     * @param name Name of users
     * @return Number of words used by given name
     */
    public long getNumWordsSentByName (String name){
        long num = 0;
        ArrayList<Message> messages = getMessages();
        for (Message m : messages){
            if (users.get(m.getSender()).getName().equals(name))
                num += m.numWords();
        }
        return num;
    }

    /**
     * Get messages to users born before a certain year
     * @param date Timestamp of birthdate
     * @return ArrayList of all the messages of users born before given timestamp
     */
    public ArrayList<Message> getMessagesOfUserBornBefore (Timestamp date) {
        ArrayList<Message> messages = new ArrayList<>();
        for (User u : getUsersBornBefore(date)){
            messages.addAll(mailboxes.get(u.getUsername()).getMessages());
        }
        return  messages;
    }
    /**
     * Get users born before a certain year
     * @param date Date to to compare with
     * @return ArrayList of all users born before given timestamp
     */
    public ArrayList<User> getUsersBornBefore (Timestamp date){
        return (ArrayList<User>) getUsers().stream().filter(u -> u.getDateOfBirth().before(date)).collect(Collectors.toList());
    }

    public ArrayList<String> getAllNames (){
        ArrayList<String> names = new ArrayList<>();
        for (User u : users.values()){
            if (!names.contains(u.getName()))
                names.add(u.getName());
        }
        return names;
    }

    public User getUser (String username){
        return users.get(username);
    }

    public MailStore getMailStore() {
        return store;
    }

    public ArrayList<Mailbox> getMailboxes() { return new ArrayList<>(mailboxes.values()); }

}
