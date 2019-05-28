/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VALcontroller {
    static Parent root;
    
    @FXML private Button backButton;
    @FXML private TableView loginsTable;
    
    public void initialize() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.print("IOException occured while loading second view");
            e.printStackTrace();
            System.exit(1);
        }
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wastedpotential", "root", "yoast");
        PreparedStatement selectLogins = con.prepareStatement("SELECT * FROM logins");
        ResultSet loginsResultSet = selectLogins.executeQuery();
        
        TableColumn<String, Login> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        TableColumn<String, Login> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        TableColumn<String, Login> dateColumn = new TableColumn<>("Timestamp");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        
        TableColumn<String, Login> wasSuccessfulColumn = new TableColumn<>("Was successful?");
        wasSuccessfulColumn.setCellValueFactory(new PropertyValueFactory<>("wasSuccessful"));
        
        loginsTable.getColumns().add(firstNameColumn);
        loginsTable.getColumns().add(lastNameColumn);
        loginsTable.getColumns().add(dateColumn);
        loginsTable.getColumns().add(wasSuccessfulColumn);
        
        while (loginsResultSet.next()) {
            boolean wasSuccessful;
            if (loginsResultSet.getBoolean("wasSuccessful")) {
                wasSuccessful = true;
            } else {
                wasSuccessful = false;
            }
            
            loginsTable.getItems().add(new Login(loginsResultSet.getString("firstName"), 
                                                 loginsResultSet.getString("lastName"), 
                                                 loginsResultSet.getString("timeStamp"), 
                                                 String.valueOf(wasSuccessful)));
        }
    }
    
    @FXML void BackButtonPressed(ActionEvent event) {
        Scene scene = ((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
}
