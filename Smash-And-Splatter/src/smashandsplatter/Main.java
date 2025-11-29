/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package smashandsplatter;

import java.io.IOException;
import smashandsplatter.models.Torque;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author eliob
 */
public class Main extends Application {
    
    private static Stage currStage;
    private static Scene currScene;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch();
    }

    @Override
    public void start(Stage stage) {
        currStage = stage;
        // temp, for testing
        try {
            Parent root = new Pane();

            Scene sc = new Scene(root);
            currScene = sc;

            stage.setScene(sc);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getCurrStage() {
        return currStage;
    }

    public static Scene getCurrScene() {
        return currScene;
    }

    public static void setCurrScene(Scene currScene) {
        Main.currScene = currScene;
    }
    
    
}
