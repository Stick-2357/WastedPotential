package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chicks967
 */
//import static gui.PainterController.root;
import java.awt.event.KeyEvent;
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
import javafx.scene.control.Button;

public class CapController 
{
    static Parent root;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;
    
    FXMLLoader tripLoader;
    FXMLLoader pastLoader;
    FXMLLoader addLoader;
    FXMLLoader updateLoader;
    
    
     public void initialize()
    {

            tripLoader = new FXMLLoader(getClass().getResource("NewTrip.fxml"));
            pastLoader = new FXMLLoader(getClass().getResource("PastTrips.fxml"));
            addLoader = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
            updateLoader = new FXMLLoader(getClass().getResource("Update Student.fxml"));
    }

    @FXML
    void AccPastTrip(ActionEvent event) 
    {
        try
        {
            root = pastLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
        
        catch (IOException e)
        {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    void AddStudent(ActionEvent event) 
    {
        try
        {
            root = addLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
        
        catch (IOException e)
        {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    void NewTrip(ActionEvent event) 
    {
        try
        {
            root = tripLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
        
        catch (IOException e)
        {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    void UpdateStudent(ActionEvent event) 
    {
        try
        {
            root = updateLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        }
        
        catch (IOException e)
        {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
