package org.example.model;
import java.util.Objects;

public class Person {
    private int ID;
    private String name;
    private String surname;

    public Person(int ID, String name, String surname){
        this.ID=ID;
        this.name=name;
        this.surname=surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + ID +
                ", username='" + name + '\'' +
                ", password='" + surname + '\'' +
                '}';
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
