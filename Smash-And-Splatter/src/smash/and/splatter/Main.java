/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package smash.and.splatter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author eliob
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch();
    }

    @Override
    public void start(Stage stage) {
        // temp, for testing
        Torque tq = new Torque();
        Label force = new Label("Force: " + tq.getForce());
        Label distance = new Label("Distance: " + tq.getDistance());
        Label angle = new Label("Angle (degrees): " + tq.getAngle());
        Label torque = new Label("Torque: " + tq.getTorque());
        
        VBox root = new VBox(force, distance, angle, torque);
        
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();
    }
    
}
