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
import javafx.animation.RotateTransition;
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
    private ImageView person;
    @FXML
    private ImageView boulder;
    @FXML
    private ImageView logMoving;
    
    private Animation successAnimation;
    private Animation failAnimation;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        successAnimation = new ParallelTransition(personBuffAnimation(), rockFallingSuccess());
        failAnimation = new ParallelTransition(rockFallingFail(), manGettingCrushed());
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
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), boulder);
        
        // goofy ahh
        rotate.setFromAngle(0);
        rotate.setToAngle(-720);
       
        PathTransition pathTransition = new PathTransition(Duration.seconds(timeToFall), path, boulder);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH);
        
        ParallelTransition pt = new ParallelTransition(pathTransition, rotate);
        
        return new SequentialTransition(timeline, pt);
    }
    
    /**
     * Makes the animation for the person getting crushed
     * @return an animation of the person getting crushed
     */
    private Animation manGettingCrushed() {
        String pathToFormat = "file:src/smashandsplatter/resources/images/BoulderCrushDrawings/Humandown.png";
        KeyFrame[] keyFrames = new KeyFrame[2];
        
        keyFrames[0] = new KeyFrame(Duration.ZERO, 
                new KeyValue(person.imageProperty(), new Image("file:src/smashandsplatter/resources/images/AnimationMuscleHuman/HumanBuff1.png")),
                new KeyValue(person.viewOrderProperty(), 100),
                new KeyValue(boulder.viewOrderProperty(), 0)
        );
        
        keyFrames[1] = new KeyFrame(Duration.seconds(1.85), 
                new KeyValue(person.imageProperty(), new Image(pathToFormat)),
                new KeyValue(person.viewOrderProperty(), 100),
                new KeyValue(boulder.viewOrderProperty(), 0)
        );
        
        return new Timeline(keyFrames);
    }

    /**
     * Makes the animation for the rock falling on fail
     * @return an animation of the rock falling
     */
    public Animation rockFallingFail() {
        double timeToFall = 1.0;
        
        Rotate rot = new Rotate(0, 20, 50);
        logMoving.getTransforms().add(rot);
        
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, 
                        new KeyValue(rot.angleProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.0), 
                        new KeyValue(rot.angleProperty(), -90)
                )
        );
        
        Path path = new Path(
                new MoveTo(80, 90),
                new LineTo(-20, 120),
                new LineTo(-20, 420)
        );
        
        RotateTransition rotate = new RotateTransition(Duration.seconds(1), boulder);
        
        rotate.setFromAngle(0);
        rotate.setToAngle(-720);
       
        PathTransition pathTransition = new PathTransition(Duration.seconds(timeToFall), path, boulder);
        pathTransition.setInterpolator(Interpolator.EASE_BOTH);
        
        ParallelTransition pt = new ParallelTransition(pathTransition, rotate);
        
        return new SequentialTransition(timeline, pt);
    }
    
    /**
     * Gets the animation when the user succeeds at the task
     * @return the animation
     */
    public Animation getSuccessAnimation() {
        return successAnimation;
    }

    /**
     * Gets the animation that plays when the user fails the task
     * @return fail animation
     */
    public Animation getFailAnimation() {
        return failAnimation;
    }
}
