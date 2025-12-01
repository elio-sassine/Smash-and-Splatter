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
        
        assertNotNull(instance);
    }

    /**
     * Test of rockFallingFail method, of class SmashCenterController.
     */
    @Test
    public void testRockFallingFail() {
        System.out.println("rockFallingFail");
        SmashCenterController instance = new SmashCenterController();
        Class expResult = Animation.class;
        Class result = instance.rockFallingFail().getClass();
        assertEquals(expResult, result); 
    }

    /**
     * Test of getSuccessAnimation method, of class SmashCenterController.
     */
    @Test
    public void testGetSuccessAnimation() {
        System.out.println("getSuccessAnimation");
        SmashCenterController instance = new SmashCenterController();
        Class expResult = Animation.class;
        Class result = instance.getSuccessAnimation().getClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFailAnimation method, of class SmashCenterController.
     */
    @Test
    public void testGetFailAnimation() {
        System.out.println("getFailAnimation");
        SmashCenterController instance = new SmashCenterController();
        Class expResult = Animation.class;
        Class result = instance.getFailAnimation().getClass();
        assertEquals(expResult, result);
    }
    
}
