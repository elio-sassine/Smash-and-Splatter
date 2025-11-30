/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smashandsplatter.controller.splatter;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
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
    private Pane root;

    @FXML
    private ImageView thrower;
    
    private Trajectory trajectory;
    private Animation finalFailAnimation;
    private Animation finalSuccessAnimation;
    
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            trajectory = SplatterViewController.getTrajectory();
            
            pie.setFitHeight(75);
            pie.setFitWidth(75);
            pie.setMouseTransparent(true);
            
            thrower.setImage(new Image("file:src/smashandsplatter/resources/images/AnimationPieHuman/Humanpie1.png"));
            thrower.setX(0);
            thrower.setY(500 - 80 - trajectory.getyPos() * 5);
            thrower.setMouseTransparent(true);
            
            Rectangle floor = new Rectangle(-200, 500 - 80 - trajectory.getyPos() * 5 + 125, 400, 500);
            floor.setFill(new Color(0.1098, 0.5922, 0.0, 1.0));
            floor.setStroke(null);
            floor.setMouseTransparent(true);
            
            root.getChildren().add(0, floor);
            root.setMouseTransparent(true);
            
            receiver.setImage(new Image("file:src/smashandsplatter/resources/images/AnimationPieHuman/Humanpie1.png"));
            receiver.setX(trajectory.getDistance() * 15 - 60);
            receiver.setY(450);
            receiver.setMouseTransparent(true);
            
            Animation pieAnimation = makePieThrownAnimation();

            Animation punchAnimation = personPunchingAnimation();
            Animation pieAllOverScreen = pieAllOverScreenAnimation();
            finalFailAnimation = new SequentialTransition(pieAnimation, punchAnimation, pieAllOverScreen);

            Animation pieSlideAnimation = personFacePie();
            finalSuccessAnimation = new SequentialTransition(pieAnimation, pieSlideAnimation);
        });
    }
    
    private Animation makePieThrownAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, 
                        new KeyValue(pie.imageProperty(), null)
                ),
                new KeyFrame(Duration.millis(2),
                        new KeyValue(pie.imageProperty(), new Image("file:src/smashandsplatter/resources/images/Pie.png"))
                )
        );
        Path path = new Path(
                new MoveTo(75, 500 - trajectory.getyPos() * 5),
                new QuadCurveTo(
                        trajectory.getControlX() * 15 + 20, 
                        500 - trajectory.getControlY() * 5,
                        trajectory.getDistance() * 15 + 20,
                        // placeholder value until we actually do the person
                        500
                )
        );
        
        PathTransition animation = new PathTransition(Duration.seconds(trajectory.getTime() / 2), path, pie);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        animation.setOnFinished(e -> {
            pie.setRotate(90);
        });
        
        return new SequentialTransition(timeline, animation);
    };
    
    private Animation personPunchingAnimation() {
        double timeToThrow = 1.0;
        String stringToFormat = "file:src/smashandsplatter/resources/images/AnimationPieHuman/Humanpie%d.png";
        KeyFrame[] keyFrames = new KeyFrame[11];
        
        for (int i = 0; i < 11; i++) {
            keyFrames[i] = new KeyFrame(
                    Duration.seconds(i * timeToThrow / (10)),
                    new KeyValue(
                            receiver.imageProperty(), 
                            new Image(String.format(stringToFormat, i + 1))
                    ), 
                    new KeyValue(pie.yProperty(), pie.getY() - 100)
            );
        }
        
        Timeline finalAnim = new Timeline(keyFrames);
        
        return finalAnim;
    }
    
    private Animation pieAllOverScreenAnimation() {
        double timeToSlide = 1.5;
        String stringToFormat = "file:src/smashandsplatter/resources/images/Creamanimation/Cream%d.png";
        KeyFrame[] keyFramesBeforeScreen = new KeyFrame[2];
        
        // base pie sprite
        keyFramesBeforeScreen[0] = new KeyFrame(Duration.ZERO,
                new KeyValue(pie.fitHeightProperty(), 75),
                new KeyValue(pie.fitWidthProperty(), 75),
                new KeyValue(
                            pie.imageProperty(), 
                            new Image("file:src/smashandsplatter/resources/images/RestOfDrawings/Pieup.png")
                )
        );
        
        keyFramesBeforeScreen[1] = new KeyFrame(
                    Duration.millis(250),
                    new KeyValue(
                            pie.imageProperty(), 
                            new Image("file:src/smashandsplatter/resources/images/RestOfDrawings/Pieup.png")
                    ),
                    new KeyValue(pie.translateXProperty(), -300),
                    new KeyValue(pie.translateYProperty(), -300),
                    new KeyValue(pie.fitHeightProperty(), 400),
                    new KeyValue(pie.fitWidthProperty(), 400)
        );
        
        
        KeyFrame[] keyFramesScreen = new KeyFrame[5];
        for (int i = 0; i < 5; i++) {
            keyFramesScreen[i] = new KeyFrame(
                    Duration.seconds((i) * timeToSlide / (6)),
                    new KeyValue(
                            pie.imageProperty(), 
                            new Image(String.format(stringToFormat, i + 1))
                    ),
                    new KeyValue(pie.fitHeightProperty(), 1200),
                    new KeyValue(pie.fitWidthProperty(), 1200)
            );
        }
        
        Timeline beforeScreenAnim = new Timeline(keyFramesBeforeScreen);
        Timeline screenAnim = new Timeline(keyFramesScreen);
        screenAnim.setCycleCount(Animation.INDEFINITE);
        return new SequentialTransition(beforeScreenAnim, screenAnim); 
    }
    
    private Animation personFacePie() {
        double timeToSlide = 1.0;
        String stringToFormat = "file:src/smashandsplatter/resources/images/PieThrow%d.png";
        KeyFrame[] keyFrames = new KeyFrame[7];
        
        // base pie sprite
        keyFrames[0] = new KeyFrame(
                    Duration.seconds(0),
                    new KeyValue(
                            pie.imageProperty(), 
                            new Image("file:src/smashandsplatter/resources/images/PieThrow1.png")
                    ),
                    new KeyValue(pie.rotateProperty(), 0)
        );
        
        for (int i = 0; i < 6; i++) {
            keyFrames[i + 1] = new KeyFrame(
                    Duration.seconds((i + 1) * timeToSlide / (5)),
                    new KeyValue(
                            pie.imageProperty(), 
                            new Image(String.format(stringToFormat, i + 1))
                    ),
                    new KeyValue(pie.yProperty(), pie.getY() + (i + 1) * 3)
            );
        }
        
        Timeline finalAnim = new Timeline(keyFrames);
        
        return finalAnim; 
    }

    public Animation getFinalFailAnimation() {
        return finalFailAnimation;
    }

    public Animation getFinalSuccessAnimation() {
        return finalSuccessAnimation;
    }
}
