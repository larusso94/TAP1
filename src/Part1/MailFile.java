package Part1;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MailFile implements MailStore{
    private final String fileName;
    private static int count = 0;

    public MailFile(String name){
        this.fileName = name;
    }

    public MailFile(){
        this.fileName = "MailStore_"+count+".txt";
        count++;
    }

    public void sendMail(Message message) throws IOException {
        FileWriter file = new FileWriter(this.fileName, true);
        BufferedWriter buffer = new BufferedWriter(file);

        buffer.write(message.getSender()+
                ";"+message.getSubject()+
                ";"+message.getBody()+
                ";"+message.getReciever()+
                ";"+message.getCreation()+
                "\n"
        );
        buffer.close();
        file.close();
    }
    public ArrayList<Message> getMail(String user) throws IOException{
        FileReader file = new FileReader(this.fileName);
        BufferedReader buffer = new BufferedReader(file);

        ArrayList<Message> messages = new ArrayList<>();

        String line;
        while( (line=buffer.readLine() ) != null){
            StringTokenizer str = new StringTokenizer(line, ";");
            while(str.hasMoreTokens()){
                String token = str.nextToken();
                if(token.equals(user)){
                    Message temp = new Message(
                            str.nextToken(),
                            str.nextToken(),
                            user,
                            str.nextToken(),
                            Timestamp.valueOf(str.nextToken()));
                    messages.add(temp);
                }
                else{
                    break;
                }
            }
        }
        buffer.close();
        file.close();
        return messages;
    }
}
