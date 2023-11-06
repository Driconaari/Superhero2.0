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


}


