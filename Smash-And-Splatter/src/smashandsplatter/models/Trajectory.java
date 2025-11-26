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
    
    public Trajectory() {
        Random rand = new Random();
        this.initialVelocity[0] = Math.round(rand.nextDouble(0.1, 40) * 100) / 100.0;
        this.initialVelocity[1] = Math.round(rand.nextDouble(0.1, 40) * 100) / 100.0;
        
        this.time = Math.round(rand.nextDouble(0.1, 10) * 100) / 100.0;
        
        this.finalVelocity = calculateFinalVelocity();
        this.yPos = calculateYPos();
        this.distance = calculateDistance();
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

    public double[] getInitialVelocity() {
        return initialVelocity;
    }

    public double[] getFinalVelocity() {
        return finalVelocity;
    }

    public double getyPos() {
        return yPos;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Trajectory{" + "initialVelocity=" + initialVelocity + ", finalVelocity=" + finalVelocity + ", yPos=" + yPos + ", distance=" + distance + ", time=" + time + '}';
    }
    
    
}
