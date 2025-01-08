package org.example.repo;


import org.example.model.Entity;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class InFileRepository<T extends Entity> implements IRepository<T> {

    private final String filePath;

    /**
     * Represents a Map that stores the class and then an instance of InMemoryRepo.
     * Used to keep the same instance over all Services and Controllers throughout the App
     */
    private static final Map<Class<?>, InFileRepository<?>> instances = new HashMap<>();

    /***
     * Sets the filepath for the repository
     * @param filePath to the file that the data should be stored in
     */
    public InFileRepository(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds an object to the file
     * @param obj to be added
     */
    @Override
    public void create(T obj) {
        doInFile(data -> data.putIfAbsent(obj.getId(), obj));
    }

    /**
     * Searches for an object in the repository
     * @param id of the searched object
     * @return the found object, or null if there is no object found
     */
    @Override
    public T read(int id) {
        return readDataFromFile().get(id);
    }

    /**
     * Updates the data of an object
     * @param obj the new obj that will be saved
     */
    @Override
    public void update(T obj) {
        doInFile(data -> data.replace(obj.getId(), obj));
    }

    /**
     * Removes data from the repository
     * @param id of the object to be removed
     */
    @Override
    public void delete(int id) {
        doInFile(data -> data.remove(id));
    }

    /**
     * @return All the data found in the repository
     */
    @Override
    public List<T> getAll() {
        return readDataFromFile().values().stream().collect(Collectors.toList());
    }

    /**
     * Keeps the data of the repo and the Map in sync
     * @param function function to be executed
     */
    private void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> data = readDataFromFile();
        function.accept(data);
        writeDataToFile(data);
    }

    /**
     * Reads data from file
     * @return Map object that holds an Integer(simulates an id) and the object
     */
    private Map<Integer, T> readDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Integer, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    /**
     * Writes to the repository
     * @param data the data that is written in the
     */
    private void writeDataToFile(Map<Integer, T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Used for keeping the same instance of InMemoryRepo
     * @param type data type of the Repo
     * @return instance of InMemoryRepo that uses type parameter
     * @param <T> class that inherits Entity
     * @see Entity
     */
    @SuppressWarnings("unchecked")
    public static synchronized <T extends Entity> InFileRepository<T> getInstance(Class<T> type, String filePath) {

        //Checks if an instance of this type of Class exists
        if (!instances.containsKey(type)) {
            instances.put(type, new InFileRepository<>(filePath));
        }
        return (InFileRepository<T>) instances.get(type);
    }
}
