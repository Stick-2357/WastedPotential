/*     Team: Wasted Potential
    Project: ProDeo Router
       File: AddStudentForTripController.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AddStudentForTripController {
    static Parent root;
    
    @FXML private Button backButton;
    @FXML private Button addToButton;
    @FXML private TextArea studentsArea;
    
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTrip.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML void backButtonPressed(ActionEvent event) {
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
    
    @FXML void addTo(ActionEvent event) {
        String allStudents = studentsArea.getText();
        String[] students = allStudents.split("\n");
        
        for (String student : students) {
            NewTripController.addToKids(student);
        }
        
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
}
