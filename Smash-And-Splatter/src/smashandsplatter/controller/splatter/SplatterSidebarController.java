package smashandsplatter.controller.splatter;

import java.util.Random;
import javafx.application.Platform;
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
}
