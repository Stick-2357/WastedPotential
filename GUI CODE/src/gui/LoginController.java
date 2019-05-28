/*     Team: Wasted Potential
    Project: ProDeo Router
       File: LoginController.java
Description: Takes mulitple addresses, and finds quickest route */
package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Scene;

public class LoginController {
    static Parent root;
    
    @FXML private TextField box1;
    @FXML private TextField box2;
    
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
     
    @FXML void UsernameEntered(ActionEvent event) throws SQLException {   
        ArrayList<String> names = new ArrayList<>();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wastedpotential", "root", "yoast");
        PreparedStatement selectNames = con.prepareStatement("SELECT name FROM chaperones");
        ResultSet nameResultSet = selectNames.executeQuery();
        while (nameResultSet.next()) {
            names.add(nameResultSet.getString("name"));
        }
        selectNames.close();
        
        if ("".equals(box1.getText()) || "".equals(box2.getText())) {
            System.out.println("Empty boxes");
        } else {
            int success;
            
            if (names.contains(box1.getText() + " " + box2.getText()) || ("Admin".equals(box1.getText()) && "Admin".equals(box2.getText()))) {
                success = 0;
                Scene scene = ((Node)event.getSource()).getScene();
                scene.setRoot(root);
            } else {
                success = 1;
                System.out.println("Failed login");
            }
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            PreparedStatement insertLogin = con.prepareStatement("INSERT INTO `wastedpotential`.`logins`" +
                                                                 "(`firstName`, `lastName`, `timeStamp`, `wasSuccessful`)" +
                                                                 "VALUES ('" + box1.getText() + "', '" + box2.getText() + "', '" + timestamp + "', '" + success +"')");
            insertLogin.executeUpdate();
            
        }
    }
}