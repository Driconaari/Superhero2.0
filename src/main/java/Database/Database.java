package Database;

import Superhero.Superhero;

import java.io.*;
import java.util.*;


public class Database {
    private List<Superhero> superheroes;
    private String filePath;
    private boolean dataChanged;

    public Database(String filePath) {
        this.filePath = filePath;
        superheroes = new ArrayList<>();
        loadSuperheroesFromFile();
    }

    public void addSuperhero(Superhero superhero) {
        superheroes.add(superhero);
        dataChanged = true;
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
                dataChanged = false;
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

        for (Superhero superhero : superheroes) {
            if (superhero.getName().equalsIgnoreCase(name)) {
                return superhero;
            }
        }
        return null;
    }

    public Superhero[] getSuperheroes() {

        return superheroes.toArray(new Superhero[0]);
    }

    public void removeSuperhero(String superheroName) {

        Superhero superheroToRemove = searchSuperheroByName(superheroName);

        if (superheroToRemove != null) {

            superheroes.remove(superheroToRemove);
            dataChanged=true;


            saveSuperheroesToFile();

            System.out.println("Superhero removed: " + superheroName);

        } else {
            System.out.println("Superhero not found: " + superheroName);
        }
    }

    public void sortSuperheroesByAttributes(String primaryAttribute, String secondaryAttribute) {

        if (!isValidAttribute(primaryAttribute)) {
            System.out.println("Invalid primary attribute. Please enter a valid attribute.");
            return;
        }

        Comparator<Superhero> primaryComparator = getComparator(primaryAttribute);


        superheroes.sort(primaryComparator);

        if (isValidAttribute(secondaryAttribute)) {
            Comparator<Superhero> secondaryComparator = getComparator(secondaryAttribute);

            superheroes.sort(secondaryComparator.thenComparing(primaryComparator));
        }

        displaySuperheroes(superheroes);
    }


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

        if (attribute == null || attribute.isEmpty()) {
            return false;
        }


        String lowerCaseAttribute = attribute.toLowerCase();


        List<String> validAttributes = Arrays.asList("name", "realname", "ishuman", "creationyear", "strength");


        return validAttributes.contains(lowerCaseAttribute);
    }


}

