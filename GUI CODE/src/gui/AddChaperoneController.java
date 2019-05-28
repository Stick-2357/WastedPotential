/*     Team: Wasted Potential
    Project: ProDeo Router
       File: AddChaperone.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddChaperoneController {
     static Parent root; 
    
    @FXML private Button backButton;
    @FXML private Button addChaperoneButton;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
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
    
    @FXML void backButtonClicked(ActionEvent event) {
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
    
    @FXML void addNewChaperone(ActionEvent event) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wastedpotential", "root", "yoast");
        PreparedStatement insertChap = con.prepareStatement("INSERT INTO 'wastedpotential'.'chaperones' ('firstName', 'lastName') " +
                                                            "VALUES ('" + firstName.getText() + "', '" + lastName.getText() +"')");
        insertChap.executeUpdate();
        System.out.println("Done");
    }
}
