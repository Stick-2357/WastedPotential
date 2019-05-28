
package gui;
/*     Team: Wasted Potential
    Project: ProDeo Router
       File: AddDriverForTripController.java
Description: Takes mulitple addresses, and finds quickest route */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddDriverForTripController {
    static Parent root;
    
    @FXML private Button backButton;
    @FXML private Button addToButton;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField availibleSeatsField;
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
        if (firstNameField.getText() == "" || lastNameField.getText() == "") {
            System.out.println("Fields empty");
        } else {
            NewTripController.addToChaperones(firstNameField.getText() + " " + lastNameField.getText());
        }
        
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
}
