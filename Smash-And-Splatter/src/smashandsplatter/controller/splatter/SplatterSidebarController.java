package smashandsplatter.controller.splatter;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
    
    @FXML
    private VBox root;
    
    private Trajectory trajectory;
    private ArrayList<Integer> shownFields;
    private ArrayList<TextField> lockedFields;
    
    private BooleanProperty success = new SimpleBooleanProperty(false);
    private IntegerProperty triesLeft = new SimpleIntegerProperty(5);
    
    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            trajectory = SplatterViewController.getTrajectory();
            shownFields = new ArrayList<>();
            lockedFields = new ArrayList<>();
        
            initializeFields(); 
            
            // for testing
            System.out.println(trajectory);
        });
    }
    
    /**
     * Initializes the fields for sidebar
     */
    private void initializeFields() {
        Random rand = new Random();
        
        for (int i = 0; i < 3; i++) {
            int nextInt = rand.nextInt(6);
            while (shownFields.contains(nextInt)) {
                nextInt = rand.nextInt(6);
            }
            
            shownFields.add(nextInt);
        }
        
        for (int field : shownFields) {
            switch (field) {
                case 0 -> {
                    double value = (trajectory.getInitialVelocity())[0];
                    lockedFields.add(xVelocity);
                    displayValue(xVelocity, value, "m/s");
                }
                case 1 -> {
                    double value = (trajectory.getInitialVelocity())[1];
                    lockedFields.add(initialYVelocity);
                    displayValue(initialYVelocity, value, "m/s");
                }
                case 2 -> {
                    double value = (trajectory.getFinalVelocity())[1];
                    lockedFields.add(finalYVelocity);
                    displayValue(finalYVelocity, value, "m/s");
                }
                case 3 -> {
                    double value = trajectory.getDistance();
                    lockedFields.add(xDistance);
                    displayValue(xDistance, value, "m");
                }
                case 4 -> {
                    double value = trajectory.getyPos();
                    lockedFields.add(yPos);
                    displayValue(yPos, value, "m");
                }
                case 5 -> {
                    double value = trajectory.getTime();
                    lockedFields.add(time);
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
        
        // reset the reds
        for (Node child : root.getChildren()) {
            child.getStyleClass().removeAll("wrongButton");
        }
        
        // need this to go through all the if statements and still know if we got the right answer or not
        boolean isRight = true;
        
        // divergent spaghetti
        double xDistanceAnswer = lockedFields.contains(xDistance) ? trajectory.getDistance() : Double.parseDouble(xDistance.getText());
        double xVelocityAnswer = lockedFields.contains(xVelocity) ? trajectory.getInitialVelocity()[0] : Double.parseDouble(xVelocity.getText());
        double yPosAnswer = lockedFields.contains(yPos) ? trajectory.getyPos() : Double.parseDouble(yPos.getText());
        double initialYVelocityAnswer = lockedFields.contains(initialYVelocity) ? trajectory.getInitialVelocity()[1] : Double.parseDouble(initialYVelocity.getText());
        double finalYVelocityAnswer = lockedFields.contains(finalYVelocity) ? trajectory.getFinalVelocity()[1] : Double.parseDouble(finalYVelocity.getText());
        double timeAnswer = lockedFields.contains(time) ? trajectory.getTime() : Double.parseDouble(time.getText());
        
        // convergent spaghetti
        if (!(trajectory.getDistance() + 0.1 >= xDistanceAnswer && trajectory.getDistance() - 0.1 <= xDistanceAnswer)) {
            isRight = false;
            xDistance.getStyleClass().add("wrongButton");
        }
        if (!(trajectory.getInitialVelocity()[0] + 0.1 > xVelocityAnswer && trajectory.getInitialVelocity()[0] - 0.1 <= xVelocityAnswer)) {
            isRight = false;
            xVelocity.getStyleClass().add("wrongButton");
        }
        if (!(trajectory.getTime()+ 0.1 >= timeAnswer && trajectory.getTime()- 0.1 <= timeAnswer)) {
            isRight = false;
            time.getStyleClass().add("wrongButton");
        }
        if (!(trajectory.getyPos()+ 0.1 >= yPosAnswer && trajectory.getyPos()- 0.1 <= yPosAnswer)) {
            isRight = false;
            yPos.getStyleClass().add("wrongButton");
        }
        if (!(trajectory.getInitialVelocity()[1] + 0.1 >= initialYVelocityAnswer && trajectory.getInitialVelocity()[1] - 0.1 <= initialYVelocityAnswer)) {
            isRight = false;
            initialYVelocity.getStyleClass().add("wrongButton");
        }
        if (!(trajectory.getFinalVelocity()[1] + 0.1 >= finalYVelocityAnswer && trajectory.getFinalVelocity()[1] - 0.1 <= finalYVelocityAnswer)) {
            isRight = false;
            finalYVelocity.getStyleClass().add("wrongButton");
        }

        // undefined spaghetti
        if (!isRight) {
            int tries = triesLeft.get();
            triesLeft.set(tries - 1);
            return;
        }
        
        success.set(true);
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
