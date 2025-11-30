/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import smashandsplatter.models.Torque;

/**
 * FXML Controller class
 *
 * @author eliob
 */
public class SmashViewController implements Initializable {
    private static Torque torque1;
    private static Torque torque2;
    
    private SmashSidebarController cont;
    private VBox heartBox;
    private SmashCenterController centerCont;
    
    @FXML
    private BorderPane root;
    
    @FXML
    private AnchorPane anchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        torque1 = new Torque();
        torque2 = new Torque();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashSidebar.fxml"));
        try {
            VBox sidebar = loader.load();

            root.setLeft(sidebar);
            
            cont = loader.getController();
            initializeHandlers();
            
            FXMLLoader centerLoader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashCenter.fxml"));
            Pane centerPane = centerLoader.load();
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
            
            root.setCenter(centerPane);
        } catch(IOException e) {
            System.err.println("Could not read file!");
        } catch (Exception e) {
            System.err.println("Yeah idk bro look at the stack trace");
            e.printStackTrace();
        }
    }    
    
    /**
     * initializes the handlers for the success and tries left properties
     * @param cont 
     */
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
            
            PauseTransition delayBeforeLvlPassed = new PauseTransition(Duration.seconds(1));
            PauseTransition pt = new PauseTransition(Duration.seconds(3));
            pt.setOnFinished(e -> {
                int triesLeft = cont.getTriesLeft().get();
            
                goToNextStage(triesLeft);
            });
                  
            
            Animation successAnimation = centerCont.getSuccessAnimation();
            delayBeforeLvlPassed.setOnFinished(e -> {
                anchorPane.getChildren().add(imgView);
                root.setEffect(new GaussianBlur(5));
                pt.play();
            });
            Animation animToPlay = new SequentialTransition(successAnimation, delayBeforeLvlPassed);
            animToPlay.play();
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
            
            PauseTransition delayBeforeLevelFail = new PauseTransition(Duration.seconds(1));
            delayBeforeLevelFail.setOnFinished(e -> {
                anchorPane.getChildren().add(imgView);
                root.setEffect(new GaussianBlur(5));
            });
            
            Animation failAnim = centerCont.getFailAnimation();
            new SequentialTransition(failAnim, delayBeforeLevelFail).play();
        });
    }
    
    private void goToNextStage(int triesLeft) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashView.fxml"));
            Parent root = loader.load();
            SmashViewController viewCont = loader.getController();
            SmashSidebarController sidebarCont = viewCont.cont;
            
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
     * gets the first torque involved in the equation
     * @return Torque torque1
     */
    public static Torque getTorque1() {
        return torque1;
    }

    /**
     * gets the second torque involved in the equation
     * @return Torque torque2
     */
    public static Torque getTorque2() {
        return torque2;
    }   
}
