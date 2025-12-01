/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliob
 */
public class SmashSidebarControllerTest {
    
    public SmashSidebarControllerTest() {
    }

    /**
     * Test of initialize method, of class SmashSidebarController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SmashSidebarController instance = new SmashSidebarController();
        instance.initialize(url, rb);
        
        assertNotNull(instance);
    }

    /**
     * Test of handleSubmit method, of class SmashSidebarController.
     */
    @Test
    public void testHandleSubmit() {
        System.out.println("handleSubmit");
        ActionEvent event = null;
        SmashSidebarController instance = new SmashSidebarController();
        instance.handleSubmit(event);
    }

    /**
     * Test of getSuccess method, of class SmashSidebarController.
     */
    @Test
    public void testGetSuccess() {
        System.out.println("getSuccess");
        SmashSidebarController instance = new SmashSidebarController();
        BooleanProperty expResult = null;
        BooleanProperty result = instance.getSuccess();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTriesLeft method, of class SmashSidebarController.
     */
    @Test
    public void testGetTriesLeft() {
        System.out.println("getTriesLeft");
        SmashSidebarController instance = new SmashSidebarController();
        IntegerProperty expResult = null;
        IntegerProperty result = instance.getTriesLeft();
        assertEquals(expResult, result);
    }
    
}
