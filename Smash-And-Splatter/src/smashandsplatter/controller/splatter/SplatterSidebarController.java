package smashandsplatter.controller.splatter;

import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import smashandsplatter.models.Trajectory;

/**
 * Controller for the SplatterSidebar View
 * @author eliob
 */
public class SplatterSidebarController {
    @FXML
    private Button submit;

    @FXML
    private TextField finalYVelocity;

    @FXML
    private TextField initialYVelocity;

    @FXML
    private TextField time;

    @FXML
    private TextField xDistance;

    @FXML
    private TextField xVelocity;

    @FXML
    private TextField yPos;
    
    private Trajectory trajectory;
    private int[] shownFields;
    
    private BooleanProperty success;
    private IntegerProperty triesLeft;
    
    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            trajectory = SplatterViewController.getTrajectory();
            shownFields = new int[3];
        
            initializeFields(); 
        });
    }
    
    /**
     * Initializes the fields for sidebar
     */
    private void initializeFields() {
        Random rand = new Random();

        
        for (int i = 0; i < 3; i++) {
            shownFields[i] = rand.nextInt(6);
        }
        
        for (int i = 0; i < 3; i++) {
            switch (shownFields[i]) {
                case 0 -> {
                    double value = (trajectory.getInitialVelocity())[0];
                    displayValue(xVelocity, value, "m/s");
                }
                case 1 -> {
                    double value = (trajectory.getInitialVelocity())[1];
                    displayValue(initialYVelocity, value, "m/s");
                }
                case 2 -> {
                    double value = (trajectory.getFinalVelocity())[1];
                    displayValue(finalYVelocity, value, "m/s");
                }
                case 3 -> {
                    double value = trajectory.getDistance();
                    displayValue(xDistance, value, "m");
                }
                case 4 -> {
                    double value = trajectory.getyPos();
                    displayValue(yPos, value, "m");
                }
                case 5 -> {
                    double value = trajectory.getTime();
                    displayValue(time, value, "s");
                }
            };
        }
    }
    
    /**
     * Displays the value needed
     * @param input The text field where the value is being displayed
     * @param value The value to display (as a double)
     * @param unit The unit of the value displayed
     */
    private void displayValue(TextField input, double value, String unit) {
        input.setDisable(true);
        String display = String.format("%s: %.02f %s", input.getPromptText(), value, unit);
        input.setText(display);
    }
    
    /**
     * Handles the submit
     * Has a range of answer +/- 0.1 as we approximate and so does the user
     * @param event The event that caused the submit
     */
    @FXML
    void handleSubmit(ActionEvent event) {
        // We basically have no choice but to do a bunch of if statements if we want to highlight bad answers
        // Unless JavaFx has validators (I don't think it does but I don't care enough to check, this is easier)
        // I will for sure make a great software engineer trust me fo sho
        
        // need this to go through all the if statements and still know if we got the right answer or not
        boolean isRight = true;
        
        double xDistanceAnswer = Double.parseDouble(xDistance.getText());
        double xVelocityAnswer = Double.parseDouble(xVelocity.getText());
        double yPosAnswer = Double.parseDouble(yPos.getText());
        double initialYVelocityAnswer = Double.parseDouble(initialYVelocity.getText());
        double finalYVelocityAnswer = Double.parseDouble(finalYVelocity.getText());
        double timeAnswer = Double.parseDouble(time.getText());
        
        if (!(trajectory.getDistance() + 0.1 >= xDistanceAnswer && trajectory.getDistance() - 0.1 <= xDistanceAnswer)) {
            isRight = false;
            
        }
        // basically just copy paste this 5 times lol
        
        if (!isRight) {
            int tries = triesLeft.get();
            triesLeft.set(tries - 1);
        }
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
