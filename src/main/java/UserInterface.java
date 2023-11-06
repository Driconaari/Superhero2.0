import java.util.Scanner;
import Database.Database;
import Superhero.Superhero;

public class UserInterface {
    private Database database;
    private Scanner scanner;

    public UserInterface(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Create a new superhero");
        System.out.println("2) Exit");
        System.out.println("3) Search for a superhero");
        System.out.println("4) List of Heroes");
        System.out.print("Please select an option: ");
    }

    public void createSuperhero() {
        // Collect superhero information from the user
        System.out.print("Enter superhero name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter real name: ");
        String realName = scanner.nextLine();

        System.out.print("Is the superhero human? (true/false): ");
        boolean isHuman = scanner.nextBoolean();

        System.out.print("Enter creation year: ");
        int creationYear = scanner.nextInt();

        System.out.print("Enter strength: ");
        scanner.nextLine(); // Consume newline
        String strength = scanner.nextLine();

        // Create a Superhero object
        Superhero superhero = new Superhero(name, realName, isHuman, creationYear, strength);

        // Add the superhero to the database
        database.addSuperhero(superhero);
        System.out.println("Superhero added to the database.");

        // Save superheroes to the text file after adding a superhero
        database.saveSuperheroesToFile("superheroes.txt");
    }

    public void exitAndSave() {
        // Exit the program and save superheroes
        System.out.println("Exiting the Superhero Database.");
        // Save superheroes to the text file before exiting
        database.saveSuperheroesToFile("superheroes.txt");
    }

    public void searchSuperhero() {
        // Search for a superhero by name
        System.out.print("Enter a superhero name (full or partial): ");
        scanner.nextLine(); // Consume newline
        String searchTerm = scanner.nextLine();
        database.searchAndDisplaySuperheroes(searchTerm);
    }

    public void listSuperheroes() {
        // Display a list of all superheroes
        database.listSuperheroes();
    }

    public void runMainLoop() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            if (choice == 1) {
                createSuperhero();
            } else if (choice == 2) {
                exitAndSave();
                break;
            } else if (choice == 3) {
                searchSuperhero();
            } else if (choice == 4) {
                listSuperheroes();
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

