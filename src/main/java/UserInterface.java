import java.util.InputMismatchException;
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


    public void start() {
        System.out.println("Welcome to the Superhero Database!");

        while (true) {
            try {
                displayMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createSuperhero();
                        break;
                    case 2:
                        exitAndSave();
                        return;
                    case 3:
                        searchSuperhero();
                        break;
                    case 4:
                        listSuperheroes();
                        break;
                    case 5:
                        saveSuperheroes();
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Create a new superhero");
        System.out.println("2) Exit");
        System.out.println("3) Search for a superhero");
        System.out.println("4) List of Heroes");
        System.out.println("5) Save superheroes");
        System.out.print("Please select an option: ");
    }

    private void createSuperhero() {
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
        database.saveSuperheroesToFile();
        System.out.println("Superheroes saved to 'superheroes.txt'.");
    }


    private void exitAndSave() {
        System.out.println("Exiting the Superhero Database.");
        // Save superheroes to the text file before exiting
        database.saveSuperheroesToFile();
        System.out.println("Superheroes saved to superheroes.txt.");
    }


    private void searchSuperhero() {
        System.out.print("Enter a superhero name (full or partial): ");
        scanner.nextLine(); // Consume newline
        String searchTerm = scanner.nextLine();
        database.searchAndDisplaySuperheroes(searchTerm);
    }

    private void listSuperheroes() {
        database.listSuperheroes();
    }

    private void saveSuperheroes() {
        System.out.print("Enter the filename to save the superheroes: ");
        scanner.nextLine(); // Consume newline
        String filename = scanner.nextLine();
        database.saveSuperheroesToFile();
        System.out.println("Superheroes saved to '" + filename + "'.");
    }

}
