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

//import java.awt.event.KeyEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
//import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException; 
import javafx.scene.Node;
import javafx.scene.Scene;
//import javafx.stage.Stage; 
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Circle;


public class PainterController 
{
    static Parent root;
     @FXML
    private TextField box1;

    @FXML
    private TextField box2;
    
   
    
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
    
     
    @FXML void UsernameEntered(ActionEvent event) 
    {   
        String userFname = "Caden";
        String userLname = "Hicks";
        
        if(userFname.equals(box1.getText()) && userLname.equals(box2.getText()))
        {
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
        else
        {
            System.out.println("Hi");
        }
    }
    
    

}
