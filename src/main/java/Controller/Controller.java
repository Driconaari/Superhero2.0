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

        Superhero[] superheroes = database.getSuperheroes();

        Arrays.sort(superheroes, Comparator.comparing(Superhero::getName));
        return superheroes;
    }


}


