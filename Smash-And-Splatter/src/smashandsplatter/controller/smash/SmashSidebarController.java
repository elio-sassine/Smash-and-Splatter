/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label torque1Text;

    @FXML
    private Label torque2Text;

    private Torque torque1;
    private Torque torque2;
    private double answerTorque;
    
    private BooleanProperty success = new SimpleBooleanProperty(false);
    private IntegerProperty triesLeft = new SimpleIntegerProperty(5);
    
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
            
            answerTorque = Math.round(answerTorque * 100) / 100.0;
            
            // for testing
            System.out.println(answerTorque);
        });
    }    
    
    /**
     * Handles the submit
     * Has a range of answer +/- 0.1 as we approximate and so does the user
     * @param event 
     */
    @FXML
    void handleSubmit(ActionEvent event) {
        try {
            double submittedTorque = Double.parseDouble(answerTorqueText.getText());
            
            // It is better to check a range instead of an exact answer as we round 
            // and we should assume they round too
            if (
                    submittedTorque >= (answerTorque - 0.1) && 
                    submittedTorque <= (answerTorque + 0.1)
                )
            {
                success.set(true);
                return;
            } 
            
            int tries = triesLeft.get();
            triesLeft.set(tries - 1);
        } catch (NumberFormatException e) {
            submit.setText("Has to be a number without units!");
        }
    }
    
    /**
     * Initializes the text fields for the input torques
     * @param text the text field to modify
     * @param torque the torque object used for the text field
     * @param number which torque it is
     */
    private void initializeTextField(Label text, Torque torque, int number) {
        text.setText(String.format(
                    "Torque %d: \nF = %.2f N \nr = %.2f m \nAngle = %.2f deg",
                    number,
                    torque.getForce(),
                    torque.getDistance(),
                    torque.getAngle()
        ));
    }

    /**
     * Gets the success property to be able to add listeners
     * @return BooleanProperty success
     */
    public BooleanProperty getSuccess() {
        return success;
    }

    /**
     * Gets the amount of tries left as property to be able to add listeners
     * @return IntegerProperty triesLeft
     */
    public IntegerProperty getTriesLeft() {
        return triesLeft;
    }
}
