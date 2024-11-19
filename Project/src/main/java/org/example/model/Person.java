package org.example.model;

/**
 * The {@code Person} class is an abstract class that represents a general person
 * with a name and an ID. It serves as a base class for other classes, such as {@code Student},
 * which extend and specialize the person-related features.
 */
public abstract class Person {
    /** The name of the person */
    protected String name;
    /** The ID of the person */
    protected Integer id;

    /**
     * Constructs a new {@code Person} with the specified ID and name.
     *
     * @param id   The unique identifier for the person.
     * @param name The name of the person.
     */
    protected Person(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the person.
     *
     * @return The ID of the person.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id The new ID of the person.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
