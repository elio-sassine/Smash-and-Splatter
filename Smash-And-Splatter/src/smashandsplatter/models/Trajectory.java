/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smashandsplatter.models;

import java.util.Random;

/**
 *
 * @author eliob
 */
public class Trajectory {
    private double[] initialVelocity = new double[2];
    private double[] finalVelocity = new double[2];
    private double yPos;
    private double distance;
    private double time;
    private static final double GRAVITY = -9.8;
    
    /**
     * Makes a trajectory model with randomized fields
     */
    public Trajectory() {
        Random rand = new Random();
        this.initialVelocity[0] = Math.round(rand.nextDouble(0.1, 40) * 100) / 100.0;
        this.initialVelocity[1] = Math.round(rand.nextDouble(0.1, 40) * 100) / 100.0;
        
        this.time = Math.round(rand.nextDouble(0.1, 10) * 100) / 100.0;
        
        this.finalVelocity = calculateFinalVelocity();
        this.yPos = calculateYPos();
        this.distance = calculateDistance();
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
        finalVelocity[1] = this.initialVelocity[1] + this.GRAVITY * this.time;
        
        return finalVelocity;
    };
    
    private double calculateYPos() {
        return 0.5 * (initialVelocity[1] + finalVelocity[1]) * time; 
    }
    
    private double calculateDistance() {
        return initialVelocity[0] * time;
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
        return "Trajectory{" + "initialVelocity=" + initialVelocity + ", finalVelocity=" + finalVelocity + ", yPos=" + yPos + ", distance=" + distance + ", time=" + time + '}';
    }
}
