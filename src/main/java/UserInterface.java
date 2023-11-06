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
                        editSuperhero();
                        break;
                    case 3:
                        searchSuperhero();
                        break;
                    case 4:
                        listSuperheroes();
                        break;
                    case 5:
                        saveSuperheroes();
                        break;
                    case 6:
                        exitAndSave();
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
        System.out.println("2) Edit a superhero");
        System.out.println("3) Search for a superhero");
        System.out.println("4) listSuperheroes");
        System.out.println("5) saveSuperheroes");
        System.out.println("6) Exit with save");
        System.out.print("Please select an option: ");
    }


    private void createSuperhero() {
        // Collect superhero information from the user
        System.out.print("Enter superhero name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter real name: ");
        String realName = scanner.nextLine();

        boolean isHuman;
        while (true) {
            System.out.print("Is the superhero human? (true/false): ");
            String isHumanInput = scanner.nextLine();

            if (isHumanInput.equalsIgnoreCase("true")) {
                isHuman = true;
                break; // Valid input, exit the loop
            } else if (isHumanInput.equalsIgnoreCase("false")) {
                isHuman = false;
                break; // Valid input, exit the loop
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }


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

    private void editSuperhero() {
        System.out.print("Enter the name of the superhero you want to edit: ");
        scanner.nextLine(); // Consume newline
        String nameToEdit = scanner.nextLine();

        // Search for the superhero by name
        Superhero superheroToEdit = database.searchSuperheroByName(nameToEdit);

        if (superheroToEdit != null) {
            System.out.println("Editing superhero: " + superheroToEdit.getName());
            editSuperheroAttributes(superheroToEdit, scanner); // Call the editSuperheroAttributes method
            database.saveSuperheroesToFile(); // Save the updated superhero to the text file
            System.out.println("Superhero has been edited and saved.");
        } else {
            System.out.println("Superhero not found. Please enter a valid superhero name.");
        }
    }

    public void editSuperheroAttributes(Superhero superhero, Scanner scanner) {
        System.out.println("Editing superhero attributes:");

        // Edit superhero name
        System.out.print("Enter the new superhero name (or press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            superhero.setName(newName);
        }

        // Edit real name
        System.out.print("Enter the new real name (or press Enter to keep current): ");
        String newRealName = scanner.nextLine();
        if (!newRealName.isEmpty()) {
            superhero.setRealName(newRealName);
        }

        // Edit isHuman
        System.out.print("Is the superhero human? (true/false) (or press Enter to keep current): ");
        String isHumanInput = scanner.nextLine();
        if (!isHumanInput.isEmpty()) {
            boolean newIsHuman = Boolean.parseBoolean(isHumanInput);
            superhero.setHuman(newIsHuman);
        }

        // Edit creation year
        System.out.print("Enter the new creation year (or press Enter to keep current): ");
        String creationYearInput = scanner.nextLine();
        if (!creationYearInput.isEmpty()) {
            int newCreationYear = Integer.parseInt(creationYearInput);
            superhero.setCreationYear(newCreationYear);
        }

        // Edit strength
        System.out.print("Enter the new strength (or press Enter to keep current): ");
        String newStrength = scanner.nextLine();
        if (!newStrength.isEmpty()) {
            superhero.setStrength(newStrength);
        }
    }
}


