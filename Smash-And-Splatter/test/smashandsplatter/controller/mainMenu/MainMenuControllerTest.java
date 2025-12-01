/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.mainMenu;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eliob
 */
public class MainMenuControllerTest {
    
    public MainMenuControllerTest() {
    }

    /**
     * Test of initialize method, of class MainMenuController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        MainMenuController instance = new MainMenuController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
