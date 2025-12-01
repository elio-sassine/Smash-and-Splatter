/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.splatter;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;
import smashandsplatter.models.Trajectory;

/**
 *
 * @author eliob
 */
public class SplatterViewControllerTest {
    
    public SplatterViewControllerTest() {
    }

    /**
     * Test of initialize method, of class SplatterViewController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SplatterViewController instance = new SplatterViewController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrajectory method, of class SplatterViewController.
     */
    @Test
    public void testGetTrajectory() {
        System.out.println("getTrajectory");
        Trajectory expResult = null;
        Trajectory result = SplatterViewController.getTrajectory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
