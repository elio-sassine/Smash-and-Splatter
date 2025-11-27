/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import smashandsplatter.models.Torque;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SmashViewController implements Initializable {
    private static Torque torque1;
    private static Torque torque2;
    
    @FXML
    private BorderPane root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        torque1 = new Torque();
        torque2 = new Torque();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashSidebar.fxml"));
        
        try {
            VBox sidebar = loader.load();
            root.setLeft(sidebar);
            
            SmashSidebarController cont = loader.getController();
            initializeHandlers(cont);
        } catch(IOException e) {
            System.err.println("Could not read file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    private void initializeHandlers(SmashSidebarController cont) {
        cont.getSuccess().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                // add fail code, usually unreachable
                return;
            }
            // add animation code on success
        });
        
        cont.getTriesLeft().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() != 0) {
                // remove a heart
                return;
            }
            
            // do fail code
        });
    }

    /**
     * gets the first torque involved in the equation
     * @return Torque torque1
     */
    public static Torque getTorque1() {
        return torque1;
    }

    /**
     * gets the second torque involved in the equation
     * @return Torque torque2
     */
    public static Torque getTorque2() {
        return torque2;
    }
}
