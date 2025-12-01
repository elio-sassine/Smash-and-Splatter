/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smashandsplatter.models;

import java.util.Random;

/**
 * A class representing Torque
 * @author eliob
 */
public class Torque {
    // in N
    private double force;
    // in m
    private double distance;
    // in degrees
    private double angle;
    // in Nm
    private double torque;
    
    /**
     * Makes a torque with random values
     */
    public Torque() {
        Random rand = new Random();
        
        this.force = rand.nextDouble(0.1, 100);
        // rounds to 2 digits, will be used again
        this.force = Math.round(this.force * 100) / 100.0;
        
        this.distance = rand.nextDouble(0.1, 3);
        this.distance = Math.round(this.distance * 100) / 100.0;
        
        this.angle = rand.nextDouble(0.1, 180);
        // round to 1 digit, angle is in degrees
        this.angle = Math.round(this.angle * 10) / 10.0;
        
        // formula for this is 
        // rF sin(theta)
        // with r as distance, F as force, and theta as the angle
        this.torque = this.force * this.distance * Math.sin(Math.toRadians(angle));
        this.torque = Math.round(this.torque * 100) / 100.0;

    }

    /**
     * Makes a torque with pre-defined values (Only used for testing)
     * @param force force in N
     * @param distance distance in m
     * @param angle angle in degrees
     */
    public Torque(double force, double distance, double angle) {
        this.force = force;
        this.distance = distance;
        this.angle = angle;
        
        this.torque = this.force * this.distance * Math.sin(Math.toRadians(angle));
    }

    /**
     * gets the force
     * @return force in N
     */
    public double getForce() {
        return force;
    }

    /**
     * gets the distance
     * @return distance in m
     */
    public double getDistance() {
        return distance;
    }

    /**
     * gets the angle
     * @return angle in degrees
     */
    public double getAngle() {
        return angle;
    }

    /**
     * gets the torque
     * @return torque in Nm
     */
    public double getTorque() {
        return torque;
    }    

    /**
     * gets the string representation of the force
     * @return force to string
     */
    @Override
    public String toString() {
        // place holder
        return "Torque{" + "force=" + force + ", distance=" + distance + ", angle=" + angle + ", torque=" + torque + '}';
    }
}
