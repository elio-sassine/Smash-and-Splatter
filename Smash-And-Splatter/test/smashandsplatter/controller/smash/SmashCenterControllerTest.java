/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.smash;

import javafx.animation.Animation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliob
 */
public class SmashCenterControllerTest {
    
    public SmashCenterControllerTest() {
    }

    /**
     * Test of initialize method, of class SmashCenterController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SmashCenterController instance = new SmashCenterController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rockFallingFail method, of class SmashCenterController.
     */
    @Test
    public void testRockFallingFail() {
        System.out.println("rockFallingFail");
        SmashCenterController instance = new SmashCenterController();
        Animation expResult = null;
        Animation result = instance.rockFallingFail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuccessAnimation method, of class SmashCenterController.
     */
    @Test
    public void testGetSuccessAnimation() {
        System.out.println("getSuccessAnimation");
        SmashCenterController instance = new SmashCenterController();
        Animation expResult = null;
        Animation result = instance.getSuccessAnimation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFailAnimation method, of class SmashCenterController.
     */
    @Test
    public void testGetFailAnimation() {
        System.out.println("getFailAnimation");
        SmashCenterController instance = new SmashCenterController();
        Animation expResult = null;
        Animation result = instance.getFailAnimation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
