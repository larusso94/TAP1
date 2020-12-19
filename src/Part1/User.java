package Part1;

import java.sql.Timestamp;

public class User {
    private String username;
    private String name;
    private Timestamp birth;

    public User(String username, String name, Timestamp date_of_birth) {
        this.username = username;
        this.name = name;
        this.birth = date_of_birth;
    }

    public String getUsername() { return username; }
    public String getName() { return name; }
    public Timestamp getDateOfBirth() { return birth; }

    public void setUsername(String username) { this.username = username; }
    public void setName(String name) { this.name = name; }
    public void setDateOfBirth(Timestamp date_of_birth) { this.birth = date_of_birth; }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", date_of_birth=" + birth +
                '}';
    }
}
