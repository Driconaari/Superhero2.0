package Database;

import Superhero.Superhero;

import java.io.*;
import java.util.*;


public class Database {
    private List<Superhero> superheroes;
    private String filePath; // Add a field for the file path
    private boolean dataChanged;

    public Database(String filePath) {
        this.filePath = filePath; // Initialize the file path
        superheroes = new ArrayList<>();
        loadSuperheroesFromFile(); // Load existing superheroes from the file
    }

    public void addSuperhero(Superhero superhero) {
        superheroes.add(superhero);
        dataChanged = true;  // Set dataChanged to true when adding a superhero
    }


    public void listSuperheroes() {
        System.out.println("Superheroes in the database:");
        for (Superhero superhero : superheroes) {
            System.out.println(superhero);
        }
    }

    public void saveSuperheroesToFile() {
        if (dataChanged) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("superheroes.txt"))) {
                objectOutputStream.writeObject(superheroes);
                System.out.println("Superheroes saved to 'superheroes.txt'.");
                dataChanged = false; // Reset the flag after saving
            } catch (IOException e) {
                System.out.println("Error saving superheroes to file: " + e.getMessage());
            }
        } else {
            System.out.println("No changes to superheroes data. File not overwritten.");
        }
    }




    public void loadSuperheroesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            superheroes = (List<Superhero>) ois.readObject();
            System.out.println("Superheroes loaded from file: " + filePath);
        } catch (FileNotFoundException e) {
            // Handle the case when the file doesn't exist yet (first-time run)
            System.out.println("No superheroes file found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading superheroes from file: " + e.getMessage());
        }
    }

    public void searchAndDisplaySuperheroes(String searchTerm) {
        System.out.println("Matching superheroes:");
        for (Superhero superhero : superheroes) {
            if (superhero.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(superhero);
            }
        }
    }

    public Superhero searchSuperheroByName(String name) {
        // Iterate through your database and find the superhero with the matching name
        for (Superhero superhero : superheroes) {
            if (superhero.getName().equalsIgnoreCase(name)) {
                return superhero;
            }
        }
        return null; // Return null if no superhero with the given name is found
    }

    public void sortSuperheroesByName() {
        // Use Collections.sort with a custom comparator
        Collections.sort(superheroes, Comparator.comparing(Superhero::getName));
    }

    public Superhero[] getSuperheroes() {
        // Convert the List to an array and return
        return superheroes.toArray(new Superhero[0]);
    }

    public void removeSuperhero(String superheroName) {
        // Find the superhero in the list
        Superhero superheroToRemove = searchSuperheroByName(superheroName);

        if (superheroToRemove != null) {
            // Remove the superhero from the list
            superheroes.remove(superheroToRemove);

            // Update the text file with the new list
            saveSuperheroesToFile();

            System.out.println("Superhero removed: " + superheroName);
        } else {
            System.out.println("Superhero not found: " + superheroName);
        }
    }

    public void sortSuperheroesByAttributes(String primaryAttribute, String secondaryAttribute) {
        // Validate the primary attribute before sorting
        if (!isValidAttribute(primaryAttribute)) {
            System.out.println("Invalid primary attribute. Please enter a valid attribute.");
            return;
        }

        // Define a comparator for the primary attribute
        Comparator<Superhero> primaryComparator = getComparator(primaryAttribute);

        // Sort the superheroes using the primary comparator
        superheroes.sort(primaryComparator);

        // Check if a secondary attribute is provided
        if (isValidAttribute(secondaryAttribute)) {
            // Define a comparator for the secondary attribute
            Comparator<Superhero> secondaryComparator = getComparator(secondaryAttribute);

            // Sort the superheroes using the secondary comparator
            superheroes.sort(secondaryComparator.thenComparing(primaryComparator));
        }

        // Display the sorted superheroes
        displaySuperheroes(superheroes);
    }



    // Helper method to get a comparator for a given attribute
    private Comparator<Superhero> getComparator(String attribute) {
        switch (attribute.toLowerCase()) {
            case "name":
                return Comparator.comparing(Superhero::getName);
            case "real name":
                return Comparator.comparing(Superhero::getRealName);
            case "ishuman":
                return Comparator.comparing(Superhero::isHuman);
            case "creationyear":
                return Comparator.comparingInt(Superhero::getCreationYear);
            case "strength":
                return Comparator.comparing(Superhero::getStrength);
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }



    private void displaySuperheroes(List<Superhero> superheroes) {
        if (superheroes.isEmpty()) {
            System.out.println("No superheroes to display.");
        } else {
            System.out.println("Superheroes:");
            for (Superhero superhero : superheroes) {
                System.out.println(superhero);
            }
        }
    }


    private boolean isValidAttribute(String attribute) {
        // Check if the attribute is null or empty
        if (attribute == null || attribute.isEmpty()) {
            return false;
        }

        // Convert the attribute to lowercase for case-insensitive comparison
        String lowerCaseAttribute = attribute.toLowerCase();

        // List of valid attributes for sorting
        List<String> validAttributes = Arrays.asList("name", "realname", "ishuman", "creationyear", "strength");

        // Check if the provided attribute is in the list of valid attributes
        return validAttributes.contains(lowerCaseAttribute);
    }


    public void sortSuperheroesByAttribute(String attribute) {
        try {
            // Validate the attribute before sorting
            if (!isValidAttribute(attribute)) {
                throw new IllegalArgumentException("Invalid attribute. Please enter a valid attribute.");
            }

            // Use a lambda expression to define a comparator based on the attribute
            Comparator<Superhero> comparator = getComparator(attribute);

            // Sort the superheroes using the chosen comparator
            superheroes.sort(comparator);

            // Display the sorted superheroes
            displaySuperheroes(superheroes);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

