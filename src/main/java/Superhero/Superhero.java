package Superhero;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Superhero implements Serializable {
    private String name;
    private String realName;
    private boolean isHuman;
    private int creationYear;
    private String strength;

    public Superhero(String name, String realName, boolean isHuman, int creationYear, String strength) {
        this.name = name;
        this.realName = realName;
        this.isHuman = isHuman;
        this.creationYear = creationYear;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Real Name: " + realName + ", Human: " + isHuman + ", Year: " + creationYear + ", Strength: " + strength;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public String getStrength() {
        return strength;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Use the default serialization for other fields
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Use the default deserialization for other fields
    }
}
