package Database;

import Superhero.Superhero;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Database {
    private List<Superhero> superheroes;
    private String filePath; // Add a field for the file path

    public Database(String filePath) {
        this.filePath = filePath; // Initialize the file path
        superheroes = new ArrayList<>();
        loadSuperheroesFromFile();
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
}
