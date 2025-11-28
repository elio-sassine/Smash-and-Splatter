/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package smashandsplatter.controller.smash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
            
            System.out.println(sidebar);
            System.out.println(root);
            root.setLeft(sidebar);
            
            cont = loader.getController();
            initializeHandlers();
        } catch(IOException e) {
            System.err.println("Could not read file!");
        } catch (Exception e) {
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
            int triesLeft = cont.getTriesLeft().get();
            
            goToNextStage(triesLeft);
        });
        
        cont.getTriesLeft().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() > 0) {
                // remove a heart
                return;
            }
            
            // do fail code
            Image img = new Image("file:src/smashandsplatter/resources/images/LevelFailed.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(500);
            imgView.setFitWidth(500);
            
            System.out.println(img.exceptionProperty().get());
            imgView.setX(250);
            imgView.setY(50);
            
            System.out.println(imgView);    
            System.out.println(img.getUrl());
            anchorPane.getChildren().add(imgView);
            System.out.println(anchorPane.getChildren());
            root.setEffect(new GaussianBlur(5));
        });
    }
    
    private void goToNextStage(int triesLeft) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/smashandsplatter/views/smash/SmashView.fxml"));
            Parent root = loader.load();
            SmashViewController viewCont = loader.getController();
            SmashSidebarController sidebarCont = viewCont.cont;
            
            sidebarCont.getTriesLeft().set(triesLeft);
            
            Scene currScene = new Scene(root);
            
            Main.setCurrScene(currScene);
            Stage curr = Main.getCurrStage();
            
            curr.setScene(currScene);
        } catch (Exception e) {
            e.printStackTrace();
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
