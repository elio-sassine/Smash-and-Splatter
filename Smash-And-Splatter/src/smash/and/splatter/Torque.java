/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smash.and.splatter;

import java.util.Random;

/**
 *
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

    public Torque(double force, double distance, double angle) {
        this.force = force;
        this.distance = distance;
        this.angle = angle;
        
        this.torque = this.force * this.distance * Math.sin(Math.toRadians(angle));
    }

    public double getForce() {
        return force;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }

    public double getTorque() {
        return torque;
    }    

    @Override
    public String toString() {
        // place holder
        return "Torque{" + "force=" + force + ", distance=" + distance + ", angle=" + angle + ", torque=" + torque + '}';
    }
}
