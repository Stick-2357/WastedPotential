/*     Team: Wasted Potential
    Project: ProDeo Router
       File: Main.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage stage;
    static Scene scene;
    
    @Override public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
       launch(args);
    }
}
