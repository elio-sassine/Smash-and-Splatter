/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package smashandsplatter.controller.mainMenu;

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
    public void testInitialize() throws InterruptedException {
        System.out.println("initialize");
        MainMenuController instance = new MainMenuController();
        assertNotNull(instance);
    }
}