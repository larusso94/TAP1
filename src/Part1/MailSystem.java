package Part1;

import java.util.Date;
import java.util.Hashtable;

public class MailSystem {
    private MailStore mailStore;
    private Hashtable<String, Mailbox> mailboxes;

    public MailSystem (MailStore store){
        this.mailStore = store;
        this.mailboxes = new Hashtable<String,Mailbox>();
    }

    public MailStore getMailStore() {
        return mailStore;
    }

    public Hashtable<String, Mailbox> getMailboxes() {
        return mailboxes;
    }

    public void setMailStore(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public void setMailboxes(Hashtable<String, Mailbox> mailboxes) {
        this.mailboxes = mailboxes;
    }

    public Mailbox createUser( String username, String name, Date birth_date){
        this.mailboxes.put(username, new Mailbox(this.mailStore, new User(this.mailStore, username, name, birth_date)));
        return this.mailboxes.get(username);
    }
}
