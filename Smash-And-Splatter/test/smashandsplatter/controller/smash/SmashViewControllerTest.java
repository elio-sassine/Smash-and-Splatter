/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;
import smashandsplatter.models.Torque;

/**
 *
 * @author eliob
 */
public class SmashViewControllerTest {
    
    public SmashViewControllerTest() {
    }

    /**
     * Test of initialize method, of class SmashViewController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SmashViewController instance = new SmashViewController();
        instance.initialize(url, rb);

        assertNotNull(instance);
    }

    /**
     * Test of getTorque1 method, of class SmashViewController.
     */
    @Test
    public void testGetTorque1() {
        System.out.println("getTorque1");
        Torque expResult = null;
        Torque result = SmashViewController.getTorque1();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTorque2 method, of class SmashViewController.
     */
    @Test
    public void testGetTorque2() {
        System.out.println("getTorque2");
        Torque expResult = null;
        Torque result = SmashViewController.getTorque2();
        assertEquals(expResult, result);
    }
    
}
