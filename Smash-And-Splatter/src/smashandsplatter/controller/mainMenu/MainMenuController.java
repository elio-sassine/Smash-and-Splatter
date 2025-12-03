/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.mainMenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import smashandsplatter.Main;
import smashandsplatter.controller.smash.SmashViewController;
import smashandsplatter.controller.splatter.SplatterViewController;

/**
 * FXML Controller class
 *
 * @author Antonia
 */
public class MainMenuController implements Initializable {

    @FXML
    private AnchorPane mainMenuPane;
    @FXML
    private Button smashBtn;
    @FXML
    private Button splatterBtn;
    @FXML
    private ImageView titleImgView;
    @FXML
    private MediaPlayer playerStart;
    @FXML
    private ChoiceBox<Player> choiceBox;
    @FXML
    private Button muteBtn;

    /**
     * Enum representing player types
     */
    public enum Player {
        ALIEN("Alien"), HUMAN("Human"), ZOMBIE("Zombie");

        private final String displayName;

        private Player(String displayName) {
            this.displayName = displayName;
        }
        
        /**
         * gets the display name of the player
         * @return string representation of player
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * gets the display name of the player
         * @return string representation of player
         */
        @Override 
        public String toString() {
            return displayName;
        }
    }
    
    private static Player currPlayer = Player.HUMAN;
    private static boolean muted = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        choiceBox.getItems().add(Player.HUMAN);
        choiceBox.getItems().add(Player.ALIEN);
        choiceBox.getItems().add(Player.ZOMBIE);
        
        choiceBox.setValue(currPlayer);
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println(newVal);
            currPlayer = newVal;
        });
        
        Image smashImg = new Image(getClass().getResource("/smashandsplatter/resources/images/SmashButton.png")
                        .toExternalForm());
        ImageView smashImgView = new ImageView(smashImg);
        smashImgView.setFitHeight(70.4);
        smashImgView.setFitWidth(230.4);
        
        smashBtn.setGraphic(smashImgView);
        smashBtn.setBackground(Background.EMPTY);
        
        Image splatterImg = new Image(getClass().getResource("/smashandsplatter/resources/images/SplatterButton.png")
                            .toExternalForm());
        ImageView splatterImgView = new ImageView(splatterImg);
        splatterImgView.setFitHeight(70.4);
        splatterImgView.setFitWidth(230.4);
        
        splatterBtn.setGraphic(splatterImgView);
        splatterBtn.setBackground(Background.EMPTY);
        
        String path = getClass().getResource("/smashandsplatter/resources/music/StartGameMusic.mp3").toString();
        Media musicStart = new Media(path);
        playerStart = new MediaPlayer(musicStart);
        playerStart.setCycleCount(MediaPlayer.INDEFINITE);
        
        initializeMuteButton(playerStart, muteBtn);
        
        playerStart.play();
    }    

    //Handles actions when smash button is pressed
    @FXML
    private void handleSmash(ActionEvent event) {
        try {
            playerStart.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashView.fxml"));
            Parent root = loader.load();
            SmashViewController cont = loader.getController();
            cont.setLevelsPassed(0);
            
            Scene sc = new Scene(root);
            
            Main.getCurrStage().setScene(sc);           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //Handles actions when splatter button is pressed
    @FXML
    private void handleSplatter(ActionEvent event) {
        try {
            playerStart.stop();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterView.fxml"));
            Parent root = loader.load();
            SplatterViewController cont = loader.getController();
            cont.setLevelsPassed(0);

            Scene sc = new Scene(root);
            
            Main.getCurrStage().setScene(sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleMute(ActionEvent event) {
        if (muted) {
            muted = false;
            
            ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
            volumeOn.setPreserveRatio(true);
            volumeOn.setFitHeight(40);
            volumeOn.setFitWidth(40);
            muteBtn.setGraphic(volumeOn);
            playerStart.setMute(muted);
            
            return;
        }
        
        muted = true;
        
        playerStart.setMute(muted);
        ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
        volumeOff.setPreserveRatio(true);
        volumeOff.setFitHeight(50);
        volumeOff.setFitWidth(50);
        muteBtn.setGraphic(volumeOff);
    }
    
    /**
     * current player
     * @return Player
     */
    public static Player getCurrPlayer() {
        return currPlayer;
    }

    /**
     * is muted or not
     * @return boolean muted
     */
    public static boolean isMuted() {
        return muted;
    }

    /**
     * sets muted state
     * @param muted boolean defining mute state
     */
    public static void setMuted(boolean muted) {
        MainMenuController.muted = muted;
    }
    
    /**
     * initializes the mute button for all the controllers
     * @param player 
     */
    public static void initializeMuteButton(MediaPlayer player, Button muteBtn) {
        if (!muted) {
            ImageView volumeOn = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOn.png"));
            volumeOn.setPreserveRatio(true);
            volumeOn.setFitHeight(40);
            volumeOn.setFitWidth(40);
            muteBtn.setGraphic(volumeOn);
            player.setMute(muted);
            return;
        } 
        
        player.setMute(muted);
        ImageView volumeOff = new ImageView(new Image("file:src/smashandsplatter/resources/images/VolumeOff.png"));
        volumeOff.setPreserveRatio(true);
        volumeOff.setFitHeight(50);
        volumeOff.setFitWidth(50);
        muteBtn.setGraphic(volumeOff);
    }
}
