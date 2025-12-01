/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.splatter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliob
 */
public class SplatterSidebarControllerTest {
    
    public SplatterSidebarControllerTest() {
    }

    /**
     * Test of initialize method, of class SplatterSidebarController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SplatterSidebarController instance = new SplatterSidebarController();
        instance.initialize();
        assertNotNull(instance);
    }

    /**
     * Test of handleSubmit method, of class SplatterSidebarController.
     */
    @Test
    public void testHandleSubmit() {
        System.out.println("handleSubmit");
        ActionEvent event = null;
        SplatterSidebarController instance = new SplatterSidebarController();
        instance.handleSubmit(event);
    }

    /**
     * Test of getSuccess method, of class SplatterSidebarController.
     */
    @Test
    public void testGetSuccess() {
        System.out.println("getSuccess");
        SplatterSidebarController instance = new SplatterSidebarController();
        BooleanProperty expResult = null;
        BooleanProperty result = instance.getSuccess();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTriesLeft method, of class SplatterSidebarController.
     */
    @Test
    public void testGetTriesLeft() {
        System.out.println("getTriesLeft");
        SplatterSidebarController instance = new SplatterSidebarController();
        IntegerProperty expResult = null;
        IntegerProperty result = instance.getTriesLeft();
        assertEquals(expResult, result);
    }
    
}
