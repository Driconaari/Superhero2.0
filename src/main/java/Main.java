import java.util.Scanner;
import Database.Database;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        UserInterface ui = new UserInterface(database, scanner);
        ui.start();
    }
}
