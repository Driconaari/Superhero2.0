package Database;

import Superhero.Superhero;

import java.io.*;

public class Database {
    private Superhero[] superheroes;
    private int count;

    // Constructor for initializing the database
    public Database() {
        superheroes = new Superhero[5]; // You can adjust the size as needed
        count = 0;
    }


    public void addSuperhero(Superhero superhero) {
        if (count < superheroes.length) {
            superheroes[count++] = superhero;
        } else {
            System.out.println("Database is full. Cannot add more superheroes.");
        }
    }

    public void saveSuperheroesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Superhero superhero : superheroes) {
                if (superhero != null) {
                    // Write superhero details to the text file
                    writer.println("Name: " + superhero.getName());
                    writer.println("Real Name: " + superhero.getRealName());
                    writer.println("Is Human: " + superhero.isHuman());
                    writer.println("Creation Year: " + superhero.getCreationYear());
                    writer.println("Strength: " + superhero.getStrength());
                    writer.println(); // Add a separator between superheroes
                }
            }
            System.out.println("Superheroes saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving superheroes to file: " + e.getMessage());
        }
    }



    public void loadSuperheroesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String name = line.split(": ")[1];
                String realName = reader.readLine().split(": ")[1];
                boolean isHuman = Boolean.parseBoolean(reader.readLine().split(": ")[1]);
                int creationYear = Integer.parseInt(reader.readLine().split(": ")[1]);
                String strength = reader.readLine().split(": ")[1];
                reader.readLine(); // Consume the empty line

                Superhero superhero = new Superhero(name, realName, isHuman, creationYear, strength);
                addSuperhero(superhero);
            }
            System.out.println("Superheroes loaded from file: " + filename);
        } catch (IOException e) {
            System.err.println("Error loading superheroes from file: " + e.getMessage());
        }
    }




    public void searchAndDisplaySuperheroes(String searchTerm) {
        System.out.println("Matching superheroes:");
        for (Superhero superhero : superheroes) {
            if (superhero != null && superhero.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                // Display the matching superhero
                System.out.println("Name: " + superhero.getName());
                System.out.println("Real Name: " + superhero.getRealName());
                System.out.println("Is Human: " + superhero.isHuman());
                System.out.println("Creation Year: " + superhero.getCreationYear());
                System.out.println("Strength: " + superhero.getStrength());
                System.out.println(); // Add a separator
            }
        }
    }

    public void listSuperheroes() {
        System.out.println("List of all superheroes:");
        for (Superhero superhero : superheroes) {
            if (superhero != null) {
                // Display the superhero details
                System.out.println("Name: " + superhero.getName());
                System.out.println("Real Name: " + superhero.getRealName());
                System.out.println("Is Human: " + superhero.isHuman());
                System.out.println("Creation Year: " + superhero.getCreationYear());
                System.out.println("Strength: " + superhero.getStrength());
                System.out.println(); // Add a separator
            }
        }
    }
}

