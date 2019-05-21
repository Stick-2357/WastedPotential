/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author chicks967
 */
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application
{
    static Stage stage;
    static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        this.stage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        
        //.setRoot());
    }
    public static void main(String[] args) 
    {
       launch(args);
       
    }
}
