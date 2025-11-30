/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SmashCenterController {

    @FXML
    private Pane root;
    @FXML
    private ImageView person;
    @FXML
    private ImageView boulder;
    @FXML
    private ImageView logBase;
    @FXML
    private ImageView logMoving;
    
    private Animation successAnimation;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        successAnimation = new ParallelTransition(personBuffAnimation(), rockFallingSuccess());
    }    
    
    /**
     * Makes the animation for the person getting buff
     * @return an animation of the person getting buff
     */
    private Animation personBuffAnimation() {
        double timeToBuff = 1.5;
        String pathToFormat = "file:src/smashandsplatter/resources/images/AnimationMuscleHuman/HumanBuff%d.png";
        KeyFrame[] keyFrames = new KeyFrame[13];
        
        for (int i = 0; i < 13; i++) {
            keyFrames[i] = new KeyFrame(Duration.seconds(i * (timeToBuff / 12)), 
                    new KeyValue(person.imageProperty(), new Image(String.format(pathToFormat, i + 1)))
            );
        }
        
        PauseTransition pt = new PauseTransition(Duration.seconds(0.75));
        
        return new SequentialTransition(pt, new Timeline(keyFrames));
    }
    
    /**
     * Makes the animation for the rock falling on success
     * @return an animation of the rock falling
     */
    private Animation rockFallingSuccess() {
        double timeToFall = 1.0;
        
        Rotate rot = new Rotate(0, 20, 50);
        logMoving.getTransforms().add(rot);
        
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, 
                        new KeyValue(rot.angleProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.0), new KeyValue(rot.angleProperty(), -90))
        );
        
        Path path = new Path(
                new MoveTo(80, 90),
                new LineTo(-20, 120),
                new LineTo(-20, 410),
                new LineTo(-20, 390)
        );
        
        PathTransition pathTransition = new PathTransition(Duration.seconds(timeToFall), path, boulder);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH);
        
        return new SequentialTransition(timeline, pathTransition);
    }

    /**
     * Gets the animation when the user succeeds at the task
     * @return the animation
     */
    public Animation getSuccessAnimation() {
        return successAnimation;
    }
}
