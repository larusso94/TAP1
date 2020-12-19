package Part1;

import java.io.IOException;
import java.util.Scanner;

public class CLI {

    private Scanner sc = new Scanner(System.in);
    private static User user = null;

    private static void printHeader(){

    }
    private static int printLoginMenu(){
        printHeader();
        System.out.println("||  1. Login            ");
        System.out.println("||  2. CreateUser       ");
        System.out.println("||  3. Filter Messages  ");
        int swValue = Keyin.inInt(" Select option: ");
        while(swValue < 0 || swValue > 3){
            System.out.println("Error, select option between 1 and 3");
            swValue = Keyin.inInt(" Select option: ");
        }
        return swValue;
    }
    private static int printUserMenu(){
        printHeader();
        System.out.println("||  1. Login            ");
        System.out.println("||  2. CreateUser       ");
        System.out.println("||  3. Filter Messages  ");
        int swValue = Keyin.inInt(" Select option: ");
        while(swValue < 0 || swValue > 3){
            System.out.println("Error, select option between 1 and 3");
            swValue = Keyin.inInt(" Select option: ");
        }
        return swValue;
    }

    public static void main(String[] args) throws IOException {
        boolean end = false;

        while (!end){
            if (user == null){
                switch(printLoginMenu()){
                    case 1:
                        System.out.println("login");
                        break;
                    case 2:
                        System.out.println("create");
                        break;
                    case 3:
                        System.out.println("filter messages");
                        break;
                }
            }
            else{
                printLoginMenu();
                switch()){
                    case 1:
                        System.out.println("login");
                        break;
                    case 2:
                        System.out.println("create");
                        break;
                }
            }
        }
    }


}
