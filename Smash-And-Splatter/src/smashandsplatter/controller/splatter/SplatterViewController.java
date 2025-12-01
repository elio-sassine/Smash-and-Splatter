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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import smashandsplatter.Main;
import smashandsplatter.controller.mainMenu.MainMenuController;
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
    
    @FXML
    private MediaPlayer playerSplatter;
    @FXML
    private MediaPlayer playerSucess;
    @FXML
    private MediaPlayer playerLose;
    @FXML
    private Button muteBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trajectory = new Trajectory();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterSidebar.fxml"));
        try {
            VBox sidebar = loader.load();

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
            root.setLeft(sidebar);
            root.setCenter(center);
            
            String path = getClass().getResource("/smashandsplatter/resources/music/SplatterGameMusic.mp3").toString();
            Media musicSplatter = new Media(path);
            playerSplatter = new MediaPlayer(musicSplatter);
            playerSplatter.setCycleCount(MediaPlayer.INDEFINITE);
            playerSplatter.setVolume(0.25);
            playerSplatter.setMute(MainMenuController.isMuted());
            
            if (!MainMenuController.isMuted()) {
                ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
                volumeOn.setPreserveRatio(true);
                volumeOn.setFitHeight(40);
                volumeOn.setFitWidth(40);
                muteBtn.setGraphic(volumeOn);

            } else {
                ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
                volumeOff.setPreserveRatio(true);
                volumeOff.setFitHeight(50);
                volumeOff.setFitWidth(50);
                muteBtn.setGraphic(volumeOff);
            }
            playerSplatter.play();
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
            
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                int triesLeft = cont.getTriesLeft().get();
                goToNextStage(triesLeft);
            });
                    
            Animation anim = centerCont.getFinalSuccessAnimation();
            anim.setOnFinished(e -> {
                levelsPassed++;
                anchorPane.getChildren().add(imgView);
                root.setEffect(new GaussianBlur(5));
                Label failLbl = new Label("Levels Passed: " + levelsPassed);
                anchorPane.getChildren().add(failLbl);
                
                failLbl.setLayoutX(450);
                failLbl.setLayoutY(400);
                failLbl.setScaleX(2);
                failLbl.setScaleY(2);
                
                DropShadow glow = new DropShadow();
                glow.setColor(Color.WHITE);     // glow color
                glow.setRadius(1);            // size of glow
                glow.setSpread(1);
                failLbl.setEffect(glow);
                
                playerSplatter.stop();
            
                String path = getClass().getResource("/smashandsplatter/resources/music/WinLevelMusic.mp3").toString();
                Media musicSucess = new Media(path);
                playerSucess = new MediaPlayer(musicSucess);
                playerSucess.setMute(MainMenuController.isMuted());
                playerSucess.play();
                
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
            
            PauseTransition p = new PauseTransition(Duration.seconds(trajectory.getTime() / 2 + 2.9));
            
            p.setOnFinished(e -> {
                playerSplatter.stop();
            });
            
            PauseTransition pt = new PauseTransition(Duration.seconds(trajectory.getTime() / 2 + 2.5));
            pt.setOnFinished(e -> {
                anchorPane.getChildren().add(imgView);
                Label failLbl = new Label("Levels Passed: " + levelsPassed);
                anchorPane.getChildren().add(failLbl);
                
                failLbl.setLayoutX(450);
                failLbl.setLayoutY(400);
                failLbl.setScaleX(2);
                failLbl.setScaleY(2);
                
                String path = getClass().getResource("/smashandsplatter/resources/music/LoseLevelMusic.mp3").toString();
                Media musicLose = new Media(path);
                playerLose = new MediaPlayer(musicLose);
                playerLose.setMute(MainMenuController.isMuted());
                playerLose.play();
                
                DropShadow glow = new DropShadow();
                glow.setColor(Color.WHITE);     // glow color
                glow.setRadius(1);            // size of glow
                glow.setSpread(1);
                failLbl.setEffect(glow);
            });
            
            PauseTransition delayBeforeSentBack = new PauseTransition(Duration.seconds(trajectory.getTime() / 2 + 4.5 + 3));
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
            ParallelTransition parallel = new ParallelTransition(failAnim, pt, p, delayBeforeSentBack);
            
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
    
    @FXML
    private void handleMute(ActionEvent event) {
        if (MainMenuController.isMuted()) {
            MainMenuController.setMuted(false);
            
            ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
            volumeOn.setPreserveRatio(true);
            volumeOn.setFitHeight(40);
            volumeOn.setFitWidth(40);
            muteBtn.setGraphic(volumeOn);
            playerSplatter.setMute(false);
            
            return;
        }
        
        MainMenuController.setMuted(true);
        
        playerSplatter.setMute(MainMenuController.isMuted());
        ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
        volumeOff.setPreserveRatio(true);
        volumeOff.setFitHeight(50);
        volumeOff.setFitWidth(50);
        muteBtn.setGraphic(volumeOff);
    }

    /**
     * Gets the trajectory
     * @return the trajectory
     */
    public static Trajectory getTrajectory() {
        return trajectory;
    }
    
    
}
