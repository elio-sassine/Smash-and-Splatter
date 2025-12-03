/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.splatter;

import javafx.animation.Animation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliob
 */
public class SplatterCenterControllerTest {
    
    public SplatterCenterControllerTest() {
    }

    /**
     * Test of initialize method, of class SplatterCenterController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SplatterCenterController instance = new SplatterCenterController();
        instance.initialize();
        
        assertNotNull(instance);
    }

    /**
     * Test of getFinalFailAnimation method, of class SplatterCenterController.
     */
    @Test
    public void testGetFinalFailAnimation() {
        System.out.println("getFinalFailAnimation");
        SplatterCenterController instance = new SplatterCenterController();
        Animation expResult = null;
        Animation result = instance.getFinalFailAnimation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalSuccessAnimation method, of class SplatterCenterController.
     */
    @Test
    public void testGetFinalSuccessAnimation() {
        System.out.println("getFinalSuccessAnimation");
        SplatterCenterController instance = new SplatterCenterController();
        Animation expResult = null;
        Animation result = instance.getFinalSuccessAnimation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
