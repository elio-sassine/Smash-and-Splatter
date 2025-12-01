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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import smashandsplatter.Main;
import smashandsplatter.models.Torque;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import smashandsplatter.controller.mainMenu.MainMenuController;

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
    private boolean mainMenuLoaded = false; // Flag to ensure the scene is set only once
    
    private static int levelsPassed = 0;

    
    @FXML
    private BorderPane root;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Rectangle sky;
    @FXML
    private Rectangle grass;
    @FXML
    private MediaPlayer playerSmash;
    @FXML
    private MediaPlayer playerSuccess;
    @FXML
    private MediaPlayer playerLose;
    @FXML
    private Button muteBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        torque1 = new Torque();
        torque2 = new Torque();
        // makes sure they are behind everything
        sky.setViewOrder(1000);
        grass.setViewOrder(999);
        
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
            
            String path = getClass().getResource("/smashandsplatter/resources/music/SmashGameMusic.mp3").toString();
            Media musicSmash = new Media(path);
            playerSmash = new MediaPlayer(musicSmash);
            playerSmash.setCycleCount(MediaPlayer.INDEFINITE);
            
            if (!MainMenuController.isMuted()) {
                ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
                volumeOn.setPreserveRatio(true);
                volumeOn.setFitHeight(40);
                volumeOn.setFitWidth(40);
                muteBtn.setGraphic(volumeOn);
                playerSmash.setMute(MainMenuController.isMuted());

            } else {
                playerSmash.setMute(MainMenuController.isMuted());
                ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
                volumeOff.setPreserveRatio(true);
                volumeOff.setFitHeight(50);
                volumeOff.setFitWidth(50);
                muteBtn.setGraphic(volumeOff);
            }
            
            playerSmash.play();
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
            PauseTransition pt = new PauseTransition(Duration.seconds(5.5));
            pt.setOnFinished(e -> {
                int triesLeft = cont.getTriesLeft().get();
            
                goToNextStage(triesLeft);
            });
                  
            
            Animation successAnimation = centerCont.getSuccessAnimation();
            delayBeforeLvlPassed.setOnFinished(e -> {
                playerSmash.stop();
            
                String path = getClass().getResource("/smashandsplatter/resources/music/WinLevelMusic.mp3").toString();
                Media musicSucess = new Media(path);
                playerSuccess = new MediaPlayer(musicSucess);                
                playerSuccess.setMute(MainMenuController.isMuted());
                playerSuccess.play();
                levelsPassed++;
                
                anchorPane.getChildren().add(imgView);
                root.setEffect(new GaussianBlur(5));
                Label failLbl = new Label("Levels Passed: " + levelsPassed);
                anchorPane.getChildren().add(failLbl);
                
                failLbl.setLayoutX(450);
                failLbl.setLayoutY(400);
                failLbl.setScaleX(2);
                failLbl.setScaleY(2);
                
                failLbl.setFont(Font.font("Awasete Powder"));

                
                DropShadow glow = new DropShadow();
                glow.setColor(Color.WHITE);     // glow color
                glow.setRadius(1);            // size of glow
                glow.setSpread(1);
                failLbl.setEffect(glow);
                
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
            
            Label levelsPassedLbl = new Label("Levels Passed: " + levelsPassed);
            levelsPassedLbl.setLayoutX(450);
            levelsPassedLbl.setLayoutY(400);
            levelsPassedLbl.setFont(Font.font("Awasete Powder"));
            levelsPassedLbl.setScaleX(2);
            levelsPassedLbl.setScaleY(2);
            
            DropShadow glow = new DropShadow();
            glow.setColor(Color.WHITE);     // glow color
            glow.setRadius(1);            // size of glow
            glow.setSpread(1);

            levelsPassedLbl.setEffect(glow);
            
            PauseTransition delayBeforeLevelFail = new PauseTransition(Duration.seconds(1));
            delayBeforeLevelFail.setOnFinished(e -> {
                playerSmash.stop();
                
                String path = getClass().getResource("/smashandsplatter/resources/music/LoseLevelMusic.mp3").toString();
                Media musicLose = new Media(path);
                playerLose = new MediaPlayer(musicLose);
                playerLose.setMute(MainMenuController.isMuted());
                playerLose.play();
                
                anchorPane.getChildren().add(imgView);
                anchorPane.getChildren().add(levelsPassedLbl);
                root.setEffect(new GaussianBlur(5));
            });
            
            PauseTransition delayBeforeSentBack = new PauseTransition(Duration.seconds(4));
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
            
            Animation failAnim = centerCont.getFailAnimation();
            new SequentialTransition(failAnim, delayBeforeLevelFail, delayBeforeSentBack).play();
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
    
    @FXML
    private void handleMute(ActionEvent event) {
        if (MainMenuController.isMuted()) {
            MainMenuController.setMuted(false);
            
            ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
            volumeOn.setPreserveRatio(true);
            volumeOn.setFitHeight(40);
            volumeOn.setFitWidth(40);
            muteBtn.setGraphic(volumeOn);
            playerSmash.setMute(false);
            
            return;
        }
        
        MainMenuController.setMuted(true);
        
        playerSmash.setMute(MainMenuController.isMuted());
        ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
        volumeOff.setPreserveRatio(true);
        volumeOff.setFitHeight(50);
        volumeOff.setFitWidth(50);
        muteBtn.setGraphic(volumeOff);
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

    public static void setLevelsPassed(int levelsPassed) {
        SmashViewController.levelsPassed = levelsPassed;
    }
}
