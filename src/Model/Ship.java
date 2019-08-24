package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

/**
 * A cargo ship, with a unique id, that arrives at
 * the space station to deliver its cargo.
 *
 * @author ngeard@unimelb.edu.au
 */

public class Ship {

    // a unique identifier for this cargo ship
    private int id;

    // the next ID to be allocated
    private static int nextId = 1;

    // a flag indicating whether the ship is currently loaded
    boolean loaded;

    // create a new vessel with a given identifier
    private Ship(int id) {
        this.id = id;
        this.loaded = true;
    }

    // get a new Model.Ship instance with a unique identifier
    public static Ship getNewShip() {
        return new Ship(nextId++);
    }

    // produce an identifying string for the cargo ship
    public String toString() {
        return "ship [" + id + "]";
    }

    //change the flag to "false" and print the statement
    public void unload(){
        this.loaded = false;
        System.out.println(this.toString()+ " being " +
                "unloaded.");
    }

}
