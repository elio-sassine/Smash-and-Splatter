/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.splatter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import smashandsplatter.models.Trajectory;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SplatterViewController implements Initializable {
    private static Trajectory trajectory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trajectory = new Trajectory();
    }    

    /**
     * Gets the trajectory
     * @return the trajectory
     */
    public static Trajectory getTrajectory() {
        return trajectory;
    }
    
    
}
