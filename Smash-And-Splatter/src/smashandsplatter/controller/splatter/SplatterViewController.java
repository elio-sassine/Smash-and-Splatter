/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.splatter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import smashandsplatter.Main;
import smashandsplatter.models.Trajectory;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SplatterViewController implements Initializable {
    private static Trajectory trajectory;
    private static int levelsPassed;
    
    @FXML
    private BorderPane root;
    
    @FXML
    private AnchorPane anchorPane;
    
    private SplatterSidebarController cont;
    private SplatterCenterController centerCont;
    private VBox heartBox;
    private boolean mainMenuLoaded = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trajectory = new Trajectory();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterSidebar.fxml"));
        try {
            VBox sidebar = loader.load();
            root.setLeft(sidebar);

            cont = loader.getController();
            initializeHandlers();
            System.out.println(getClass().getResource("/smashandsplatter/views/splatter/SplatterCenter.fxml"));
            FXMLLoader centerLoader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterCenter.fxml"));
            Pane center = centerLoader.load();
            
            centerCont = centerLoader.getController();
            
            heartBox = new VBox();
            heartBox.setSpacing(12);
            heartBox.setPadding(new Insets(60, 30, 30, 30));
            heartBox.setPrefHeight(heartBox.getHeight());
            heartBox.setPrefWidth(heartBox.getWidth());
            for (int i = 0; i < 5; i++) {
                ImageView heart = new ImageView(new Image("file:src/smashandsplatter/resources/images/Heart.png"));
                heart.setFitHeight(30);
                heart.setFitWidth(30);
                
                heartBox.getChildren().add(heart);
            }
            
            root.setRight(heartBox);
            
            root.setCenter(center);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    private void initializeHandlers() {
        cont.getSuccess().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                // add fail code, usually unreachable
                return;
            }
            
            // add animation code on success
            Image img = new Image("file:src/smashandsplatter/resources/images/LevelPassed.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(500);
            imgView.setFitWidth(500);
            
            imgView.setX(250);
            imgView.setY(50);
            
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                int triesLeft = cont.getTriesLeft().get();
                levelsPassed++;
                goToNextStage(triesLeft);
            });
                    
            Animation anim = centerCont.getFinalSuccessAnimation();
            anim.setOnFinished(e -> {
                anchorPane.getChildren().add(imgView);
                root.setEffect(new GaussianBlur(5));
                pause.play();
            });
            
            anim.play();
        });
        
        cont.getTriesLeft().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() > 0) {
                // remove a heart
                updateHearts(newVal.intValue());
                return;
            }
            updateHearts(newVal.intValue());
            
            // do fail code
            Image img = new Image("file:src/smashandsplatter/resources/images/LevelFailed.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(500);
            imgView.setFitWidth(500);
            
            imgView.setX(250);
            imgView.setY(50);
            
            System.out.println(levelsPassed);
            
            Animation failAnim = centerCont.getFinalFailAnimation();
            PauseTransition pt = new PauseTransition(Duration.seconds(trajectory.getTime() / 2 + 2.5));
            pt.setOnFinished(e -> {
                anchorPane.getChildren().add(imgView);
                Label failLbl = new Label("Levels Passed: " + levelsPassed);
                anchorPane.getChildren().add(failLbl);
                
                failLbl.setLayoutX(450);
                failLbl.setLayoutY(400);
                
            });
            
            PauseTransition delayBeforeSentBack = new PauseTransition(Duration.seconds(trajectory.getTime() / 2 + 4.5));
            System.out.println("Here...");
            delayBeforeSentBack.setOnFinished(event -> {
                try {
                    if (mainMenuLoaded) return;
                    
                    System.out.println("LOADING MAIN MENU...");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/mainMenu/MainMenu.fxml"));
                    Parent root = loader.load();
                    System.out.println("MAIN MENU LOADED");

                    Scene sc = new Scene(root);
                    
                    mainMenuLoaded = true;
                    Main.getCurrStage().setScene(sc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ParallelTransition parallel = new ParallelTransition(failAnim, pt, delayBeforeSentBack);
            
            parallel.play();
        });
    }
    
    private void goToNextStage(int triesLeft) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterView.fxml"));
            Parent root = loader.load();
            SplatterViewController viewCont = loader.getController();
            SplatterSidebarController sidebarCont = viewCont.cont;
            
            sidebarCont.getTriesLeft().set(triesLeft);
            viewCont.updateHearts(triesLeft);
            
            Scene currScene = new Scene(root);
            
            Main.setCurrScene(currScene);
            Stage curr = Main.getCurrStage();
            
            curr.setScene(currScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the hearts when the user fails a task
     * @param triesLeft the tries the user has left (aka the amount of hearts left)
     */
    private void updateHearts(int triesLeft) {
        for (int i = 0; i < 5 - triesLeft; i++) {
            List hearts = heartBox.getChildren();
            Object obj = hearts.get(i);
            if (obj instanceof ImageView heart) {
                heart.setImage(new Image("file:src/smashandsplatter/resources/images/DeadHeart.png"));
            }
        }
    }

    /**
     * Gets the trajectory
     * @return the trajectory
     */
    public static Trajectory getTrajectory() {
        return trajectory;
    }
    
    
}
