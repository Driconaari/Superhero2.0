package Controller;

import Database.Database;
import Superhero.Superhero;

import java.util.Arrays;
import java.util.Comparator;

public class Controller {
    private Database database;

    public Controller(Database database) {
        this.database = database;
    }
    public Superhero[] getSuperheroesSortedByName() {
        // Assuming Database has a method to get superheroes
        Superhero[] superheroes = database.getSuperheroes();
        // Sort the superheroes by name
        Arrays.sort(superheroes, Comparator.comparing(Superhero::getName));
        return superheroes;
    }

    public void createSuperhero(String name, String realName, boolean isHuman, int creationYear, String strength) {
        // Create a Superhero object
        Superhero superhero = new Superhero(name, realName, isHuman, creationYear, strength);

        // Add the superhero to the database
        database.addSuperhero(superhero);
    }

    public void saveSuperheroesToFile(String filename) {
        database.saveSuperheroesToFile();
    }

    public void loadSuperheroesFromFile(String filename) {
        database.loadSuperheroesFromFile();
    }

    public void searchSuperhero(String searchTerm) {
        database.searchAndDisplaySuperheroes(searchTerm);
    }
}


