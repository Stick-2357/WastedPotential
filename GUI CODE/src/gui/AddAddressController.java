/*     Team: Wasted Potential
    Project: ProDeo Router
       File: AddAddressController.java
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

public class AddAddressController {
    static Parent root;
    
    @FXML private Button backButton;

    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
    }
     
    @FXML void backButtonPressed(ActionEvent event)  {
        Scene scene = ((Node)event.getSource()).getScene();
            scene.setRoot(root);
    }
}
