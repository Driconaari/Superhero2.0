package Database;

import Superhero.Superhero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Superhero> superheroes;
    private String filePath; // Add a field for the file path

    public Database(String filePath) {
        this.filePath = filePath; // Initialize the file path
        superheroes = new ArrayList<>();
        loadSuperheroesFromFile(); // Load existing superheroes from the file
    }

    public void addSuperhero(Superhero superhero) {
        superheroes.add(superhero);
    }

    public void listSuperheroes() {
        System.out.println("Superheroes in the database:");
        for (Superhero superhero : superheroes) {
            System.out.println(superhero);
        }
    }

    public void saveSuperheroesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(superheroes);
            System.out.println("Superheroes saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving superheroes to file: " + e.getMessage());
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
}
