package Superhero;

import java.io.*;
public class Superhero implements Serializable {
    private static final long serialVersionUID = 123456789L;
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

    public void setName(String newName) {
        this.name = newName;
    }

    public void setRealName(String newRealName) {
        this.realName = newRealName;
    }

    public void setHuman(boolean newIsHuman) {
        this.isHuman = newIsHuman;
    }

    public void setCreationYear(int newCreationYear) {
        this.creationYear = newCreationYear;
    }

    public void setStrength(String newStrength) {
        this.strength = newStrength;
    }

    public String getRealName() {
        return realName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public String getStrength() {
        return strength;
    }

    public boolean isHuman() {
        return isHuman;
    }
}
