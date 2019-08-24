package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

public class Tugs {

    volatile private int availTugs;

    // create a new vessel with a given number
    public Tugs(int numTugs) {
        this.availTugs = numTugs;
    }

    //a synchronized method for pilot to acquire tugs before docking and
    // undocking
    public synchronized void acquire(int num) {
        //if the number of acquired tugs is less than available tugs
        while (num > availTugs) {
            try {
                //let threads wait for released tugs
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        availTugs = availTugs - num;
    }

    //a synchronized method for pilot to release tugs after docking and
    // undocking
    public synchronized void release(int num) {
        availTugs = availTugs + num;
        //notify threads which are waiting for tugs
        notifyAll();
    }

    //return number of available tugs
    public int getAvailTugs() {
        return availTugs;
    }
}
