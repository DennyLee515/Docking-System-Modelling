package Model;/*
 * Modelling Complex Software Systems_SWEN90004_2019
 * Assignment 1a
 * Student:Dongming Li
 * ID:1002971
 */

public class Operator extends Thread {

    private Berth berth;

    // create a new operator with berth instance
    public Operator(Berth berth) {
        this.berth = berth;
    }

    @Override
    //repeatedly active and deactive the shield
    public void run() {
        try {
            while (!interrupted()) {
                // let some time pass before the next debris comes
                sleep(Params.debrisLapse());
                activate();
                // let some time pass before deactivating the shield
                sleep(Params.DEBRIS_TIME);
                deactivate();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //activate the shield of the berth
    public void activate() {
        berth.activateShield();
        System.out.println("Shield is activated.");
    }

    //deactivate the shield of the berth
    public void deactivate() {
        berth.deactivateShield();
        //notify waith thread
        berth.notifyMethod();
        System.out.println("Shield is deactivated.");
    }

}
