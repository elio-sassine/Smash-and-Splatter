/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smashandsplatter.controller.splatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;
import smashandsplatter.models.Trajectory;

/**
 *
 * @author eliob
 */
public class SplatterCenterController {
    
    @FXML
    private ImageView pie;

    @FXML
    private ImageView receiver;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView thrower;
    
    private Trajectory trajectory;
    
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            trajectory = SplatterViewController.getTrajectory();
            
            pie.setImage(new Image("file:src/smashandsplatter/resources/images/Pie.png"));
            pie.setFitHeight(100);
            pie.setFitWidth(100);
            
            Animation pieAnimation = makePieThrownAnimation();
            pieAnimation.play();
        });
    }
    
    private Animation makePieThrownAnimation() {
        Path path = new Path(
                new MoveTo(20, 500 - trajectory.getyPos() * 5),
                new QuadCurveTo(
                        trajectory.getControlX() * 15 + 20, 
                        500 - trajectory.getControlY() * 5,
                        trajectory.getDistance() * 15 + 20,
                        // placeholder value until we actually do the person
                        500
                )
        );
        
        PathTransition animation = new PathTransition(Duration.seconds(trajectory.getTime()), path, pie);
        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        return animation;
    };
    
}
