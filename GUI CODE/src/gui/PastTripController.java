/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author chicks967
 */
public class PastTripController 
{
    static Parent root;
    
    @FXML
    private Button backButton;

     public void initialize()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            root = loader.load();
        }
        catch (IOException e)
        {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    @FXML
    void backButtonPressed(ActionEvent event) 
    {
        Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
    }
}
