/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import smashandsplatter.models.Torque;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SmashSidebarController implements Initializable {

    @FXML
    private TextField answerTorqueText;

    @FXML
    private Button submit;

    @FXML
    private TextField torque1Text;

    @FXML
    private TextField torque2Text;

    private Torque torque1;
    private Torque torque2;
    private double answerTorque;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            torque1 = SmashViewController.getTorque1();
            torque2 = SmashViewController.getTorque2();
            
            initializeTextField(torque1Text, torque1, 1);
            initializeTextField(torque2Text, torque2, 2);
            
            answerTorque = -(torque1.getTorque() + torque2.getTorque());
        });
    }    
    
    /**
     * Initializes the text fields for the input torques
     * @param text the text field to modify
     * @param torque the torque object used for the text field
     * @param number which torque it is
     */
    private void initializeTextField(TextField text, Torque torque, int number) {
        text.setText(String.format(
                    "Torque %d: F = %.2f N, r = %.2f m, Angle = %.2f deg",
                    number,
                    torque.getForce(),
                    torque.getDistance(),
                    torque.getAngle()
        ));
        text.setDisable(true);
    }
    
}
