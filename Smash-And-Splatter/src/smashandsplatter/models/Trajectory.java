/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smashandsplatter.models;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author eliob
 */
public class Trajectory {
    private double[] initialVelocity = new double[2];
    private double[] finalVelocity = new double[2];
    private final double yPos;
    private final double distance;
    private final double time;
    private static final double GRAVITY = -9.8;
    
    /**
     * Makes a trajectory model with randomized fields
     */
    public Trajectory() {
        Random rand = new Random();
        this.yPos = Math.round(rand.nextDouble(10, 50) * 100) / 100.0;
        this.distance = Math.round(rand.nextDouble(10, 50) * 100) / 100.0;
        
        this.initialVelocity[1] = Math.round(rand.nextDouble(10, 50) * 100) / 100.0;
        
        this.time = computeTime();
        this.initialVelocity[0] = computeInitialHorizontalVelocity();
        this.finalVelocity = calculateFinalVelocity();
    }

    public Trajectory(double yPos, double distance, double time, double[] initialVelocity, double[] finalVelocity) {
        this.yPos = yPos;
        this.distance = distance;
        this.time = time;
        this.initialVelocity = initialVelocity;
        this.finalVelocity = finalVelocity;
    }
    
    private double[] calculateFinalVelocity() {
        double[] finalVelocity = new double[2];
        finalVelocity[0] = this.initialVelocity[0];
        finalVelocity[1] = computeFinalYVelocity();
        
        return finalVelocity;
    };
    
    /**
     * computes the time of flight given initial y velocity, gravity and height
     * @return time of flight
     */
    private double computeTime() {
        double inside = initialVelocity[1] * initialVelocity[1] - 2 * GRAVITY * yPos;
        double t = (-initialVelocity[1] - Math.sqrt(inside)) / GRAVITY;
        return round2(t);
    }

    /**
     * computes the initial x velocity given distance and time
     * @return initial (and final) x velocity
     */
    private double computeInitialHorizontalVelocity() {
        return round2(distance / time);
    }

    /**
     * Compute the final velocity given the gravity and time
     * @return final y velocity
     */
    private double computeFinalYVelocity() {
        return round2(initialVelocity[1] + GRAVITY * time);
    }
    
    /**
     * rounds an input to 2 digits
     * @param v the input to round to 2 digits
     * @return v rounded to 2 digits
     */
    public static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    /**
     * gets the initial velocity as a vector (as an [x, y] array)
     * @return initial velocity as a vector
     */
    public double[] getInitialVelocity() {
        return initialVelocity;
    }

    /**
     * gets the final velocity vector
     * @return final velocity vector (as an [x, y] array) 
     */
    public double[] getFinalVelocity() {
        return finalVelocity;
    }

    /**
     * gets the y position (height)
     * @return y position (height)
     */
    public double getyPos() {
        return yPos;
    }
    
    /**
     * gets the distance (x)
     * @return distance (x)
     */
    public double getDistance() {
        return distance;
    }

    /**
     * gets the time of the trajectory
     * @return the time of the trajectory
     */
    public double getTime() {
        return time;
    }

    /**
     * makes a string of the trajectory class
     * @return a string representation of the class
     */
    @Override
    public String toString() {
        return "Trajectory{" + "initialVelocity=" + Arrays.toString(initialVelocity) + ", finalVelocity=" + Arrays.toString(finalVelocity) + ", yPos=" + yPos + ", distance=" + distance + ", time=" + time + '}';
    }
}
