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
import smashandsplatter.Main;

/**
 * FXML Controller class
 *
 * @author Raluca
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
    private ChoiceBox<Player> choiceBox;
    
    public enum Player {
        ALIEN("Alien"), HUMAN("Human"), ZOMBIE("Zombie");

        private final String displayName;

        private Player(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override 
        public String toString() {
            return displayName;
        }
    }
    
    private static Player currPlayer = Player.HUMAN;
    
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
    }    

    //Handles actions when smash button is pressed
    @FXML
    private void handleSmash(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashView.fxml"));
            Parent root = loader.load();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/splatter/SplatterView.fxml"));
            Parent root = loader.load();

            Scene sc = new Scene(root);
            
            Main.getCurrStage().setScene(sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
