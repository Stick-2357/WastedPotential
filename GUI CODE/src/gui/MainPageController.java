/*     Team: Wasted Potential
    Project: ProDeo Router
       File: MainPageController.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException; 
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainPageController {
    static Parent root;

    @FXML private Button newTripButton;
    @FXML private Button accPastTripButton;
    @FXML private Button viewLoginAttemptsButton;
    @FXML private Button addStudentButton;
    @FXML private Button updateStudentButton;
    @FXML private Button addChaperoneButton;
    
    FXMLLoader tripLoader;
    FXMLLoader pastLoader;
    FXMLLoader addLoader;
    FXMLLoader updateLoader;
    FXMLLoader viewLoginsLoader;
    FXMLLoader addChaperoneLoader;
    
    public void initialize() {
        tripLoader = new FXMLLoader(getClass().getResource("NewTrip.fxml"));
        pastLoader = new FXMLLoader(getClass().getResource("PastTrips.fxml"));
        addLoader = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
        updateLoader = new FXMLLoader(getClass().getResource("UpdateStudent.fxml"));
        viewLoginsLoader = new FXMLLoader(getClass().getResource("VAL.fxml"));
        addChaperoneLoader = new FXMLLoader(getClass().getResource("Add_Chap.fxml"));
    }

    @FXML void AccPastTrip(ActionEvent event) {
        try {
            root = pastLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML void AddStudent(ActionEvent event) {
        try {
            root = addLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML void NewTrip(ActionEvent event) {
        try {
            root = tripLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @FXML void UpdateStudent(ActionEvent event) {
        try {
            root = updateLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML void viewLoginAttempts(ActionEvent event) {
        try {
            root = viewLoginsLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    } 
    
    @FXML void addChaperone(ActionEvent event) {
        try {
            root = addChaperoneLoader.load();
            Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    } 
}
